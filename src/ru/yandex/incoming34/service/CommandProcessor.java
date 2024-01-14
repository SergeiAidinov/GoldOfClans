package ru.yandex.incoming34.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

import ru.yandex.incoming34.dto.Command;
import ru.yandex.incoming34.dto.GoldDeltaCommand;
import ru.yandex.incoming34.dto.GoldRequestCommand;

public class CommandProcessor implements Runnable {

	java.util.logging.Logger log = java.util.logging.Logger.getLogger(CommandProcessor.class.getName());

	private final List<Command> commandList;
	private final DataService dataService;

	public CommandProcessor(List<Command> commandList, DataService dataService) {
		this.commandList = commandList;
		this.dataService = dataService;
	}

	private void processCommand() {
		for (Command command : commandList) {
			if (Objects.isNull(command))
				continue;
			if (command.getClass().equals(GoldDeltaCommand.class)) {
				handleGoldDelta(command);
			} else if (command.getClass().equals(GoldRequestCommand.class)) {
				requestGold(command);
			}
		}
	}

	private void requestGold(Command command) {
		GoldRequestCommand goldRequest = (GoldRequestCommand) command;
		int gold = dataService.getClans().get(goldRequest.getClanId()).getGold();
		LocalDateTime localDateTime = LocalDateTime.now();
		GoldResponce goldResponce = new GoldResponce(goldRequest.getClanId(), localDateTime, gold);
		dataService.getAnswers().put(goldRequest.getRequestId(), goldResponce);

	}

	private void handleGoldDelta(Command command) {
		GoldDeltaCommand delta = (GoldDeltaCommand) command;
		log.log(Level.ALL, "Пользователь " + delta.getUserId() + " добавил к золоту клана " + delta.getClanId()
				+ " сумму " + delta.getGoldDelta());
		System.out.println("У клана " + delta.getClanId() + " было "
				+ dataService.getClans().get(delta.getClanId()).getGold() + " золота");
		System.out.println("Пользователь " + delta.getUserId() + " изменил количесвто золота у клана "
				+ delta.getClanId() + " на сумму " + delta.getGoldDelta());
		dataService.getClans().get(delta.getClanId()).addOrTakeGold(delta.getGoldDelta());
		System.out.println("Новое значение золота: " + dataService.getClans().get(delta.getClanId()).getGold());
	}

	@Override
	public void run() {
		processCommand();

	}

}

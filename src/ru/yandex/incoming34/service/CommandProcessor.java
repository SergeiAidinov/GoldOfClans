package ru.yandex.incoming34.service;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import ru.yandex.incoming34.dto.Command;
import ru.yandex.incoming34.dto.GoldDeltaCommand;
import ru.yandex.incoming34.dto.GoldDeltaResult;

public class CommandProcessor implements Runnable {

	private final List<Command> commandList;
	private final GamePlayService gamePlayService;
	private final Logger LOGGER = Logger.getLogger(CommandProcessor.class.getSimpleName());
	private final StringBuilder stringBuilder = new StringBuilder();

	public CommandProcessor(List<Command> commandList, GamePlayService dataService) {
		this.commandList = commandList;
		this.gamePlayService = dataService;

	}

	private void processCommand() {
		for (Command command : commandList) {
			if (Objects.isNull(command))
				continue;
			if (command.getClass().equals(GoldDeltaCommand.class))
				handleGoldDelta(command);
		}
	}

	private void handleGoldDelta(Command command) {
		GoldDeltaCommand delta = (GoldDeltaCommand) command;
		GoldDeltaResult goldDeltaResult = gamePlayService.getClans().get(delta.getClanId())
				.addOrTakeGold(delta.getGoldDelta());
		stringBuilder.append("Обрабатывается команда " + command.getClass().getCanonicalName() + ", поступившая "
				+ command.getCommandDateTime());
		stringBuilder.append(
				" РЕЗУЛЬТАТ: У клана " + delta.getClanId() + " было " + goldDeltaResult.getOldValue() + " золота. ");
		stringBuilder.append(goldDeltaResult.getOperationDateTime() + " Пользователь " + delta.getUserId()
				+ " изменил количесвто золота у клана " + delta.getClanId() + " на сумму " + delta.getGoldDelta());
		stringBuilder.append(". Новое значение золота: " + goldDeltaResult.getNewValue());
		LOGGER.info(stringBuilder.toString());
		stringBuilder.setLength(0);
	}

	@Override
	public void run() {
		processCommand();

	}

}

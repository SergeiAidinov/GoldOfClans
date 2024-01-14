package ru.yandex.incoming34.service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.logging.Level;

import ru.yandex.incoming34.MainClass;
import ru.yandex.incoming34.dto.Clan;
import ru.yandex.incoming34.dto.Command;
import ru.yandex.incoming34.dto.GoldDeltaCommand;
import ru.yandex.incoming34.dto.GoldRequestCommand;

public class ClanServiceImpl implements ClanService, Runnable {

	java.util.logging.Logger log = java.util.logging.Logger.getLogger(ClanServiceImpl.class.getName());

	/*
	 * private ConcurrentLinkedQueue<Command> commands = new
	 * ConcurrentLinkedQueue<Command>(); private final HashMap<Long, Clan> clans;
	 */

	private final DataService dataService;

	private ClanServiceImpl(DataService dataService) {
		this.dataService = dataService;
	}

	public static ClanServiceImpl instance(DataService dataService) {
		ClanServiceImpl clanServiceImplInstance = null;
		if (Objects.isNull(clanServiceImplInstance)) {
			clanServiceImplInstance = new ClanServiceImpl(dataService);
			return clanServiceImplInstance;
		}
		return clanServiceImplInstance;
	}

	/*
	 * @Override public Clan getClan(long clanId) { return clans.get(clanId); }
	 * 
	 * @Override public boolean addCommand(Command command) { return
	 * commands.offer(command); }
	 */

	public void run() {
		System.out.println("The Game commences...");
		handleCommands();
	}

	private void handleCommands() {
		while (true) {
			if (!dataService.getCommands().isEmpty()) {
				System.out.println("Заданий в очереди: " + dataService.getCommands().size());
				Command command = dataService.getCommands().poll();
				if (command.getClass().equals(GoldDeltaCommand.class)) {
					handleGoldDelta(command);
				} else if (command.getClass().equals(GoldRequestCommand.class)) {
					requestGold(command);
				}
			}
		}
	}

	private void requestGold(Command command) {
		GoldRequestCommand goldRequest = (GoldRequestCommand) command;
		int gold = dataService.getClans().get(goldRequest.getClanId()).getGold();
		LocalDateTime localDateTime = LocalDateTime.now();
		GoldResponce goldResponce = new GoldResponce(goldRequest.getClanId(), localDateTime, gold);
		MainClass.dataService.getAnswers().put(goldRequest.getRequestId(), goldResponce);

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
	public Clan getClan(long clanId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addCommand(Command command) {
		// TODO Auto-generated method stub
		return false;
	}
}

package ru.yandex.incoming34.service;

import java.util.List;
import java.util.Objects;

import ru.yandex.incoming34.dto.Clan;
import ru.yandex.incoming34.dto.Command;

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
				int limit = dataService.getCommands().size();
				List<Command> commandList = dataService.getCommands().stream().limit(limit).toList();
				System.out.println("Заданий в очереди: " + dataService.getCommands().size());
				// dataService.getCommands().removeAll(commandList);
				new Thread(new CommandProcessor(commandList, dataService)).start();
			}
		}
	}

	/*
	 * private void requestGold(Command command) { GoldRequestCommand goldRequest =
	 * (GoldRequestCommand) command; int gold =
	 * dataService.getClans().get(goldRequest.getClanId()).getGold(); LocalDateTime
	 * localDateTime = LocalDateTime.now(); GoldResponce goldResponce = new
	 * GoldResponce(goldRequest.getClanId(), localDateTime, gold);
	 * MainClass.dataService.getAnswers().put(goldRequest.getRequestId(),
	 * goldResponce);
	 * 
	 * }
	 */

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

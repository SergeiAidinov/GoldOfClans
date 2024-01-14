package ru.yandex.incoming34.service;

import java.util.Random;

import ru.yandex.incoming34.MainClass;
import ru.yandex.incoming34.dto.GoldDeltaCommand;

public class UserGoldService extends Thread {

	// private final ClanService clanService;
	private final DataService dataService;
	private final Random random = new Random();
	private final long userId;

	public UserGoldService(DataService dataService, long userId) {
		this.dataService = dataService;
		this.userId = userId;
	}

	public void run() {
		while (true) {
			addGoldToClan(userId, Long.valueOf(random.nextLong(0, MainClass.CLANS_QUANTITY)),
					random.nextInt(MainClass.MAX_CONTRIBUTION) - MainClass.MAX_CONTRIBUTION / 3);
			try {
				sleep(MainClass.USER_SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void addGoldToClan(long userId, long clanId, int gold) {
		dataService.getCommands().add(new GoldDeltaCommand(userId, clanId, gold));
	}

}

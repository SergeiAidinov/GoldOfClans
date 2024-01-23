package ru.yandex.incoming34.service;

import java.time.LocalDateTime;
import java.util.Random;

import ru.yandex.incoming34.MainClass;
import ru.yandex.incoming34.dto.GoldDeltaCommand;

public class UserGoldService extends Thread {

	private final GamePlayService gamePlayService;
	private final Random random = new Random();
	private final long userId;

	public UserGoldService(GamePlayService dataService, long userId) {
		this.gamePlayService = dataService;
		this.userId = userId;
	}

	public void run() {
		while (true) {
			GoldDeltaCommand goldDeltaCommand = new GoldDeltaCommand(userId, LocalDateTime.now(),
					Long.valueOf(random.nextLong(0, MainClass.CLANS_QUANTITY)),
					random.nextInt(MainClass.MAX_CONTRIBUTION) - MainClass.MAX_CONTRIBUTION / 3);
			gamePlayService.acceptCommand(goldDeltaCommand);
			try {
				sleep(MainClass.USER_SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

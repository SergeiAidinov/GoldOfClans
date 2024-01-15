package ru.yandex.incoming34;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ru.yandex.incoming34.dto.Clan;
import ru.yandex.incoming34.service.GamePlayService;
import ru.yandex.incoming34.service.Questor;
import ru.yandex.incoming34.service.UserGoldService;

public class MainClass {

	public static final int CLANS_QUANTITY = 3;
	public static final int MAX_CONTRIBUTION = 100;
	public static final int MIN_COMMANDS_PER_THREAD = (int) Math.pow(2, 10);
	public static final int USER_SLEEP = 0;
	public static final int QUESTOR_SLEEP = 0;
	private static final int USERS_QUANTITY = 100;
	private static final int QUESTORS_QUANTITY = 100;

	public static void main(String[] args) {

		GamePlayService gamePlayService = GamePlayService.instance(new ConcurrentHashMap<Long, Clan>(
				Map.of(0L, new Clan("Dwarves", 100), 1L, new Clan("Elves", 20), 2L, new Clan("Humans", 50))));

		for (int i = 0; i < USERS_QUANTITY; i++) {
			new Thread(new UserGoldService(gamePlayService, i)).start();
		}
		for (int i = 0; i < QUESTORS_QUANTITY; i++) {
			new Thread(new Questor(gamePlayService)).start();
		}
	}
}

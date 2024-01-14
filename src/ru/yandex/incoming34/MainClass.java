package ru.yandex.incoming34;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ru.yandex.incoming34.dto.Clan;
import ru.yandex.incoming34.service.DataService;
import ru.yandex.incoming34.service.Questor;
import ru.yandex.incoming34.service.UserGoldService;

public class MainClass {

	public static final int CLANS_QUANTITY = 3;
	public static final int MAX_CONTRIBUTION = 100;
	public static final int COMMANDS_PER_THREAD = (int) Math.pow(2, 10);
	public static final int USER_SLEEP = 0;
	public static final int QUESTOR_SLEEP = 0;
	private static final int USERS_QUANTITY = 100;
	private static final int QUESTORS_QUANTITY = 100;
	// public static DataService dataService;

	public static void main(String[] args) {

		DataService dataService = DataService.instance(new ConcurrentHashMap<Long, Clan>(
				Map.of(0L, new Clan("Dwarves", 100), 1L, new Clan("Elves", 20), 2L, new Clan("Humans", 50))));

		for (int i = 0; i < USERS_QUANTITY; i++) {
			UserGoldService userAddGoldService = new UserGoldService(dataService, i);
			Thread thread = new Thread(userAddGoldService);
			thread.start();
		}
		for (int i = 0; i < QUESTORS_QUANTITY; i++) {
			Thread requestThread = new Thread(new Questor(dataService));
			requestThread.start();
		}
	}
}

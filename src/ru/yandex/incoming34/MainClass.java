package ru.yandex.incoming34;

import java.util.HashMap;
import java.util.Map;

import ru.yandex.incoming34.dto.Clan;
import ru.yandex.incoming34.service.ClanServiceImpl;
import ru.yandex.incoming34.service.DataService;
import ru.yandex.incoming34.service.Questor;
import ru.yandex.incoming34.service.UserGoldService;

public class MainClass {

	public static final int CLANS_QUANTITY = 3;
	public static final int MAX_CONTRIBUTION = 100;
	private static final int USERS_QUANTITY = 100;
	private static final int QUESTORS_QUANTITY = 20;
	public static DataService dataService = DataService.instance();

	public static void main(String[] args) {
		final ClanServiceImpl clanService = ClanServiceImpl.instance(new HashMap<Long, Clan>(
				Map.of(0L, new Clan("Dwarves", 100), 1L, new Clan("Elves", 20), 2L, new Clan("Humans", 50))));
		Thread clanServiceThread = new Thread(clanService);
		clanServiceThread.start();

		for (int i = 0; i < USERS_QUANTITY; i++) {
			UserGoldService userAddGoldService = new UserGoldService(clanService, i);
			Thread thread = new Thread(userAddGoldService);
			thread.start();
		}
		for (int i = 0; i < QUESTORS_QUANTITY; i++) {
			Thread requestThread = new Thread(new Questor(clanService));
			requestThread.start();
		}
	}
}

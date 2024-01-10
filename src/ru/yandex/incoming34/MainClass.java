package ru.yandex.incoming34;

import java.util.HashMap;
import java.util.Map;

import ru.yandex.incoming34.dto.Clan;
import ru.yandex.incoming34.service.ClanServiceImpl;
import ru.yandex.incoming34.service.UserAddGoldService;

public class MainClass {

	public static void main(String[] args) {
		ClanServiceImpl clanService = new ClanServiceImpl(new HashMap<Long, Clan>(
				Map.of(0L, new Clan("Dwarves", 100), 1L, new Clan("Elves", 20), 2L, new Clan("Humans", 50))));
		Thread clanServiceThread = new Thread(clanService);
		clanServiceThread.start();

		for (int i = 0; i < 100; i++) {
			UserAddGoldService userAddGoldService = new UserAddGoldService(clanService);
			Thread thread = new Thread(userAddGoldService);
			thread.start();
		}
	}
}

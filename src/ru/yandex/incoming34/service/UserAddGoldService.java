package ru.yandex.incoming34.service;

import java.util.Random;

import ru.yandex.incoming34.dto.Delta;

public class UserAddGoldService extends Thread {

	private final ClanService clanService;
	private final Random random = new Random();

	public UserAddGoldService(ClanService clanService) {
		this.clanService = clanService;
	}

	public void run() {
		while (true) {
			addGoldToClan(random.nextLong(), Long.valueOf(random.nextLong(0, 3)), random.nextInt(50));
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void addGoldToClan(long userId, long clanId, int gold) {
		clanService.addDelta(new Delta(userId, clanId, gold));
	}

}

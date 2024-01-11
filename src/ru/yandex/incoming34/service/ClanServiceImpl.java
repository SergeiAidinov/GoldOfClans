package ru.yandex.incoming34.service;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;

import ru.yandex.incoming34.dto.Clan;
import ru.yandex.incoming34.dto.Delta;

public class ClanServiceImpl implements ClanService, Runnable {

	java.util.logging.Logger log = java.util.logging.Logger.getLogger(ClanServiceImpl.class.getName());

	private ConcurrentLinkedQueue<Delta> deltas = new ConcurrentLinkedQueue<Delta>();
	private final HashMap<Long, Clan> clans;

	private ClanServiceImpl(HashMap<Long, Clan> clans) {
		this.clans = clans;
	}

	public static ClanServiceImpl instance(HashMap<Long, Clan> clans) {
		ClanServiceImpl clanServiceImplInstance = null;
		if (Objects.isNull(clanServiceImplInstance)) {
			clanServiceImplInstance = new ClanServiceImpl(clans);
			return clanServiceImplInstance;
		}
		return clanServiceImplInstance;
	}

	@Override
	public Clan getClan(long clanId) {
		return clans.get(clanId);
	}

	@Override
	public boolean addDelta(Delta delta) {
		return deltas.offer(delta);
	}

	public void run() {
		System.out.println("The Game commences...");
		handleDeltas();
	}

	private void handleDeltas() {
		while (true) {
			if (!deltas.isEmpty()) {
				System.out.println(deltas.size());
				Delta delta = deltas.poll();
				log.log(Level.ALL, "Пользователь " + delta.getUserId() + " добавил к золоту клана " + delta.getClanId()
						+ " сумму " + delta.getGoldDelta());
				System.out.println(
						"У клана " + delta.getClanId() + " было " + clans.get(delta.getClanId()).getGold() + " золота");
				System.out.println("Пользователь " + delta.getUserId() + " добавил к золоту клана " + delta.getClanId()
						+ " сумму " + delta.getGoldDelta());
				clans.get(delta.getClanId()).addOrTakeGold(delta.getGoldDelta());
				System.out.println("Новое значение золота: " + clans.get(delta.getClanId()).getGold());
			}
		}
	}
}

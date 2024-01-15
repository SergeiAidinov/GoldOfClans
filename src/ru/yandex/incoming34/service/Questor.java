package ru.yandex.incoming34.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import ru.yandex.incoming34.MainClass;
import ru.yandex.incoming34.dto.GoldRequestCommand;
import ru.yandex.incoming34.dto.GoldResponse;

public class Questor implements Runnable {

	private final GamePlayService dataService;
	private final Random random = new Random();
	private final List<UUID> placedRequests = new ArrayList<>();

	public Questor(GamePlayService dataService) {
		this.dataService = dataService;
	}

	@Override
	public void run() {
		requestInfo();
	}

	private void requestInfo() {
		while (true) {
			UUID requestId = UUID.randomUUID();
			GoldRequestCommand goldRequest = new GoldRequestCommand(requestId,
					random.nextLong(0, MainClass.CLANS_QUANTITY));
			dataService.acceptCommand(goldRequest);
			placedRequests.add(requestId);
			try {
				Thread.sleep(MainClass.QUESTOR_SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			final List<UUID> handledUuids = new ArrayList<>();
			for (UUID uuid : placedRequests) {
				GoldResponse goldResponce = (GoldResponse) dataService.getAnswers().get(uuid);
				if (Objects.nonNull(goldResponce)) {
					System.out.println("По стостоянию на " + goldResponce.getLocalDateTime() + " у клана "
							+ goldResponce.getClanId() + " было " + goldResponce.getGold() + " золота.");
					handledUuids.add(uuid);
					dataService.getAnswers().remove(uuid);
				}
			}

			placedRequests.removeAll(handledUuids);

		}
	}

}

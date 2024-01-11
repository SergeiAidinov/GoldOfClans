package ru.yandex.incoming34.service;

import java.time.LocalDateTime;

import ru.yandex.incoming34.dto.Response;

public class GoldResponce extends Response {

	private final long clanId;
	private final LocalDateTime localDateTime;
	private final int gold;

	public GoldResponce(long clanId, LocalDateTime localDateTime, int gold) {
		this.clanId = clanId;
		this.localDateTime = localDateTime;
		this.gold = gold;
	}

	public long getClanId() {
		return clanId;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public int getGold() {
		return gold;
	}

}

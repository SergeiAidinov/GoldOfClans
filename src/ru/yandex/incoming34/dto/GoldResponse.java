package ru.yandex.incoming34.dto;

import java.time.LocalDateTime;

public class GoldResponse extends Response {

	private final int clanId;
	private final int gold;
	private final LocalDateTime localDateTime;

	public GoldResponse(int clanId, int gold, LocalDateTime localDateTime) {
		this.clanId = clanId;
		this.gold = gold;
		this.localDateTime = localDateTime;
	}

	public int getClanId() {
		return clanId;
	}

	public int getGold() {
		return gold;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

}

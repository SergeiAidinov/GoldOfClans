package ru.yandex.incoming34.dto;

import java.time.LocalDateTime;

public class GoldResponse extends Response {

	private final Long clanId;
	private final int gold;
	private final LocalDateTime localDateTime;

	public GoldResponse(Long clanId, LocalDateTime localDateTime, int gold) {
		this.clanId = clanId;
		this.gold = gold;
		this.localDateTime = localDateTime;
	}

	public Long getClanId() {
		return clanId;
	}

	public int getGold() {
		return gold;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

}

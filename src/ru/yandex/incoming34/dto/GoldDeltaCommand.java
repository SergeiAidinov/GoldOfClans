package ru.yandex.incoming34.dto;

import java.time.LocalDateTime;

public class GoldDeltaCommand extends Command {

	private final Long clanId;
	private final int goldDelta;

	public GoldDeltaCommand(long userId, LocalDateTime commandDateTime, Long clanId, int goldDelta) {
		super(userId, commandDateTime);
		this.clanId = clanId;
		this.goldDelta = goldDelta;
	}

	public Long getClanId() {
		return clanId;
	}

	public int getGoldDelta() {
		return goldDelta;
	}

}

package ru.yandex.incoming34.dto;

public class GoldDeltaCommand extends Command {

	private final Long clanId;
	private final int goldDelta;

	public GoldDeltaCommand(long userId, Long clanId, int goldDelta) {
		super(userId);
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

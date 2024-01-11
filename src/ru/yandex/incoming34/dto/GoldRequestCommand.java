package ru.yandex.incoming34.dto;

import java.util.UUID;

public class GoldRequestCommand extends Command {

	private final UUID requestId;
	private final Long clanId;

	public GoldRequestCommand(UUID requestId, Long clanId) {
		this.requestId = requestId;
		this.clanId = clanId;
	}

	public Long getClanId() {
		return clanId;
	}

	public UUID getRequestId() {
		return requestId;
	}

}

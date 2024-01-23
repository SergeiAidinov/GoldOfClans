package ru.yandex.incoming34.dto;

import java.time.LocalDateTime;

public abstract class Command {

	private final long userId;
	private final LocalDateTime commandDateTime;

	public Command(long userId, LocalDateTime commandDateTime) {
		this.userId = userId;
		this.commandDateTime = commandDateTime;
	}

	public long getUserId() {
		return userId;
	}

	public LocalDateTime getCommandDateTime() {
		return commandDateTime;
	}

}

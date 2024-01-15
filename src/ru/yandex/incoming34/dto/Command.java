package ru.yandex.incoming34.dto;

public abstract class Command {

	private final long userId;

	public Command(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}

}

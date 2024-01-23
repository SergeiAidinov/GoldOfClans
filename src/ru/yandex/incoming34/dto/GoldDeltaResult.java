package ru.yandex.incoming34.dto;

import java.time.LocalDateTime;

public class GoldDeltaResult {

	private final int oldValue;
	private final int newValue;
	private final LocalDateTime operationDateTime;

	public GoldDeltaResult(int oldValue, int newValue, LocalDateTime operationDateTime) {
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.operationDateTime = operationDateTime;
	}

	public int getOldValue() {
		return oldValue;
	}

	public int getNewValue() {
		return newValue;
	}

	public LocalDateTime getOperationDateTime() {
		return operationDateTime;
	}

}

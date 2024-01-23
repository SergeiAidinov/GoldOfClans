package ru.yandex.incoming34.structure;

import java.time.LocalDateTime;

import ru.yandex.incoming34.dto.GoldDeltaResult;

public class Clan {

	private final String name;
	private int gold;

	public Clan(String name, int gold) {
		this.name = name;
		this.gold = gold;
	}

	public String getName() {
		return name;
	}

	public GoldDeltaResult addOrTakeGold(int delta) {
		int oldValue = gold;
		gold = getGold() + delta;
		return new GoldDeltaResult(oldValue, gold, LocalDateTime.now());
	}

	public int getGold() {
		return gold;
	}
}

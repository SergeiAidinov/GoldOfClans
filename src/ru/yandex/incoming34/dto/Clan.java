package ru.yandex.incoming34.dto;

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

	public void addOrTakeGold(int delta) {
		gold = getGold() + delta;
	}

	public int getGold() {
		return gold;
	}
}

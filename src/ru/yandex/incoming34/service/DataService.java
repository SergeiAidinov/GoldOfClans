package ru.yandex.incoming34.service;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import ru.yandex.incoming34.dto.Clan;
import ru.yandex.incoming34.dto.Command;
import ru.yandex.incoming34.dto.Response;

/**
 * 
 */
public class DataService {

	private final HashMap<Long, Clan> clans;
	private final ConcurrentLinkedQueue<Command> commands = new ConcurrentLinkedQueue<Command>();
	private final ConcurrentHashMap<UUID, Response> answers = new ConcurrentHashMap<>();

	private DataService(HashMap<Long, Clan> clans) {
		this.clans = clans;

	}

	public static DataService instance(HashMap<Long, Clan> clans) {
		DataService dataServiceInsnance = null;
		if (Objects.isNull(dataServiceInsnance)) {
			dataServiceInsnance = new DataService(clans);
		}
		return dataServiceInsnance;
	}

	public ConcurrentHashMap<UUID, Response> getAnswers() {
		return answers;
	}

	public HashMap<Long, Clan> getClans() {
		return clans;
	}

	public ConcurrentLinkedQueue<Command> getCommands() {
		return commands;
	}

}

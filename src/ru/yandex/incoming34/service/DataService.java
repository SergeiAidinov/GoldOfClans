package ru.yandex.incoming34.service;

import java.util.ArrayList;
import java.util.List;
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

	private final ConcurrentHashMap<Long, Clan> clans;
	private final static ConcurrentLinkedQueue<Command> commands = new ConcurrentLinkedQueue<Command>();
	private final static ConcurrentHashMap<UUID, Response> answers = new ConcurrentHashMap<>();
	private static DataService dataServiceInsnance = null;

	private DataService(ConcurrentHashMap<Long, Clan> clans) {
		this.clans = clans;

	}

	public static DataService instance(ConcurrentHashMap<Long, Clan> clans) {
		if (Objects.isNull(dataServiceInsnance)) {
			dataServiceInsnance = new DataService(clans);
		}
		return dataServiceInsnance;
	}

	public ConcurrentHashMap<UUID, Response> getAnswers() {
		return answers;
	}

	public ConcurrentHashMap<Long, Clan> getClans() {
		return clans;
	}

	public void getCommands(Command command) {
		int limit = commands.size();
		if (limit >= 1024) {
			List<Command> commandList = new ArrayList<>();
			for (int i = 0; i < limit; i++)
				commandList.add(commands.poll());
			// commands.stream().limit(limit).toList(); //
			// commands.removeAll(commandList);
			new Thread(new CommandProcessor(commandList, this)).start();
		}
		commands.add(command);
	}

	/*
	 * public void requestGold(Command command) { GoldRequestCommand goldRequest =
	 * (GoldRequestCommand) command; int gold =
	 * clans.get(goldRequest.getClanId()).getGold(); LocalDateTime localDateTime =
	 * LocalDateTime.now(); GoldResponce goldResponce = new
	 * GoldResponce(goldRequest.getClanId(), localDateTime, gold);
	 * answers.put(goldRequest.getRequestId(), goldResponce);
	 * 
	 * }
	 */

}

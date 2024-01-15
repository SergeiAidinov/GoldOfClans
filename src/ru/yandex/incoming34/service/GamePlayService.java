package ru.yandex.incoming34.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ru.yandex.incoming34.MainClass;
import ru.yandex.incoming34.dto.Clan;
import ru.yandex.incoming34.dto.Command;

public class GamePlayService {

	private final ConcurrentHashMap<Long, Clan> clans;
	private final static ConcurrentLinkedQueue<Command> commands = new ConcurrentLinkedQueue<Command>();
	private static GamePlayService gamePlayServiceInsnance = null;
	Lock lock = new ReentrantLock(true);

	private GamePlayService(ConcurrentHashMap<Long, Clan> clans) {
		this.clans = clans;
	}

	public static GamePlayService instance(ConcurrentHashMap<Long, Clan> clans) {
		if (Objects.isNull(gamePlayServiceInsnance)) {
			gamePlayServiceInsnance = new GamePlayService(clans);
		}
		return gamePlayServiceInsnance;
	}

	public ConcurrentHashMap<Long, Clan> getClans() {
		return clans;
	}

	public void acceptCommand(Command command) {
		lock.lock();
		try {
			commands.add(command);
			int limit = commands.size();
			if (limit >= MainClass.MIN_COMMANDS_PER_THREAD) {
				List<Command> commandList = new ArrayList<>();
				for (int i = 0; i < limit; i++)
					commandList.add(commands.poll());
				new Thread(new CommandProcessor(commandList, this)).start();
			}

		} finally {
			lock.unlock();
		}
	}
}

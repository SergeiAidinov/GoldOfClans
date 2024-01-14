package ru.yandex.incoming34.service;

import java.util.List;

import ru.yandex.incoming34.dto.Command;

public class CommandProcessor implements Runnable {

	private final List<Command> commandList;

	public void processCommand() {

	}

	public CommandProcessor(List<Command> commandList) {
		super();
		this.commandList = commandList;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}

package ru.yandex.incoming34.service;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import ru.yandex.incoming34.dto.Response;

public class DataService {

	private final ConcurrentHashMap<UUID, Response> answers = new ConcurrentHashMap<>();

	private DataService() {

	}

	public static DataService instance() {
		DataService dataServiceInsnance = null;
		if (Objects.isNull(dataServiceInsnance)) {
			dataServiceInsnance = new DataService();
		}
		return dataServiceInsnance;
	}

	public ConcurrentHashMap<UUID, Response> getAnswers() {
		return answers;
	}

}

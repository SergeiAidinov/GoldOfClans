package ru.yandex.incoming34.service;

import ru.yandex.incoming34.dto.Clan;
import ru.yandex.incoming34.dto.Command;

public interface ClanService {

	Clan getClan(long clanId);

	boolean addCommand(Command command);
}

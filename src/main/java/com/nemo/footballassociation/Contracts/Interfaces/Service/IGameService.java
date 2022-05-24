package com.nemo.footballassociation.Contracts.Interfaces.Service;

import com.nemo.footballassociation.Contracts.Modules.DbModels.Game;
import com.nemo.footballassociation.Contracts.Modules.DbModels.LeagueBySeason;

import java.util.List;
import java.util.Optional;

public interface IGameService {
    Game saveGame(Game game);

    boolean existsGameByIds(int gameId);

    List<Game> getGames();

    Game getGameByIds(int gameId) throws Exception;

    Game updateGame(Game game, int gameId) throws Exception;
}

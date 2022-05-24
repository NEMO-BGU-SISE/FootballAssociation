package com.nemo.footballassociation.Service;

import com.nemo.footballassociation.Contracts.Interfaces.Repository.IGameRepository;
import com.nemo.footballassociation.Contracts.Interfaces.Service.IGameService;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Game;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService implements IGameService {
    private IGameRepository gameRepository;

    public GameService(IGameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game saveGame(Game game) {
        game.setId(gameRepository.findMaxId() == null ? 0 : gameRepository.findMaxId() + 1);
        return gameRepository.save(game);
    }

    @Override
    public boolean existsGameByIds(int gameId) {
        return gameRepository.existsById(gameId);
    }

    @Override
    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game getGameByIds(int gameId) throws Exception {
        return gameRepository.findById(gameId).orElseThrow(() ->
                new Exception(""));
    }

    @Override
    public Game updateGame(Game game, int gameId) throws Exception {
        if (gameRepository.existsById(gameId)) {
            try {
                Game gameToUpdate = gameRepository.findById(gameId).orElseThrow(() ->
                        new Exception(""));
                gameToUpdate.update(game);
                gameRepository.save(gameToUpdate);
                return gameToUpdate;
            } catch (Exception e) {
                throw e;
            }
        } else {
            return null;
        }
    }
}

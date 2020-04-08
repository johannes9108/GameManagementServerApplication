package dataaccess;

import domain.Game;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class GameDaoImpl implements GameDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Game createGame(Game newGame) {
        em.persist(newGame);
        return newGame;
    }

    @Override
    public Game removeGame(int id) {
        try {
            Game game = em.find(Game.class, id);
            game.getConsoles().clear();
            em.merge(game);
            em.remove(game);
            return game;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Game updateGame(Game updateGame) {
        em.merge(updateGame);
        return updateGame;
    }

    @Override
    public Game findGameByID(int id) {
        try {
            return em.find(Game.class, id);
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public List<Game> getAllGames() {
        Query query = em.createQuery("SELECT g From Game g");
        return query.getResultList();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Lucas
 */
public class DataBase {

    private Session session;
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private static final DataBase instance = new DataBase();

    private DataBase() {

    }

    public static synchronized DataBase instance() {
        return instance;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void updatePlayer(Player player) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(player);
        session.getTransaction().commit();
        session.close();
    }

    public void savePlayer(Player player) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(player);
        session.getTransaction().commit();
        session.close();
    }

    public Player getPlayer(String id) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        Player player = (Player) session.get(Player.class, id);
        session.getTransaction().commit();
        session.close();
        return player;
    }

    public Player getPlayerByID(String id) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        Player player = null;
        String sql = "SELECT * FROM Player WHERE tempID =:player_temp_id";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Player.class);
        query.setParameter("player_temp_id", id);
        if (!query.list().isEmpty()) {
            player = (Player) query.list().get(0);
        }
        session.close();
        return player;
    }

    public int countPlayers() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        int count = Integer.parseInt(session.createQuery("select count(*) from Player").uniqueResult().toString());
        session.close();
        return count;
    }

    public void updateGame(Game game) {
        if (game != null) {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(game);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void saveGame(Game game) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(game);
        session.getTransaction().commit();
        session.close();
    }

    public Game getGame(String id) {
        
        session = sessionFactory.openSession();
        session.beginTransaction();
        Game game = (Game) session.get(Game.class, id);
        if(game!=null){
            GameSessionModel.instance().addGame(game);
        }
        session.getTransaction().commit();
        session.close();
        return game;
    }

}

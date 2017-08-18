package dao;

import datamodels.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;


public class Sql2oTeamDao implements TeamDao {
    private final Sql2o sql2o;

    public Sql2oTeamDao(Sql2o newConnect) {
        sql2o = newConnect;
    }

    public void addTeam(Team newSquad) {
        try (Connection conn = sql2o.open()) {
            int serverId = (int) conn.createQuery("INSERT INTO teams (hackId, name, blurb) VALUES (:hackId, :name, :blurb)")
                    .addParameter("hackId", newSquad.getHackId())
                    .addParameter("name", newSquad. getTeamName())
                    .addParameter("blurb", newSquad.getTeamBlurb())
                    .addColumnMapping("HACKID", "hackId")
                    .addColumnMapping("NAME", "teamName")
                    .addColumnMapping("BLURB", "teamBlurb")
                    .executeUpdate().getKey();
            newSquad.setTeamId(serverId);
        } catch (Sql2oException catchIt) {
            catchIt.printStackTrace();
        }
    }

    public List<Team> getAllTeamsByHack(int hackId) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM teams WHERE hackId = :hackId")
                    .addParameter("hackId", hackId)
                    .addColumnMapping("TEAMID", "teamId")
                    .addColumnMapping("NAME", "teamName")
                    .addColumnMapping("BLURB", "teamBlurb")
                    .addColumnMapping("HACKID", "hackId")
                    .executeAndFetch(Team.class);
        }
    }

    
    public Team findById(int number) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM teams WHERE teamId = :teamId")
                    .addParameter("teamId", number)
                    .addColumnMapping("TEAMID", "teamId")
                    .addColumnMapping("NAME", "teamName")
                    .addColumnMapping("BLURB", "teamBlurb")
                    .addColumnMapping("HACKID", "hackId")
                    .executeAndFetchFirst(Team.class);
        }
    }

    
    public void changeName(String newName, int teamId) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE teams SET name = :newName WHERE teamId = :teamId")
                    .addParameter("name", newName)
                    .addParameter("teamId", teamId)
                    .executeUpdate();
        }
    }

    
    public void removeTeam(int teamId) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM teams WHERE teamId = :teamId")
                    .addParameter("teamId", teamId)
                    .executeUpdate();
        }
    }
}

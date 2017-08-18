package dao;


import datamodels.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oHackathonDao implements HackathonDao {
    private final Sql2o sql2o;

    public Sql2oHackathonDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public void addHack(Hackathon newHack) {
        try (Connection conn = sql2o.open()) {
            int newId = (int) conn.createQuery("INSERT INTO hackathons (focus, location) VALUES (:focus, :location)")
                    .addParameter("focus", newHack.getFocus())
                    .addParameter("location", newHack.getLocation())
                    .addColumnMapping("FOCUS", "focus")
                    .addColumnMapping("LOCATION", "location")
                    .executeUpdate()
                    .getKey();
            newHack.setId(newId);
        } catch (Sql2oException ex) {
            ex.printStackTrace();
        }

    }

    public Hackathon findHack(int hackId) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM hackathons WHERE id = :id")
                    .addParameter("id", hackId)
                    .executeAndFetchFirst(Hackathon.class);
        }
    }

    public List<Hackathon> getAllHacks() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM hackathons")
                    .executeAndFetch(Hackathon.class);
        }
    }

    public void updateHack(int hackId, String newLocation) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE hackathons SET location = :location WHERE id = :id")
                    .addParameter("LOCATION", newLocation)
                    .addParameter("ID", hackId)
                    .executeUpdate();
        } catch (Sql2oException error) {
            error.printStackTrace();
        }
    }


    public void removeHack(int hackId) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM hackathons WHERE id = :id")
                    .addParameter("ID", hackId)
                    .executeUpdate();
        } catch (Sql2oException error) {
            error.printStackTrace();
        }
    }
}

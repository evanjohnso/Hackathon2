package dao;

import datamodels.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import sun.jvm.hotspot.ui.MemoryViewer;

import java.util.List;

public class Sql2oMemberDao implements MembersDao {
    private final Sql2o connection;

    public Sql2oMemberDao(Sql2o newConnect) {
        connection = newConnect;
    }
    
    public void add(Members newFolk) {
        try (Connection conn = connection.open()) {
            int memNum = (int) conn.createQuery("INSERT INTO members (hackId, teamId, name, location) VALUES (:hackId, :teamId, :name, :location)")
                    .addParameter("hackId", newFolk.getHackId())
                    .addParameter("teamId", newFolk.getTeamId())
                    .addParameter("name", newFolk.getMemberName())
                    .addParameter("location", newFolk.getMemberLocation())
                    .addColumnMapping("HACKID", "hackId")
                    .addColumnMapping("TEAMID", "teamId")
                    .addColumnMapping("NAME", "memberName")
                    .addColumnMapping("LOCATION", "memberLocation")
                    .executeUpdate().getKey();
            newFolk.setMemberId(memNum);
        } catch (Sql2oException ex) {
            ex.printStackTrace();
        }
    }

    public List<Members> getAllMembersByTeam(int teamId) {
        try (Connection conn = connection.open()) {
            return conn.createQuery("SELECT * FROM members WHERE teamId = :teamId")
                    .addParameter("teamId", teamId)
                    .addColumnMapping("HACKID", "hackId")
                    .addColumnMapping("TEAMID", "teamId")
                    .addColumnMapping("NAME", "memberName")
                    .addColumnMapping("LOCATION", "memberLocation")
                    .executeAndFetch(Members.class);

        }

    }

    
    public Members findById(int thisID) {
        try (Connection conn = connection.open()) {
            return conn.createQuery("SELECT * FROM members WHERE memberId = :memberId")
                    .addParameter("memberId", thisID)
                    .addColumnMapping("HACKID", "hackId")
                    .addColumnMapping("TEAMID", "teamId")
                    .addColumnMapping("NAME", "memberName")
                    .addColumnMapping("LOCATION", "memberLocation")
                    .executeAndFetchFirst(Members.class);
        }
    }

    
    public void removeMember(int memberId) {
        try (Connection conn = connection.open()) {
            conn.createQuery("DELETE FROM members WHERE memberId = :memberId")
                    .addParameter("memberId", memberId)
                    .executeUpdate();
        }
    }
}

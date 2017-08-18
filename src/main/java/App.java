import dao.Sql2oHackathonDao;
import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import datamodels.Hackathon;
import datamodels.Members;
import datamodels.Team;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static spark.Spark.staticFileLocation;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        //Create a connection pathway
        String connectionString = "jdbc:h2:~/hackathons.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o neuralPathway = new Sql2o(connectionString,"","");

        //Instantiate Data Access Objects via neuralPathway
        Sql2oHackathonDao hackDao = new Sql2oHackathonDao(neuralPathway);
        Sql2oTeamDao teamDao = new Sql2oTeamDao(neuralPathway);
        Sql2oMemberDao memberDao = new Sql2oMemberDao(neuralPathway);


        //Homepage displays welcome blurb and link to continue on
        get("/", (request, response) -> {
            Map<String, Object> data = new HashMap<>();
            return new ModelAndView(data, "homepage.hbs");
        }, new HandlebarsTemplateEngine());

        //Hackathons displays all current hackathons around
        get("/hackathons", (request, response) -> {
            Map<String, Object> data = new HashMap<>();

            List<Hackathon> listAll = hackDao.getAllHacks();
            int size = listAll.size();
            //If no hacks, display a few to get started
            if (size == 0) {
                hackDao.addHack(new Hackathon("Java", "Portland, OR") );
                hackDao.addHack(new Hackathon("Python", "Seattle, WA"));
                hackDao.addHack(new Hackathon("Ruby", "San Fransisco, CA"));
            }
            data.put("hacks", hackDao.getAllHacks());

            return new ModelAndView(data, "localHacks.hbs");
        }, new HandlebarsTemplateEngine());

        //Display the registered teams, or link to a form to register a team if none already
        get("/hackathons/:id", (request, response) -> {
            Map<String, Object> data = new HashMap<>();
            int hackId = Integer.parseInt(request.params("id") );
            Hackathon thisHack = hackDao.findHack(hackId);
            List<Team> theseTeams = teamDao.getAllTeamsByHack(hackId);

            data.put("thisHack", thisHack);
            data.put("teams", theseTeams);
            return new ModelAndView(data, "specificHack.hbs");
        }, new HandlebarsTemplateEngine());

        //Display a form for a contributor to register a new team
        get("/hackathons/:id/teams/registration", (request, response) -> {
            Map<String, Object> data = new HashMap<>();
            int thisHackId = Integer.parseInt(request.params("id"));

            data.put("thisHack", thisHackId);
            return new ModelAndView(data, "registration.hbs");
        }, new HandlebarsTemplateEngine());

        //After team is registered, link back to Hack page and update
        post("/hackathons/:hackId", (request, response) -> {
            Map<String, Object> data = new HashMap<>();

            int hackId = Integer.parseInt(request.params("hackId"));
            String event = request.queryParams("event");
            String newName = request.queryParams("name");
            String newBlurb = request.queryParams("blurb");

            teamDao.addTeam(new Team(newName, newBlurb, hackId));

            response.redirect("/hackathons/" + hackId);
            return null;
        });

//        If team wants to edit or remove team members
        get("/hackathons/:hackId/team/:teamId/edit", (request, response) -> {
            Map<String, Object> data = new HashMap<>();
            int hackId = Integer.parseInt(request.params("hackId"));
            int teamId = Integer.parseInt(request.params("teamId"));

            data.put("thisTeam", teamDao.findById(teamId) );

            return new ModelAndView(data, "edit.hbs");
        }, new HandlebarsTemplateEngine());
//        //Update name
        post("/team/:teamId/changedName", (request, response) -> {
            Map<String, Object> data = new HashMap<>();
            int teamId = Integer.parseInt(request.params("teamId"));
            String newName = request.queryParams("newName");
            teamDao.changeName(newName, teamId);
            int hackId = teamDao.findById(teamId).getHackId();
            response.redirect("/hackathons/" + hackId);
            return null;
        });
//        //Add new team member
        post("/team/:teamId/addMember", (request, response) -> {
            Map<String, Object> data = new HashMap<>();
            int teamId = Integer.parseInt(request.params("teamId"));
            int hackId = teamDao.findById(teamId).getHackId();

            String name = request.queryParams("name");
            String city = request.queryParams("city");
            memberDao.add(new Members(name, city, teamId, hackId));

            response.redirect("/hackathons/" + hackId);
            return null;
        });
//        //Remove specific team member
        post("/team/:teamId/removeMember", (request, response) -> {
            Map<String, Object> data = new HashMap<>();
            int teamId = Integer.parseInt(request.params("teamId"));
            int hackId = teamDao.findById(teamId).getHackId();
            String name = request.queryParams("noName");
            int finders = memberDao.findByName(name).getMemberId();
            memberDao.removeMember(finders);
            response.redirect("/hackathons/" + hackId);
            return null;
        });
    }
}

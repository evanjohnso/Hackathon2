import datamodels.Hackathon;
import datamodels.Members;
import datamodels.Team;
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

        //Homepage displays welcome message and link to continue on
        get("/", (request, response) -> {
            Map<String, Object> data = new HashMap<>();
            Hackathon Java = new Hackathon("Java", "Portland, OR");
            Hackathon Python = new Hackathon("Python", "Seattle, WA");
            Hackathon Ruby = new Hackathon("Ruby", "San Fransisco, CA");
            //Create three starter Hack's on homepage, but don't display until next page
            request.session().attribute("hacks", Hackathon.getInstances());

            return new ModelAndView(data, "homepage.hbs");
        }, new HandlebarsTemplateEngine());

        //Hackathons displays all current hackathons around
        get("/hackathons", (request, response) -> {
            Map<String, Object> data = new HashMap<>();
            //Display the hard coded hack's on the page
            data.put("hacks", request.session().attribute("hacks"));
            return new ModelAndView(data, "localHacks.hbs");
        }, new HandlebarsTemplateEngine());

        //Display the registered teams, or link to a form to register a team if none already
        get("/hackathons/:id", (request, response) -> {
            Map<String, Object> data = new HashMap<>();
            String event = request.params("id");
            Hackathon thisHack = Hackathon.findHack(event);
            ArrayList<Team> theseTeams = thisHack.getTeams();
            data.put("thisHack", thisHack);
            data.put("teams", theseTeams);
            return new ModelAndView(data, "specificHack.hbs");
        }, new HandlebarsTemplateEngine());

        //Display a form for a contributor to register a new team
        get("/registration", (request, response) -> {
            Map<String, Object> data = new HashMap<>();
            data.put("hacks", request.session().attribute("hacks"));
            return new ModelAndView(data, "registration.hbs");
        }, new HandlebarsTemplateEngine());

        //After team is registered, link back to Hack page and update
        post("/registration/success", (request, response) -> {
            Map<String, Object> data = new HashMap<>();
            String event = request.queryParams("event");
            String newName = request.queryParams("name");
            String newBlurb = request.queryParams("blurb");
            int starters = Integer.parseInt(request.queryParams("members"));
            Team newTeam = new Team(newName, newBlurb, starters);
            Hackathon thisHack = Hackathon.findHack(event);
            thisHack.addTeam(newTeam);
            response.redirect("/hackathons");
            return null;
        });

        //If team wants to edit or remove team members
        get("/edit", (request, response) -> {
            Map<String, Object> data = new HashMap<>();
            return new ModelAndView(data, "edit.hbs");
        }, new HandlebarsTemplateEngine());
        //Update name
        post("/team/changedName", (request, response) -> {
            Map<String, Object> data = new HashMap<>();
            //Take user input
            String oldName = request.queryParams("oldName");
            String newName = request.queryParams("newName");
            //Look for team in instances array
            Team thisTeam = Team.findTeam(oldName);
            if (thisTeam != null)
                thisTeam.setTeamName(newName);
            response.redirect("/hackathons");
            return null;
        });
        //Add new team member
        post("/team/addMember", (request, response) -> {
            Map<String, Object> data = new HashMap<>();
            String teamName = request.queryParams("squad");
            String name = request.queryParams("name");
            String city = request.queryParams("city");
            int age = Integer.parseInt(request.queryParams("age"));
            Members newGuy = new Members();
            newGuy.setNewMember(name, city, age);

            //Find what team they are on
            Team thisTeam = Team.findTeam(teamName);
            if (thisTeam != null)
                thisTeam.addMember(newGuy);
            response.redirect("/hackathons");
            return null;
        });
        //Remove specific team member
        post("/team/removeMember", (request, response) -> {
            Map<String, Object> data = new HashMap<>();
            String teamName = request.queryParams("teamName");
            String name = request.queryParams("noName");

            //Find team they are on and nuke them
            Team thisTeam = Team.findTeam(teamName);
            if (thisTeam != null) {
                thisTeam.removeMember(name);
            }
            response.redirect("/hackathons");
            return null;
        });
    }
}

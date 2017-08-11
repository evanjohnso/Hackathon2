import datamodels.Hackathon;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

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

        get("/hackathons/:id" , (request, response) -> {
            Map<String, Object> data = new HashMap<>();
            String event = request.params("id");
            Hackathon thisHack = Hackathon.findHack(event);
            data.put("thisHack", thisHack);
            data.put("teams", thisHack.getTeams());

            return new ModelAndView(data, "hack.hbs");
        }, new HandlebarsTemplateEngine());
    }
}

import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/patient", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("patient");
      Date birthday = request.queryParams("birthday");
      Date(year,month,day)
      Patient patient = new Patient(name, birthday);
      model.put("patient", patient);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // post("/patient", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //
    // })


    // Integer patientBirthday = Integer.parseInt(request.queryParams("birthday"));
    // patient.update(name, birthday);


    // get("/doctors/:doctorid/name/:id", (request,response) -> {
    //   Map<String,Object> model = new HashMap<String, Object>();
    //   Doctor doctor = Doctor.find(Integer.parseInt(request.params(":doctorid")));
    //   Patient patient = Patient.find(Integer.parseInt(request.params(":patientID")));
    //   model.put("doctor", doctor);
    //   model.put("patient", patient);
    //   model.put("template", "templates/");
    // });
    //
    // post("/doctors", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String,Object>();
    //
    //   String name = request.queryParams("name");
    //   Doctor newDoc = new Doctor(name);
    //   request.session().attribute("doctor", newDoc);
//
//       model.put("template", "templates/success.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
  }
}

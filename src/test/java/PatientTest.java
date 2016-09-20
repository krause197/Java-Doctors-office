import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Date;
// import java.sql.*;

public class PatientTest{
  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/doctors_office_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM patients *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void newPatient_InstantiatesCorrectly(){
    Patient newPatient = new Patient("Amy Pond", new Date(1982,3,16));
    assertTrue(newPatient instanceof Patient);
  }

  @Test
  public void shenanigans_ReturnsCorrectly() {
    Patient newPatient = new Patient("Amy Pond", new Date(1982,3,16));
    int patId = newPatient.getId();
    assertTrue(newPatient instanceof Patient);
  }

  @Test
  public void newDoctor_InstantiatesWithID() {
    Patient newPatient = new Patient("Amy Pond", new Date(1982,3,16));
    assertTrue(newPatient.getId() > 0);
  }

  // @Test
  // public void all_ReturnsCorrectly() {
  //   Patient firstPatient = new Patient("Amy Pond", new Date(1982,3,16));
  //   Patient secondPatient = new Patient("Martha", new Date(1981,2,27));
  //   List<Patient> allPatientsTest = new ArrayList();
  //   allPatientsTest.add(firstPatient);
  //   allPatientsTest.add(secondPatient);
  //   assertTrue(Patient.all().get(0).equals(firstPatient));
  //   assertTrue(Patient.all().get(1).equals(secondPatient));
  // }


}

import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class DoctorTest{

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/doctors_office_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM doctors *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void newDoctor_InstantiatesCorrectly(){
    Doctor newDoc = new Doctor("Who", "Time Travel");
    assertTrue(newDoc instanceof Doctor);
  }

  @Test
  public void getName_ReturnsCorrectly(){
    Doctor newDoc = new Doctor("Who", "Time Travel");
    String expectedOutput = "Who";
    assertTrue(newDoc.getName().equals(expectedOutput));
  }

  @Test
  public void getSpecialty_ReturnsCorrectly(){
    Doctor newDoc = new Doctor("Who", "Time Travel");
    String expectedOutput = "Time Travel";
    assertTrue(newDoc.getSpecialty().equals(expectedOutput));
  }

  @Test
  public void getId_NewDoctorIdInstantiatesCorrectly(){
    Doctor newDoc = new Doctor("Who", "Time Travel");
    assertTrue(newDoc.getId() > 0);
  }

  @Test
  public void find_ReturnsDoctor(){
    Doctor newDoc = new Doctor("Who", "Time Travel");
    int docId = newDoc.getId();
    assertTrue(Doctor.find(docId) instanceof Doctor);
  }

  @Test
  public void find_ReturnsCorrectDoctor(){
    Doctor newDoc = new Doctor("Who", "Time Travel");
    int docId = newDoc.getId();
    assertTrue(newDoc.equals(Doctor.find(docId)));
  }

  @Test
  public void all_ReturnsCorrectly(){
    Doctor firstDoc = new Doctor("Who", "Time Travel");
    Doctor secondDoc = new Doctor("What", "Confusion");
    List<Doctor> allDocs = new ArrayList<Doctor>();
    allDocs.add(firstDoc);
    allDocs.add(secondDoc);
    assertTrue(Doctor.all().get(0).equals(firstDoc));
    assertTrue(Doctor.all().get(1).equals(secondDoc));
  }

  @Test
  public void assignPatient_AssignsCorrectly(){
    Doctor newDoc = new Doctor("Who", "Time Travel");
    Patient firstPatient = new Patient("Amy Pond", new Date(1982,3,16));
    int patientId = firstPatient.getId();
    newDoc.assignPatient(firstPatient);
    assertEquals(true, firstPatient.getDoctorId() == Patient.find(patientId).getDoctorId());
  }

  @Test
  public void allPatients_ReturnsCorrectly() {
    Doctor newDoc = new Doctor("Who", "Time Travel");
    Patient firstPatient = new Patient("Amy Pond", new Date(1982,3,16));
    Patient secondPatient = new Patient("River Song", new Date(1981,2,27));
    newDoc.assignPatient(firstPatient);
    newDoc.assignPatient(secondPatient);
    assertTrue(newDoc.allPatients().get(0).equals(firstPatient));
    assertTrue(newDoc.allPatients().get(1).equals(secondPatient));
  }
}

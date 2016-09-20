import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Doctor{

  private String name;
  private String specialty;
  private int id;

  public Doctor(String _name, String _specialty){
    name = _name;
    specialty = _specialty;
    this.save();
  }

  @Override
  public boolean equals(Object otherDoctor){
    if(!(otherDoctor instanceof Doctor)){
      return false;
    } else {
      Doctor newDoc = (Doctor) otherDoctor;
      return name.equals(newDoc.getName());
    }
  }

  public String getName(){
    return name;
  }

  public String getSpecialty(){
    return specialty;
  }

  public int getId(){
    return id;
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO doctors (name, specialty) VALUES (:name, :specialty)";
      id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("specialty", specialty)
        .executeUpdate()
        .getKey();
    }
  }

  public static Doctor find(int _id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM doctors WHERE id=:id";
      Doctor tempDoc = con.createQuery(sql)
        .addParameter("id", _id)
        .executeAndFetchFirst(Doctor.class);
      return tempDoc;
      }
  }

  public static List<Doctor> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM doctors";
      List<Doctor> allDocs = con.createQuery(sql)
        .executeAndFetch(Doctor.class);

      return allDocs;
    }
  }

  public void assignPatient(Patient _patient) {
    try(Connection con = DB.sql2o.open()) {
      _patient.assignDoctor(id);
      String sql = "UPDATE patients SET doctorid = :doctorid WHERE id = :patientId";
      con.createQuery(sql)
        .addParameter("doctorid", id)
        .addParameter("patientId", _patient.getId())
        .executeUpdate();
    }
  }

  public List<Patient> allPatients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients WHERE doctorid = :doctorid";
      List<Patient> allPatients = new ArrayList();
      allPatients = con.createQuery(sql)
        .addParameter("doctorid", id)
        .executeAndFetch(Patient.class);
      return allPatients;
    }
  }
}

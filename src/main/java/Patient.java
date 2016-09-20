import org.sql2o.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Patient{

  private String name;
  private Date birthday;
  private int id;
  private int doctorid = 0;

  public Patient(String _name, Date _birthday) {
    name = _name;
    birthday = _birthday;
    this.save();
  }

  @Override
  public boolean equals(Object otherObject) {
    if(!(otherObject instanceof Patient)) {
      return false;
    } else {
      Patient newObject = (Patient) otherObject;
      return id == newObject.getId();
    }
  }

  public String getName() {
    return name;
  }

  public void assignDoctor(int _doctorId) {
    doctorid = _doctorId;
  }

  public Date getBirthday() {
    return birthday;
  }

  public int getId() {
    return id;
  }

  public int getDoctorId() {
    return doctorid;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patients (name, birthday) VALUES (:name, :birthday)";
      id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("birthday", birthday)
        .executeUpdate()
        .getKey();
    }
  }

  public static Patient find(int _id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM patients WHERE id=:id";
      Patient tempPatient = con.createQuery(sql)
        .addParameter("id", _id)
        .executeAndFetchFirst(Patient.class);
      return tempPatient;
      }
  }

  public static List<Patient> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients";
      List<Patient> allPatients = con.createQuery(sql)
        .executeAndFetch(Patient.class);
      return allPatients;
    }
  }
}

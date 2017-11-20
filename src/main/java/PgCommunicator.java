import model.Appointment;
import model.Doctor;
import model.Patient;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class PgCommunicator {
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    public PgCommunicator() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("test1");
        this.entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    public <T> void persist(T object) {
        this.entityManager.persist(object);
    }

    public <T> void merge(T object) {
        this.entityManager.merge(object);
    }

    public <T> void remove(T object) {
        this.entityManager.remove(object);
    }

    public List<Doctor> getAllDoctors() {
        return entityManager.createQuery("SELECT d from Doctor d").getResultList();
    }

    public List<Appointment> getAllAppointments() {
        return entityManager.createQuery("SELECT d from Appointment d").getResultList();
    }

    public List<Patient> getAllPatients() {
        return entityManager.createQuery("SELECT d from Patient d").getResultList();
    }

    public List<Patient> getPatientsBySurname(String name) {
        Query query = entityManager.createQuery("SELECT a FROM Patient a WHERE name = " + name);
        List<Patient> patients = query.getResultList();
        return patients;
    }

    public List<Appointment> getDoctorAppointments(Doctor doc, int pageNumber, int pageSize) {
        Query query = entityManager.createQuery("SELECT a FROM Appointment a WHERE doctor_id = " + doc.getId());
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Appointment> res = query.getResultList();
        return res;
    }

    public void commit() {
        this.entityManager.getTransaction().commit();
    }

    public void close() {
        this.entityManager.close();
    }
}

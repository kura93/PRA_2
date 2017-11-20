import model.Appointment;
import model.Doctor;
import model.Patient;

import java.util.List;


public class DeserializeAndInsertToDb {
    public static void main(String[] args) {
        try {
            Serialization serialization = new Serialization();
            PgCommunicator pg = new PgCommunicator();
            List<Doctor> doctors = serialization.deserializeFromXml("doctors.xml", Doctor.class);
            for(Doctor doctor: doctors) {
                pg.merge(doctor);
            }


            List<Patient> patients = serialization.deserializeFromXml("patients.xml", Patient.class);
            for(Patient patient: patients) {
                pg.merge(patient);
            }


            List<Appointment> appointments = serialization.deserializeFromXml("appointments.xml", Appointment.class);
            for(Appointment appointment: appointments) {
                pg.merge(appointment);
            }
            pg.commit();
        } catch (Throwable exc) {
            System.err.println("Initial SessionFactory creation failed." + exc);
        } finally {
            System.exit(1);
        }
    }
}

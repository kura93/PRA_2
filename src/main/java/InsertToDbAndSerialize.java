import model.Address;
import model.Appointment;
import model.Doctor;
import model.Patient;

import java.util.Date;

public class InsertToDbAndSerialize {
    public static void main(String[] args) {
        try {
            PgCommunicator pg = new PgCommunicator();

            Doctor doctor1 = new Doctor("Jan", "Kowalski", "Neurolog");
            Doctor doctor2 = new Doctor("Matesz", "Mateusz", "Ortopeda");
            Patient patient1 = new Patient("Patient1", "Pat1", new Date());
            Patient patient2 = new Patient("Patient2", "Pat2", new Date());
            Patient patient3 = new Patient("Patient3", "Pat3", new Date());
            Patient patient4 = new Patient("Patient4", "Pat4", new Date());

            patient1.doctors.add(doctor1);
            patient2.doctors.add(doctor1);
            patient2.doctors.add(doctor2);

            doctor1.addPatient(patient1);
            doctor1.addPatient(patient2);
            doctor2.addPatient(patient2);

            Address address1 = new Address("City2", "Street2", 36, "60-876");
            Address address2 = new Address("City3", "Street3", 36, "60-876");
            Address address3 = new Address("City4", "Street4", 36, "60-876");
            Address address4 = new Address("City5", "Street5", 36, "60-876");

            patient1.setAddress(address1);
            patient2.setAddress(address2);
            patient3.setAddress(address3);
            patient4.setAddress(address4);

            Appointment appointment = new Appointment(doctor1, patient1, new Date());
            Appointment appointment1 = new Appointment(doctor1, patient2, new Date());
            Appointment appointment2 = new Appointment(doctor1, patient3, new Date());
            Appointment appointment3 = new Appointment(doctor1, patient1, new Date());
            Appointment appointment4 = new Appointment(doctor2, patient1, new Date());
            Appointment appointment5 = new Appointment(doctor2, patient4, new Date());
            Appointment appointment6 = new Appointment(doctor2, patient3, new Date());
            Appointment appointment7 = new Appointment(doctor2, patient4, new Date());

            pg.persist(address1);
            pg.persist(address2);
            pg.persist(address3);
            pg.persist(address4);
            pg.persist(doctor1);
            pg.persist(doctor2);
            pg.persist(patient1);
            pg.persist(patient2);
            pg.persist(patient3);
            pg.persist(patient4);
            pg.persist(appointment);
            pg.persist(appointment1);
            pg.persist(appointment2);
            pg.persist(appointment3);
            pg.persist(appointment4);
            pg.persist(appointment5);
            pg.persist(appointment6);
            pg.persist(appointment7);

            pg.commit();


            Serialization serialization = new Serialization();
            serialization.serializeDataToXml(pg.getAllAppointments(), "appointments.xml");
            serialization.serializeDataToJson(pg.getAllAppointments(), "appointments.json");

            serialization.serializeDataToXml(pg.getAllDoctors(), "doctors.xml");
            serialization.serializeDataToJson(pg.getAllDoctors(), "doctors.json");

            serialization.serializeDataToXml(pg.getAllPatients(), "patients.xml");
            serialization.serializeDataToJson(pg.getAllPatients(), "patients.json");

            pg.close();

        } catch (Throwable exc) {
            System.err.println("Initial SessionFactory creation failed." + exc);
        } finally {
            System.exit(1);
        }


    }
}

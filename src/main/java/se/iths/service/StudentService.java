package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.StudentException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public Student createStudent(Student student) {
        try {
            entityManager.persist(student);
            return student;
        } catch (RuntimeException e) {

            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            StudentException exception = new StudentException(400, "BAD_REQUEST", "Data missing. Student not created");
            String message = gson.toJson(exception);
            System.out.println(message);

            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(message)
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = entityManager.createQuery("SELECT s from Student s", Student.class).getResultList();
        if (students.size() == 0) {

            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("No students in database.")
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .build());
        }
        return students;
    }
}

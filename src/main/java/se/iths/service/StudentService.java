package se.iths.service;

import se.iths.entity.Student;
import se.iths.exception.ExceptionMessage;
import se.iths.exception.SaveStudentException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.channels.IllegalChannelGroupException;
import java.util.List;

@Transactional
public class StudentService {

    ExceptionMessage exceptionMessage;

    @PersistenceContext
    EntityManager entityManager;

    public Student createStudent(Student student) throws SaveStudentException {
        try {
            entityManager.persist(student);
            return student;
        } catch (Exception e) {
            exceptionMessage = new ExceptionMessage(400, "BAD_REQUEST", "Required data is missing. Student not created");
            String message = exceptionMessage.getJsonException();

            throw new SaveStudentException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(message)
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = entityManager.createQuery("SELECT s from Student s", Student.class).getResultList();
        return students;
    }

    public Student getStudentById(Long id) {
        Student student = entityManager.find(Student.class, id);
        return student;
    }

    public List<Student> getStudentsByLastName(String lastName) {
        String query = "SELECT s from Student s WHERE s.lastName= :lastName";
        List<Student> foundStudents = entityManager.createQuery(query, Student.class).setParameter("lastName", lastName).getResultList();
        return foundStudents;
    }

    public Student updateStudent(Student student) throws WebApplicationException {
        if (student.getId() == null) {
            exceptionMessage = new ExceptionMessage(400, "BAD_REQUEST", "Required data is missing. Student not updated");
            String message = exceptionMessage.getJsonException();
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(message)
                    .type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        if (entityManager.find(Student.class, student.getId()) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Student with ID " + student.getId() + " not found")
                    .type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        return entityManager.merge(student);
    }

    public void deleteStudent(Long id) {
        try {
            Student studentToRemove = entityManager.find(Student.class, id);
            entityManager.remove(studentToRemove);
        } catch (Exception e) {
            exceptionMessage = new ExceptionMessage(404, "NOT_FOUND", "Student with ID " + id + " not found");
            String message = exceptionMessage.getJsonException();
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(message)
                    .type(MediaType.TEXT_PLAIN_TYPE).build());
        }
    }
}
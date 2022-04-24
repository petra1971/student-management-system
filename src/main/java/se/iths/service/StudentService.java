package se.iths.service;

import se.iths.entity.Student;
import se.iths.exception.ExceptionMessage;
import se.iths.exception.SaveStudentException;
import se.iths.utils.JsonConverter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Transactional
public class StudentService {

    ExceptionMessage exceptionMessage;

    @PersistenceContext
    EntityManager entityManager;

    public Student createStudent(Student student) {
        entityManager.persist(student);
        return entityManager.find(Student.class, student.getId());
    }

    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT s from Student s", Student.class).getResultList();
    }

    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> getStudentsByLastName(String lastName) {
        String query = "SELECT s from Student s WHERE s.lastName= :lastName";
        return entityManager.createQuery(query, Student.class).setParameter("lastName", lastName).getResultList();
    }

    public Student updateStudent(Student student) {
        return entityManager.merge(student);
    }

    public Student modifyStudent(Long id, Student student) {
        Student updatedStudent = entityManager.find(Student.class, id);
        if (student.getFirstName() != null)
            updatedStudent.setFirstName(student.getFirstName());
        if (student.getLastName() != null)
            updatedStudent.setLastName(student.getLastName());
        if (student.getEmail() != null)
            updatedStudent.setEmail((student.getEmail()));
        if (student.getPhoneNumber() != null)
            updatedStudent.setPhoneNumber(student.getPhoneNumber());
        return entityManager.merge(updatedStudent);
    }

    public void deleteStudent(Long id) {
        Student studentToRemove = entityManager.find(Student.class, id);
        entityManager.remove(studentToRemove);
    }
}
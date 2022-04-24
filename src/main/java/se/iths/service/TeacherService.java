package se.iths.service;

import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public Teacher getTeacherById(Long id) {
        return entityManager.find(Teacher.class, id);
    }
}

package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    public Subject getSubjectById(Long id) {
        return entityManager.find(Subject.class, id);
    }
}

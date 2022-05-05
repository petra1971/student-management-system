package se.iths.utils;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData() {

        Subject subject1 = new Subject ("Matte", "200" );
        Subject subject2 = new Subject ("Svenska", "300" );
        Subject subject3 = new Subject ("Engelska", "200" );

        Teacher teacher1 = new Teacher("Ulla", "Blom", "ullablom@mail.se");
        Teacher teacher2 = new Teacher("Berit", "Merkander", "berit@mail.se");

        Student student1 = new Student("Lars", "Hallin", "lars@ankeborg.se");
        Student student2 = new Student("Ulrika", "Ejerblom", "ulrika@ankeborg.se");
        Student student3 = new Student("Alexander", "Lukas", "alex@ankeborg.se");

        teacher1.addSubject(subject3);
        teacher1.addSubject(subject1);
        teacher2.addSubject(subject2);

        student1.addSubject(subject2);
        student2.addSubject(subject1);
        student2.addSubject(subject2);
        student3.addSubject(subject1);
        student3.addSubject(subject2);
        student3.addSubject(subject3);

        entityManager.persist(teacher1);
        entityManager.persist(teacher2);

        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
    }
}
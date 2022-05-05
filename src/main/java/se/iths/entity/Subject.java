package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String points;
    private LocalDate createdAt;
    @ManyToOne()
    private Teacher teacher; //many subjects to one teacher

    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>(); //many subjects to many students

    public Subject() {}

    public Subject(String name, String points) {
        this.name = name;
        this.points = points;
    }
    //  Denna metod körs innan objektet skrivs till DB
    @PrePersist
    public void getCurrentDate() {
        setCreatedAt(LocalDate.now());
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.getSubjects().add(this);
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }
}

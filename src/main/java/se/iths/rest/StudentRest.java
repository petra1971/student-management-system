package se.iths.rest;

import se.iths.entity.Student;
import se.iths.exception.ExceptionMessage;
import se.iths.exception.SaveStudentException;
import se.iths.service.StudentService;
import se.iths.utils.JsonConverter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Path("")
    @POST
    public Response createStudent(Student student) {
        if (student.getFirstName() == null || student.getLastName() == null || student.getEmail() == null) {
            throw new SaveStudentException(Response.status(Response.Status.BAD_REQUEST)
//                JsonConverter-lösningen behövs ej, görs automatiskt av ".entity(new ExceptionMessage(400, "BAD_REQUEST", "Required data is missing. Student not created")"
//                String message = JsonConverter.convertExceptionToJson(new ExceptionMessage(400, "BAD_REQUEST", "Required data is missing. Student not created"));
                    .entity(new ExceptionMessage(400, "BAD_REQUEST", "First name, last name or email is missing. Student not created"))
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }
        Student studentResult = studentService.createStudent(student);
        return Response.ok(studentResult).build();
    }

    @Path("")
    @GET
    public Response getStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ExceptionMessage(404, "NOT_FOUND", "No saved students"))
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }
        return Response.ok(students).build();
    }

    @Path("{id}")
    @GET
    public Response getStudentById(@PathParam("id") Long id) {
        Student foundStudent = studentService.getStudentById(id);
        if (foundStudent == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ExceptionMessage(404, "NOT_FOUND", "Student with "  + id + " was not found"))
                    .type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundStudent).build();
    }

    @Path("getbylastname")
    @GET
    public Response getStudentByLastName(@QueryParam("lastName") String lastName) {
        List<Student> foundStudents = studentService.getStudentsByLastName(lastName);
        if (foundStudents.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ExceptionMessage(404, "NOT_FOUND", "No students with last name " + lastName + " found."))
                    .type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundStudents).build();
    }

    @Path("")
    @PUT
    public Response updateStudent(Student student) {
        if (student.getId() == null || student.getFirstName() == null || student.getLastName() == null || student.getEmail() == null) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ExceptionMessage(400, "BAD_REQUEST", "Id is missing. Student not updated"))
                    .type(MediaType.APPLICATION_JSON).build());
        }
        if (studentService.getStudentById(student.getId()) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ExceptionMessage(404, "NOT_FOUND", "Student with "  + student.getId() + " was not found"))
                    .type(MediaType.APPLICATION_JSON).build());
        }
        Student updatedStudent = studentService.updateStudent(student);
        return Response.ok(updatedStudent).build();
    }

    @Path("{id}")
    @PATCH
    public Response modifyStudent(@PathParam("id") Long id, Student student) {
        if (getStudentById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ExceptionMessage(404, "NOT_FOUND", "Student with "  + student.getId() + " was not found"))
                    .type(MediaType.APPLICATION_JSON).build());
        }
        Student modifiedStudent = studentService.modifyStudent(id, student);
        return Response.ok(modifiedStudent).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        Student studentToRemove = studentService.getStudentById(id);
        if (studentToRemove == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ExceptionMessage(404, "NOT_FOUND", "Student with ID " + id + " not found. No student removed"))
                    .type(MediaType.APPLICATION_JSON).build());
        }
        studentService.deleteStudent(id);
        return Response.ok().build();
    }
}

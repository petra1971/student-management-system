package se.iths.rest;

import se.iths.entity.Student;
import se.iths.exception.ExceptionMessage;
import se.iths.service.StudentService;
import se.iths.utils.JsonConverter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.http.HttpResponse;
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
        Student studentResult = studentService.createStudent(student);
        return Response.ok(studentResult).build();
    }

    @Path("")
    @GET
    public Response getStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(JsonConverter.convertStringToJson("No students in database"))
                    .type(MediaType.TEXT_PLAIN_TYPE)
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
                    .entity("Student with Id " + id + " was not found in database.")
                    .type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        return Response.ok(foundStudent).build();
    }

    @Path("getbylastname")
    @GET
    public Response getStudentByLastName(@QueryParam("lastName") String lastName) {
        List<Student> foundStudents = studentService.getStudentsByLastName(lastName);
        if (foundStudents.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("No students with last name " + lastName + " found in database.")
                    .type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        return Response.ok(foundStudents).build();
    }

    @Path("")
    @PUT
    public Response updateStudent(Student student) {
        Student updatedStudent = studentService.updateStudent(student);
        return Response.ok(updatedStudent).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        studentService.deleteStudent(id);
        return Response.ok().build();
    }
}

package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

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
        return Response.ok(students).build();
    }

//    @Path("{id}")
//    @GET
//    public Response getStudentById(@PathParam("id") Long id) {
//        Student student = studentService.getStudentById(id);
//        return Response.ok(student).build();
//    }
//
//    @Path("{getbylastname}")
//    @GET
//    public Response getStudentByLastName(@QueryParam("lastName") String category) {
//        List<Student> students = studentService.getByLastName(lastName);
//        return Response.ok(students).build();
//    }
//
//    @Path("")
//    @PUT
//    public Response updateStudent(Student student) {
//        Student updatedStudent = studentService.updateStudent(student);
//        return Response.ok(updatedStudent).build();
//    }
//
//    @Path("{id}")
//    @DELETE
//    public Response deleteStudent(@PathParam("id") Long id) {
//        studentService.deleteStudent(id);
//        return Response.ok().build();
//    }
}

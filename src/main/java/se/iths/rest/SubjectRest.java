package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.exception.ExceptionMessage;
import se.iths.service.StudentService;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @Path("{id}") //    Det ska finnas möjlighet att givet ett ämne få ut information om deltagande studenter, samt vem som är lärare.
    @GET
    public Response getSubjectById(@PathParam("id") Long id) {
        Subject foundSubject = subjectService.getSubjectById(id);
        if (foundSubject == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ExceptionMessage(404, "NOT_FOUND", "Subject with "  + id + " was not found"))
                    .type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundSubject).build();
    }

    @Path("")
    @GET
    public List<Subject> getSubjects() {
         return subjectService.getSubjects();
    }

//    public Response getStudents() {
//        List<Student> students = studentService.getAllStudents();
//        if (students.isEmpty()) {
//            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
//                    .entity(new ExceptionMessage(404, "NOT_FOUND", "No saved students"))
//                    .type(MediaType.APPLICATION_JSON)
//                    .build());
//        }
//        return Response.ok(students).build();
//    }
}


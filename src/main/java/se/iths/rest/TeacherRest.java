package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.exception.ExceptionMessage;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @Path("{id}")
    @GET
    public Response getTeacherById(@PathParam("id") Long id) {
        Teacher foundTeacher = teacherService.getTeacherById(id);
        if (foundTeacher == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ExceptionMessage(404, "NOT_FOUND", "Teacher with "  + id + " was not found"))
                    .type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundTeacher).build();
    }
}

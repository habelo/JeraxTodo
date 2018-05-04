package se.svit.JeraxTodo.resource;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import se.svit.JeraxTodo.repository.data.User;
import se.svit.JeraxTodo.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.*;


@Component
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class UserResource {

    private final UserService service;

    public UserResource(UserService service) {
        this.service = service;
    }

    @GET
    public Response getUsers(){
        return Response.ok(service.getAllUsers()).build();
    }

    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") Long id){
        User user = service.getUserById(id);
        if(id==null||user==null){
            return Response.status(NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }


    @POST
    public Response postUser(User user){
        User result = service.createUser(user);
        return Response.status(CREATED).header("Location", "users/"+result.getId()).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long id){
        User result = service.getUserById(id);
        if(result==null||id==null)
            Response.status(NOT_FOUND).build();
        service.deleteUser(id);
        return Response.status(NO_CONTENT).build();
    }

//    @PUT
//    @Path("{id}")
//    public Response updateUser(@PathParam("id") Long id, User user){
//        if()
//            return Response.status(NOT_FOUND).build();
//        service.updateUser(id);
//        return Response.status(NO_CONTENT).build();
//    }


}

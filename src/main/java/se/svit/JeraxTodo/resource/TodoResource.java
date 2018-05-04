package se.svit.JeraxTodo.resource;

import org.springframework.stereotype.Component;
import se.svit.JeraxTodo.repository.data.Todo;
import se.svit.JeraxTodo.repository.data.User;
import se.svit.JeraxTodo.service.TodoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.Response.Status.*;

@Component
@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class TodoResource {

    private final TodoService service;

    public TodoResource(TodoService service) {
        this.service = service;
    }

    @GET
    public Response getTodos(){
        return Response.ok(service.getTodos()).build();
    }

//    @GET
//    @Path("{id}")
//    public Response getTodo(@PathParam("id") Long id){
//        Todo todo = service.getTodoById(id);
//        if(id==null||todo==null)
//            return Response.status(NOT_FOUND).build();
//        return Response.ok(todo).build();
//    }

    @GET
    @Path("{userID}")
    public Response getParamTodos(@PathParam("userID") Long userID,
                                  @QueryParam("todoID")@DefaultValue("0") Long todoID,
                                  @QueryParam("todoPriority")@DefaultValue("0") int todoPriority){
        System.out.println("userID: "+userID+" todoID: "+todoID+" prio: "+todoPriority);
        List<Todo> todos;
        if(todoID>0&&todoPriority==0) {
        todos = service.getTodosByUserId(userID);
        return Response.ok(todos).build();
        }
        else if(todoID>0&&todoPriority>0){
            todos = service.getSpecificTodoByPriority(userID, todoPriority);
            return Response.ok(todos).build();
        }
        else if(todoPriority>0){
            todos = service.getTodoByPriority(todoPriority);
            return Response.ok(todos).build();
        }
        Todo todo = service.getTodoById(userID);
        return Response.ok(todo).build();

    }

    @PUT
    @Path("{userID}")
    public Response putTodo(@PathParam("userID") Long userID,
                            @QueryParam("todoID")@DefaultValue("0") Long todoID){
        System.out.println("hej userID"+userID+" todoID "+todoID);
        if(userID==0||todoID==0)
            return Response.status(BAD_REQUEST).build();
        Todo todo = service.setTodoForUser(userID, todoID);
        if(todo==null)
            return Response.status(BAD_REQUEST).build();
        return Response.status(NO_CONTENT).build();
    }

    @POST
    public Response postTodo(Todo todo){
        Todo result = service.createTodo(todo);
        return Response.status(CREATED).header("Location", "todos/"+result.getId()).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteTodo(@PathParam("id") Long id){
        Todo todo = service.getTodoById(id);
        if(id==null||todo==null)
            return Response.status(NOT_FOUND).build();
        service.deleteTodo(id);
        return Response.status(NO_CONTENT).build();
    }
}

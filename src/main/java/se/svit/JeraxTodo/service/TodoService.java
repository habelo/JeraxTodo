package se.svit.JeraxTodo.service;

import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.stereotype.Service;
import se.svit.JeraxTodo.repository.TodoRepository;
import se.svit.JeraxTodo.repository.UserRepository;
import se.svit.JeraxTodo.repository.data.Todo;
import se.svit.JeraxTodo.repository.data.User;

import java.util.List;

@Service
public final class TodoService {

    private final TodoRepository repository;
    private final UserRepository userRepository;

    //private här?
    public TodoService(TodoRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<Todo> getTodos(){
        return Lists.newArrayList(repository.findAll());
    }

    public Todo getTodoById(Long id){
//        return repository.findById(id).orElse(null);
       return repository.findById(id).orElseThrow(()-> new InvalidServiceException("Could not find todo by ID: "+id));
    }

    public Todo createTodo(Todo todo){
        return repository.save(todo);
    }

    public void deleteTodo(Long id){
        repository.deleteById(id);
    }

    public List<Todo> getTodosByUserId(Long id){
        return repository.findAllByUserId(id);
    }


    public Todo setTodoForUser(Long userID, Long todoID) {
        User user = userRepository.findById(userID).orElse(null);
        Todo todo = repository.findById(todoID).orElse(null);
        if(todo==null||user==null)
            return null;
        todo.setUser(user);
        user.addTodo(todo);
        repository.save(todo);
        userRepository.save(user);
        return todo;
    }

    public List<Todo> getSpecificTodoByPriority(Long userID, int todoPriority) {
        return repository.findAllByPriorityAndUserId(todoPriority, userID);
    }

    public List<Todo> getTodoByPriority(int todoPriority){
        return repository.findAllByPriority(todoPriority);
    }
}

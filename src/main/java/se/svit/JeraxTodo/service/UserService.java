package se.svit.JeraxTodo.service;


import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.stereotype.Service;
import se.svit.JeraxTodo.repository.UserRepository;
import se.svit.JeraxTodo.repository.data.User;

import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
public final class UserService {

    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public List<User> getAllUsers(){
        return Lists.newArrayList(repository.findAll());
//        return repository.getAll().sorted().collect(toList());
    }

    public User getUserById(Long id){
//        return repository.findById(id).orElse(null);
        return repository.findById(id).orElseThrow(()-> new InvalidServiceException("Could not find user by ID: "+id));
    }

    public User createUser(User user){
        return repository.save(user);
    }

    public void deleteUser(Long id){
        repository.deleteById(id);
    }

    public User updateUser(Long id){
        User user = repository.findById(id).orElseThrow(()-> new InvalidServiceException("Could not find user by ID: "+id));
        System.out.println(user);
        return repository.save(user);
    }
}

package se.svit.JeraxTodo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import se.svit.JeraxTodo.repository.data.User;

import java.util.stream.Stream;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {


}

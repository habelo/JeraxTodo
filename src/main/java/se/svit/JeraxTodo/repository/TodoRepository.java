package se.svit.JeraxTodo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import se.svit.JeraxTodo.repository.data.Todo;

import java.util.List;

public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {

    List<Todo> findAllByUserId(Long userId);
    List<Todo> findAllByPriorityAndUserId(Integer priority, Long userId);
    List<Todo> findAllByPriority(Integer priority);
}

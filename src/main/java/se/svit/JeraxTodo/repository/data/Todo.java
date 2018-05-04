package se.svit.JeraxTodo.repository.data;

import javax.persistence.*;

@Entity
public final class Todo {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Integer priority;
    @ManyToOne
    private User user;

    protected Todo() {
    }

    public Todo(String content, Integer priority) {
        this.content = content;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public User setUser(User user){
        this.user=user;
        return user;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", priority=" + priority +
                ", user=" + user +
                '}';
    }
}

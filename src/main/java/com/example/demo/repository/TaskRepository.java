package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TaskRepository extends JpaRepository<Task, Long> {
    //@Query("select u from User u where u.Login = ?1 and u.Password = ?2")
/*    User findByLoginAndPassword(String Login,String Password);

    User findByLogin(String login);*/

}

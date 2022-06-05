package com.example.demo.repository;

import com.example.demo.model.Task;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TaskRepository extends JpaRepository<Task, Long> {
    //@Query("select u from User u where u.Login = ?1 and u.Password = ?2")
/*    User findByLoginAndPassword(String Login,String Password);

    User findByLogin(String login);*/

    @Query(value="select t.* from public.task t where t.task_id = :id", nativeQuery=true)
    Task getTaskByTaskId(Long id);

    List<Task> getTasksByStatusName(String statusName);
}

/*select s.*, pj.*, pf.*, u.*, a.*, t.*
from public.status s, public.project pj, public.profile pf, public.user u, public.attachment a, public.task t, public.project_staff ps, public.attachment_list al
           where
               u.user_id = pf.user_id
             AND pf.profile_id=ps.profile_id
             AND ps.project_id=pj.project_id
             AND pj.project_id=t.project_id
             AND pj.status_id=s.status_id
             AND t.status_id=s.status_id
             AND t.task_id=al.task_id
             AND al.attachment_id=a.attachment_id
             AND al.project_id=pj.project_id
             AND t.task_id = :id*/
package com.example.demo.repository;
import com.example.demo.model.Profile;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository extends JpaRepository <User, Long>{
    //@Query("select u from User u where u.Login = ?1 and u.Password = ?2")
/*    User findByLoginAndPassword(String Login,String Password);

    User findByLogin(String login);*/

    @Query(value="select u.* from public.user u, public.profile pf, public.task t, public.project_staff ps, public.project pj  where u.user_id = pf.user_id AND pf.profile_id=ps.profile_id AND ps.project_id=pj.project_id AND pj.project_id=t.project_id AND t.task_id = :id", nativeQuery=true)
    User getUserByTaskId(Long id);

}




package com.example.demo.repository;

import com.example.demo.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value="select p.* from public.comment c, public.profile p where c.author_id = p.profile_id AND c.task_id = :id", nativeQuery=true)
    List<Profile> getAllAuthorsOfTheTaskList(Long id);

    @Query(value="select pf.* from public.profile pf, public.task t, public.project_staff ps, public.project pj  where pf.profile_id=ps.profile_id AND ps.project_id=pj.project_id AND pj.project_id=t.project_id AND t.task_id = :id", nativeQuery=true)
    Profile getProfileByTaskId(Long id);

}



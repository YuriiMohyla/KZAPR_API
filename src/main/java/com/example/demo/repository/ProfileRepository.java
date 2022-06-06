package com.example.demo.repository;

import com.example.demo.model.Profile;
import com.example.demo.model.Project;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query(value="select a.email from public.user a,public.profile b where a.user_id = b.user_id AND b.profile_id = :id", nativeQuery=true)
    String getUserEmailByProfileId(Long id);

    @Query(value="select p.*\n" +
            "from public.profile p,public.project_staff ps, public.project pr, public.roles r, public.profile_roles pf\n" +
            "where ps.project_id = pr.project_id AND ps.profile_id = p.profile_id AND pf.role_id = r.role_id AND r.name = 'pm' AND pr.project_id = :id", nativeQuery=true)
    List<Profile> getProfileIdByProjectIdForPm(Long id);

    @Query(value="select p.* from public.comment c, public.profile p where c.author_id = p.profile_id AND c.task_id = :id", nativeQuery=true)
    List<Profile> getAllAuthorsOfTheTaskList(Long id);

    @Query(value="select pf.* from public.profile pf, public.task t, public.project_staff ps, public.project pj  where pf.profile_id=ps.profile_id AND ps.project_id=pj.project_id AND pj.project_id=t.project_id AND t.task_id = :id", nativeQuery=true)
    Profile getProfileByTaskId(Long id);

    @Query(value = "with recursive project_manager as (" +
            "    select ps.profile_id, ps.projectstaff_id, ps.chief_id" +
            "    from public.profile_roles pr" +
            "             join public.project_staff ps on pr.profile_role_id = ps.profile_id" +
            "    where pr.profile_id = ?1" +
            "    union" +
            "    select pr.profile_role_id, ps.projectstaff_id, ps.chief_id" +
            "    from public.project_staff ps" +
            "    join project_manager pm on pm.chief_id = ps.projectstaff_id" +
            "    join public.profile_roles pr on pr.profile_role_id = ps.profile_id" +
            ")" +
            "select p.* from public.profile_roles pr" +
            "         join public.profile p on pr.profile_id = p.profile_id " +
            "where pr.profile_role_id = (select project_manager.profile_id from project_manager order by projectstaff_id limit 1)", nativeQuery = true)
    Profile getProjectManagerByProfileId(long profileId);
}



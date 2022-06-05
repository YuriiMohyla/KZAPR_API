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
}

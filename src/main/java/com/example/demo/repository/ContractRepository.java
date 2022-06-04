package com.example.demo.repository;

import com.example.demo.model.Contract;
import com.example.demo.model.Project;
import com.example.demo.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query(value="select a.* from public.contract a,public.status s where a.status_id = s.status_id AND s.name != 'Closed'", nativeQuery=true)
    List<Contract> getUnclosedContractList();


}

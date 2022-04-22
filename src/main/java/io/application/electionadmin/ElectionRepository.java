package io.application.electionadmin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectionRepository extends CrudRepository<Election,Integer> {


    List<Election> findByElectionname(String ename);

}

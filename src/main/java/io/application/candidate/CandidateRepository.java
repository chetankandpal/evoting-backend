package io.application.candidate;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CandidateRepository extends CrudRepository<Candidate,String> {

    @Transactional
    @Modifying
    @Query("UPDATE Candidate c Set c.votes=c.votes+1 where c.cId= :cid")
    void updateVotes(@Param(value="cid")String cid);
}

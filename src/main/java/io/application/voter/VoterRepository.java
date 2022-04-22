package io.application.voter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepository extends CrudRepository<Voter,String> {

    Voter findByVoterid(String v);

}

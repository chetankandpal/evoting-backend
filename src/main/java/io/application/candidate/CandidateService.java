package io.application.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public void addCandidate(Candidate candidate){

        candidateRepository.save(candidate);
    }

    public Iterable<Candidate> getCandidateDetails(){

        return candidateRepository.findAll();
    }

    public Long getCandidateCount(){
        return candidateRepository.count();
    }

    public void addVote(String cid){
        candidateRepository.updateVotes(cid);
    }
}

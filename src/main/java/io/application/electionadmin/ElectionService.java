package io.application.electionadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectionService {

    //Create election
    //Register candidates
    //Register voters

    @Autowired
    private ElectionRepository electionRepository;

    public void addElection(Election election){


        electionRepository.save(election);
    }

    public Iterable<Election> getElectionDetails(){

        return electionRepository.findAll();
    }

    public List<Election> getElection(String ename){

        return electionRepository.findByElectionname(ename);
    }


}

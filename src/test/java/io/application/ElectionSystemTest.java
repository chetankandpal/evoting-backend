package io.application;
import io.application.candidate.Candidate;
import io.application.candidate.CandidateRepository;
import io.application.electionadmin.Election;
import io.application.electionadmin.ElectionRepository;
import io.application.voter.Voter;
import io.application.voter.VoterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ElectionSystemTest {
    @Autowired
    VoterRepository vRepo;

    @Autowired
    CandidateRepository cRepo;

    @Autowired
    ElectionRepository eRepo;
//Testing Voter
    @Test
    public void testInsertVoter(){
        Voter v = new Voter("MT2021119","Sarthak Dandriyal","Dehradun","sarthakdandriyal3199@gmail.com","0","ROLE_USER");
        vRepo.save(v);
        v = new Voter("MT2021035","Chetan Kandpal","Haldwani","chetankandpal94@gmail.com","0","ROLE_USER");
        vRepo.save(v);
        Assertions.assertNotNull(vRepo.findByVoterid("MT2021119"));
        Assertions.assertNull(vRepo.findByVoterid("1"));
    }
    @Test
    public void getVoterName(){
        Assertions.assertEquals("Sarthak Dandriyal",vRepo.findByVoterid("MT2021119").getName());
    }
    @Test
    public void getVoterAddress(){
        Assertions.assertEquals("Dehradun",vRepo.findByVoterid("MT2021119").getAddress());
    }
    @Test
    public void getVoterRole(){
        Assertions.assertEquals("ROLE_USER",vRepo.findByVoterid("MT2021119").getRole());
    }
    @Test
    public void getAllVoters(){
        List<Voter> voters=new ArrayList<>();
        vRepo.findAll().forEach(voters::add);
        Assertions.assertEquals(2,voters.size());
    }
//Testing Candidate
    @Test
    public void testInsertcandidate(){
        Candidate c = new Candidate("c1","Candidate1",0);
        cRepo.save(c);
        Assertions.assertNotNull(cRepo.findById("c1"));
    }
    @Test
    public void getCandidateName(){
        Candidate c = new Candidate("c2","Candidate2",0);
        cRepo.save(c);
        Assertions.assertEquals("Candidate2",cRepo.findById("c2").get().getName());
    }
    @Test
    public void getCandidateVotes(){
        Assertions.assertEquals(0,cRepo.findById("c1").get().getVotes());
    }

}

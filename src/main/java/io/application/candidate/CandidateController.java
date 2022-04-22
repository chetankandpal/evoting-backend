package io.application.candidate;

import io.application.electionadmin.Election;
import io.application.electionadmin.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
public class CandidateController {


    @Autowired
    private CandidateService candidateService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value="/candidate")
    public void addCandidate(@RequestBody Candidate candidate){
        candidateService.addCandidate(candidate);
    }


    @RequestMapping("/candidate")
    public Iterable<Candidate> getCandidateDetails(){
        return candidateService.getCandidateDetails();
    }

    @RequestMapping("/candidatecount")
    public Long getCandidateCount(){
        return  candidateService.getCandidateCount();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/addVote/{cid}")
    public void addvote(@PathVariable  String cid){
        candidateService.addVote(cid);
    }
}

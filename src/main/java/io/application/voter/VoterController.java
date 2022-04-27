package io.application.voter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
@CrossOrigin("http://localhost:3000/")
@RestController
public class VoterController {


    @Autowired
    private VoterService voterService;

    @RequestMapping(method = RequestMethod.POST,value="/voter")
    public void addVoter(@RequestBody Voter voter){
        voterService.addVoter(voter);
    }

    @RequestMapping("/voter")
    public Iterable<Voter> getVoterDetails(){
        return voterService.getVoterDetails();
    }

    @RequestMapping("/notify")
    public List<Voter> notifyCreation() throws MessagingException {
      return voterService.notifyCreate();
    }
    @RequestMapping("/publishresult")
    public List<Voter> notifyResult() throws MessagingException {
        return voterService.notifyResult();
    }

    @RequestMapping("/voted")
    public void voted(){
        voterService.voted();
    }

}

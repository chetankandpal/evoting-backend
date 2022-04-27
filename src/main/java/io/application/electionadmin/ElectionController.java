package io.application.electionadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000/")
@RestController
public class ElectionController {

    @Autowired
    private ElectionService electionService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value="/election")
    public void addElection(@RequestBody Election election){
        System.out.println("Ename "+election.getElectionname()+"STime"+election.getStime());
        electionService.addElection(election);
    }

    @RequestMapping("/election")
    public Iterable<Election> getElectionDetails(){
        return  electionService.getElectionDetails();

    }

    @RequestMapping(method = RequestMethod.GET,value = "/election/{ename}")
    public List<Election> getElection(@PathVariable String ename){
        return  electionService.getElection(ename);
    }


}
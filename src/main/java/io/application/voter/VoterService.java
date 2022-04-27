package io.application.voter;

import io.application.electionadmin.Election;
import io.application.electionadmin.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class VoterService implements UserDetailsService {

    @Autowired
    private VoterRepository voterRepository;
    @Autowired
    public EmailService emailService;
    @Autowired
    public ElectionRepository eRepo;

    String remName;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("ADMIN")){
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
            UserDetails userDetails = (UserDetails) new User("ADMIN",
                    "sarthakdandriyal3199@gmail.com", Arrays.asList(authority));
            return userDetails;
        }
        else {
            Voter voter = voterRepository.findByVoterid(username);
            remName=username;
            GrantedAuthority authority = new SimpleGrantedAuthority(voter.getRole());
            UserDetails userDetails = (UserDetails) new User(voter.getVoterid(),
                    voter.getEmail(), Arrays.asList(authority));

            return userDetails;
        }
    }
    public Voter getUser(String uname){
        Optional<Voter> t = voterRepository.findById(uname);
        Voter value = t.orElse(null);
        return value;
    }

    public void addVoter(Voter voter){
        voter.setVoted("0");
        voter.setRole("ROLE_USER");
        voterRepository.save(voter);
    }

    public List<Voter> notifyCreate() throws MessagingException {
        List<Voter> voters=new ArrayList<>();
        voterRepository.findAll().forEach(voters::add);
        for(int i=0;i<voters.size();i++){
            Voter v=voters.get(i);
            String Otp="Hello "+v.getName()+" you have been added as a voter.Please go to http://localhost:8083/ to vote";
            emailService.sendOtpMessage(v.getEmail(), "Election Created", Otp);
            System.out.println(v.getEmail());
        }
        return voters;
    }

    public List<Voter> notifyResult() throws MessagingException {
        List<Voter> voters=new ArrayList<>();
        voterRepository.findAll().forEach(voters::add);
        for(int i=0;i<voters.size();i++){
            Voter v=voters.get(i);
            String Otp="Hello "+v.getName()+" the election result is out.Please go to http://localhost:8083/result to view";
            emailService.sendOtpMessage(v.getEmail(), "Result out", Otp);
            System.out.println(v.getEmail());
        }
        return voters;
    }

    public Iterable<Voter> getVoterDetails(){
        return voterRepository.findAll();
    }

    public void voted(){
        System.out.println(remName);
        Optional<Voter> t = voterRepository.findById(remName);
        Voter voter = t.orElse(null);
        voter.setVoted("1");
        voterRepository.save(voter);


    }

}

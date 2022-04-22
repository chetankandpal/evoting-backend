package io.application.voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Optional;


@Controller
public class OTPController {

@Autowired
private VoterService vService;

@Autowired
private VoterRepository voterRepository;

@Autowired
public OTPService otpService;

@Autowired
public EmailService emailService;

@GetMapping("/generateOtp")
public String generateOTP() throws MessagingException{

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		System.out.println(username);
		int otp = otpService.generateOTP(username);
		if(username.equals("ADMIN")){
			String Otp = ""+otp;
			emailService.sendOtpMessage("sarthakdandriyal3199@gmail.com", "ElectionPortal _admin access OTP", Otp);
			return "adminotppage";
		}
		else {
			Voter voter = vService.getUser(username);
			//
			String vs = voter.getVoted();
			//
			if(vs.equals("0")) {
				System.out.println(voter.getName());
				String Otp = "Dear "+voter.getName()+"\nOTP is "+ otp;
				emailService.sendOtpMessage(voter.getEmail(), "ElectionPortal _voter access otp", Otp);
				return "voterotppage";
			}
			else{
				return "alreadyvoted";
			}
		}
}

@RequestMapping(value ="/validateOtp", method = RequestMethod.GET)
public @ResponseBody String validateOtp(@RequestParam("otpnum") int otpnum){
	
		final String SUCCESS = "Entered Otp is valid";
		final String FAIL = "Entered Otp is NOT valid. Please Retry!";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		//Validate the Otp 
		if(otpnum >= 0){

		  int serverOtp = otpService.getOtp(username);
		    if(serverOtp > 0){
		      if(otpnum == serverOtp){
		          otpService.clearOTP(username);
		
                  return ("Entered Otp is valid");
                } 
		        else {
                    return FAIL;
                   }
               }else {
              return FAIL;
               }
             }else {
                return FAIL;
         }
      }
}

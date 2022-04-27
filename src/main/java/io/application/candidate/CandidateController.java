package io.application.candidate;

import io.application.electionadmin.Election;
import io.application.electionadmin.ElectionRepository;
import io.application.electionadmin.ElectionService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
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

    @GetMapping("/result")
    public ResponseEntity<byte[]> getReport() {
        try {
            String filePath = "./src/main/resources/CandidateReport.jrxml";
            List<Candidate> list = (List<Candidate>) candidateService.getCandidateDetails();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            JRBeanCollectionDataSource chartDataSource = new JRBeanCollectionDataSource(list);
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("tableData", dataSource);
            JasperReport report = JasperCompileManager.compileReport(filePath);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, chartDataSource);
            byte[] byteArray = JasperExportManager.exportReportToPdf(print);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "Election Report.pdf");
            return new ResponseEntity<byte[]>(byteArray, headers, HttpStatus.OK);
        } catch(Exception e) {
            System.out.print("Exception in creating report.");
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

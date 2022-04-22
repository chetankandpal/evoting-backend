package io.application.electionadmin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Election {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer eId;
    private String electionname;
    private Date stime;
    private Date etime;
    private Integer voterscount;

    public Election(){

    }

    public Election(String election_name, Integer eId, Date stime, Date etime, Integer voters_count) {

        super();
        this.electionname = election_name;
        this.eId = eId;
        this.stime = stime;
        this.etime = etime;
        this.voterscount = voters_count;
    }

    public String getElectionname() {
        return electionname;
    }

    public void setElectionname(String election_name) {
        this.electionname = election_name;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public long getStime() {
        return stime.getTime();
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public long getEtime() {
        return etime.getTime();
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public Integer getVoterscount() {
        return voterscount;
    }

    public void setVoterscount(Integer voters_count) {
        this.voterscount = voters_count;
    }
}

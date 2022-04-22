package io.application.candidate;

import javax.persistence.*;

@Entity
public class Candidate {

    @Id
    private String cId;
    private String name;
    @Column(name="votes")
    private Integer votes=0;


    public Candidate(){


    }

    public Candidate(String cId, String name, Integer votes) {
        super();
        this.cId = cId;
        this.name = name;
        this.votes = votes;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}

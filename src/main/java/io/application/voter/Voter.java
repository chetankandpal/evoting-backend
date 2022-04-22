package io.application.voter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Blob;

@Entity
public class Voter {

    @Id
    @Column
    private String voterid;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String email;
    @Column
    private String voted;
    @Column
    private String role;

    public Voter(){

    }

    public Voter(String voterid, String name, String address, String email, String voted, String role) {
        this.voterid = voterid;
        this.name = name;
        this.address = address;
        this.email = email;
        this.voted = voted;
        this.role = role;
    }

    public String getVoterid() {
        return voterid;
    }

    public void setVoterid(String voterid) {
        this.voterid = voterid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVoted() {
        return voted;
    }

    public void setVoted(String voted) {
        this.voted = voted;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role="ROLE_USER";
    }

}

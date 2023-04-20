package com.zchpc.billing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "charges")
public class Charges {
    @Id
    @Column(name = "id")
    private Long vote_id;

    @Column(name = "description")
    private String description;

    @Column(name ="amount")
    private String amount;

    public Charges(Long vote_id, String description, String amount) {
        this.vote_id = vote_id;
        this.description = description;
        this.amount = amount;
    }

    public Charges() {
    }

    public Long getVote_id() {
        return vote_id;
    }

    public void setVote_id(Long vote_id) {
        this.vote_id = vote_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Charges{" +
                "vote_id=" + vote_id +
                ", description='" + description + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}

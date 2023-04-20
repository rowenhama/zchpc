package com.zchpc.billing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Accounts {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "account_number",nullable = true)
    private String account_number;

    @Column(name = "stand_number",nullable = true)
    private String stand_number;

    @Column(name = "house_number",nullable = true)
    private String house_number;

    @Column(name = "stand_size",nullable = true)
    private String stand_size;

    @Column(name = "establishment_year",nullable = true)
    private String establishment_year;

    @Column(name = "property_value",nullable = true)
    private String property_value;

    @Column(name = "ownership_form",nullable = true)
    private String ownership_form;

    @Column(name = "meter_number",nullable = true)
    private String meter_number;

    @Column(name = "sewer",nullable = true)
    private String sewer;

    @Column(name = "water",nullable = true)
    private String water;

    @Column(name = "meter_status",nullable = true)
    private String meter_status;

    public Accounts() {
    }

    public Accounts(Long id, String account_number, String stand_number,
                    String house_number, String stand_size, String establishment_year,
                    String property_value, String ownership_form, String meter_number,
                    String sewer, String water, String meter_status) {
        this.id = id;
        this.account_number = account_number;
        this.stand_number = stand_number;
        this.house_number = house_number;
        this.stand_size = stand_size;
        this.establishment_year = establishment_year;
        this.property_value = property_value;
        this.ownership_form = ownership_form;
        this.meter_number = meter_number;
        this.sewer = sewer;
        this.water = water;
        this.meter_status = meter_status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getStand_number() {
        return stand_number;
    }

    public void setStand_number(String stand_number) {
        this.stand_number = stand_number;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public String getStand_size() {
        return stand_size;
    }

    public void setStand_size(String stand_size) {
        this.stand_size = stand_size;
    }

    public String getEstablishment_year() {
        return establishment_year;
    }

    public void setEstablishment_year(String establishment_year) {
        this.establishment_year = establishment_year;
    }

    public String getProperty_value() {
        return property_value;
    }

    public void setProperty_value(String property_value) {
        this.property_value = property_value;
    }

    public String getOwnership_form() {
        return ownership_form;
    }

    public void setOwnership_form(String ownership_form) {
        this.ownership_form = ownership_form;
    }

    public String getMeter_number() {
        return meter_number;
    }

    public void setMeter_number(String meter_number) {
        this.meter_number = meter_number;
    }

    public String getSewer() {
        return sewer;
    }

    public void setSewer(String sewer) {
        this.sewer = sewer;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getMeter_status() {
        return meter_status;
    }

    public void setMeter_status(String meter_status) {
        this.meter_status = meter_status;
    }

    @Override
    public String
    toString() {
        return "Accounts{" +
                "id=" + id +
                ", account_number='" + account_number + '\'' +
                ", stand_number='" + stand_number + '\'' +
                ", house_number='" + house_number + '\'' +
                ", stand_size='" + stand_size + '\'' +
                ", establishment_year='" + establishment_year + '\'' +
                ", property_value='" + property_value + '\'' +
                ", ownership_form='" + ownership_form + '\'' +
                ", meter_number='" + meter_number + '\'' +
                ", sewer='" + sewer + '\'' +
                ", water='" + water + '\'' +
                ", meter_status='" + meter_status + '\'' +
                '}';
    }
}

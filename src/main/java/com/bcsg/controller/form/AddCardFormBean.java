package com.bcsg.controller.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * This class contains data of the form "adding a new card".
 */
public class AddCardFormBean {

    @NotEmpty
    @Size(max = 64)
    private String bank;

    @NotEmpty
    @Pattern(regexp="^[0-9]{4}-?[0-9]{4}-?[0-9]{4}-?[0-9]{1,4}$", message = "invalid")
    private String number;

    @DateTimeFormat(pattern = "MM/yyyy")
    @NotNull
    @Future
    private Date expiry;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }
}

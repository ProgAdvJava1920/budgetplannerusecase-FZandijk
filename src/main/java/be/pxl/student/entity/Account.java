package be.pxl.student.entity;

import javax.persistence.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Account {

    @Id
    private String IBAN;
    private String name;
    @OneToMany(mappedBy = "account")
    private List<Payment> payments = new ArrayList<>();

    public Account() {}


    public String getIBAN() {
        return IBAN;
    }
    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Payment> getPayments() {
        return payments;
    }
    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "Account{" +
                "IBAN='" + IBAN + '\'' +
                ", name='" + name + '\'' +
                ", payments=[" + payments.stream().map(Payment::toString).collect(Collectors.joining(",")) + "]}";
    }
}

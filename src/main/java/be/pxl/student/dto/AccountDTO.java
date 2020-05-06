package be.pxl.student.dto;

import java.util.ArrayList;
import java.util.List;

public class AccountDTO {
    private String iban;
    private String name;
    private List<PaymentDTO> payments = new ArrayList<>();

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PaymentDTO> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentDTO> payments) {
        this.payments = payments;
    }
}

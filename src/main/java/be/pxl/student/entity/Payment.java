package be.pxl.student.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private LocalDateTime date;
    private float amount;
    private String currency;
    private String detail;
    @ManyToOne
    private Account account;

    public Payment() {}

    public Payment(LocalDateTime date, float amount, String currency, String detail) {
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.detail = detail;
    }

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "{" +
                "date=" + date +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}

package be.pxl.student.services;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;

import java.util.List;

public interface IAccountService {
    List<Payment> getPaymentsOfAccountName(String accountName);
    Account getAccountByName(String accountName);
}

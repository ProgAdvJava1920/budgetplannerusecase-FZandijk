package be.pxl.student.services;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;

import javax.persistence.*;
import java.util.List;

public class AccountService implements IAccountService {
    private EntityManager entityManager;

    public AccountService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Payment> getPaymentsOfAccountName(String accountName) {
        TypedQuery<Payment> getPaymentsByAccountNameQuery = entityManager.createQuery("SELECT p FROM Payment p WHERE p.account_IBAN = :iban", Payment.class);
        Account account = getAccountByName(accountName);
        getPaymentsByAccountNameQuery.setParameter("iban", account.getIBAN());
        return getPaymentsByAccountNameQuery.getResultList();
    }

    @Override
    public Account getAccountByName(String accountName) {
        TypedQuery<Account> getAccountByNameQuery = entityManager.createQuery("SELECT a FROM Account a WHERE a.name = :name", Account.class);
        getAccountByNameQuery.setParameter("name", accountName);
        return getAccountByNameQuery.getSingleResult();
    }


}

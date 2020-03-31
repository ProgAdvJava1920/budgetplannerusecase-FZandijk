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
        return null;
    }

    @Override
    public Account getAccountByName(String accountName) {
        TypedQuery<Account> getAccountByNameQuery = entityManager.createQuery("SELECT a FROM Account a WHERE a.name = :name", Account.class);
        getAccountByNameQuery.setParameter("name", accountName);
        Account result = getAccountByNameQuery.getSingleResult();
        return result;
    }


}

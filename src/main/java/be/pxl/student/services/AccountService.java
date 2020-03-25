package be.pxl.student.services;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;

import javax.persistence.*;
import java.util.List;

public class AccountService implements IAccountService {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    private void Setup() {
        entityManagerFactory = Persistence.createEntityManagerFactory("budgetplanner_pu");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    private void Finish() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Override
    public List<Payment> getPaymentsOfAccountName(String accountName) {
        return null;
    }

    @Override
    public Account getAccountByName(String accountName) {
        Setup();

        TypedQuery<Account> getAccountByNameQuery = entityManager.createQuery("SELECT a FROM Account a WHERE a.name = :name", Account.class);
        transaction.begin();
        getAccountByNameQuery.setParameter("name", accountName);
        Account result = getAccountByNameQuery.getSingleResult();
        transaction.commit();

        Finish();

        return result;
    }


}

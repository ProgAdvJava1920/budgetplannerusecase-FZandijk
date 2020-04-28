package be.pxl.student;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import be.pxl.student.services.AccountService;
import be.pxl.student.services.IAccountService;
import be.pxl.student.util.BudgetPlannerImporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class BudgetPlanner {

    private static final Logger LOGGER = LogManager.getLogger(BudgetPlanner.class);
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static EntityTransaction transaction;

    public static final void main(String[] args) {
//        LOGGER.info("start reading file");
//        new BudgetPlannerImporter().importCsv(Paths.get("src/main/resources/account_payments.csv"));
//        LOGGER.info("finished reading file");

//        entityManagerFactory = Persistence.createEntityManagerFactory("budgetplanner_pu");
//        entityManager = entityManagerFactory.createEntityManager();
//
//        IAccountService accountService = new AccountService(entityManager);
//        accountService.getAccountByName("Leonila");
//
//        entityManager.close();
//        entityManagerFactory.close();
    }
}

//https://logging.apache.org/log4j/2.x/manual/appenders.html#RollingFileAppender
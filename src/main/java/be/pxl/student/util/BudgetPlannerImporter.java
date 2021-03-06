package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.HashMap;
import java.util.List;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {
    private static final Logger LOGGER = LogManager.getLogger(BudgetPlannerImporter.class);
    private PathMatcher csvMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.csv");
    private AccountMapper mapper;
    private HashMap<String, Account> accounts;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public BudgetPlannerImporter() {
        mapper = new AccountMapper();
        accounts = new HashMap<>();
    }

    public void importCsv(Path path) {
        if (!csvMatcher.matches(path)) {
            LOGGER.error("Invalid file: .csv expected. Provided: {}", path);
            return;
        }
        if (!Files.exists(path)) {
            LOGGER.error("File {} does not exist.", path);
        }
        //read csv file
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = null;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                try {
                    LOGGER.debug(mapper.map(line));

                    MappedLine result = mapper.map(line);
                    Account theAccount = result.getAccount();
                    Payment thePayment = result.getPayment();
                    String iban = result.getAccount().getIBAN();
                    thePayment.setAccount(theAccount);
                    if (!accounts.containsKey(iban)) { //account not in map, add
                        theAccount.addPayment(thePayment);
                        accounts.put(iban, theAccount);
                    } else {
                        accounts.get(iban).addPayment(thePayment);
                    }
                } catch (InvalidPaymentException ex) {
                    LOGGER.error("Error while mapping line: {}", ex.getMessage());
                }
            }
            //save entries in map to db
            entityManagerFactory = Persistence.createEntityManagerFactory("budgetplanner_pu");
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            accounts.forEach((k, v) -> {
                entityManager.persist(v);
            });
            transaction.commit();

        } catch (FileNotFoundException ex) {
            LOGGER.fatal("File not found.");
        } catch (IOException ex) {
            LOGGER.fatal("Error while reading file.");
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }
}

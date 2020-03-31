package be.pxl.student.rest;

import be.pxl.student.entity.Payment;
import be.pxl.student.services.AccountService;
import be.pxl.student.services.IAccountService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("accounts")
public class AccountsRest {

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Payment> getAllPaymentsByAccountName(@PathParam("name") String accountName) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("budgetplanner_pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        IAccountService accountService = new AccountService(entityManager);
        return accountService.getPaymentsOfAccountName(accountName);
    }
}

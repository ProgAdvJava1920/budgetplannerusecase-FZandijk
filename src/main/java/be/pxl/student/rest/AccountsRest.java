package be.pxl.student.rest;

import be.pxl.student.dto.AccountDTO;
import be.pxl.student.dto.PaymentDTO;
import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import be.pxl.student.services.AccountService;
import be.pxl.student.services.IAccountService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("accounts")
public class AccountsRest {

//    @GET
//    @Path("{name}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Payment> getAllPaymentsByAccountName(@PathParam("name") String accountName) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("budgetplanner_pu");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        IAccountService accountService = new AccountService(entityManager);
//        List<Payment> result = accountService.getPaymentsOfAccountName(accountName);
//        entityManager.close();
//        entityManagerFactory.close();
//        return result;
//    }

    @GET
    @Path("{accountName}")
    @Produces(MediaType.APPLICATION_JSON)
    public AccountDTO getAccountByName(@PathParam("accountName") String accountName) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("budgetplanner_pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        IAccountService accountService = new AccountService(entityManager);
        Account result = accountService.getAccountByName(accountName);
        entityManager.close();
        entityManagerFactory.close();
        return accountToAccountDto(result);
    }

    private AccountDTO accountToAccountDto(Account result) {
        AccountDTO account = new AccountDTO();
        account.setIban(result.getIBAN());
        account.setName(result.getName());
        account.setPayments(mapPayments(result.getPayments()));
        return account;
    }
    private List<PaymentDTO> mapPayments(List<Payment> payments) {
        return payments.stream().map(p -> mapPayment(p)).collect(Collectors.toList());
    }
    private PaymentDTO mapPayment(Payment p) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(p.getAmount());
        paymentDTO.setCurrency(p.getCurrency());
        return paymentDTO;
    }
}

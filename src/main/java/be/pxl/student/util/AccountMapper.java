package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class AccountMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);
    private static final Logger LOGGER = LogManager.getLogger(AccountMapper.class);

    public MappedLine map(String validLine) throws InvalidPaymentException {
        String[] split = validLine.split(",");
        MappedLine result;

        if (split.length != 7) {
            throw new InvalidPaymentException("Incorrect number of fields in line.");
        }

        Account account = new Account();
        account.setName(split[0]);
        account.setIBAN(split[1]);
        account.setPayments(new ArrayList<>());

        Payment payment = new Payment(LocalDateTime.parse(split[3], FORMATTER), Float.parseFloat(split[4]), split[5], split[6]);

        result = new MappedLine(account, payment);
        return result;
    }
}

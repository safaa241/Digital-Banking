package ma.mundia.digitalbankapp.Dtos;


import lombok.Data;
import ma.mundia.digitalbankapp.Enums.AccountStatus;

import java.util.Date;

@Data
public class CurrentBankAccountDTO extends BankAccountDTO{
    private String id;

    private double balance;
    private Date createdAt;

    private AccountStatus status;

    private CustomerDTO customerDTO;

    private double overDraft;
}

package ma.mundia.digitalbankapp.Dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.mundia.digitalbankapp.Entities.BankAccount;
import ma.mundia.digitalbankapp.Enums.OperationType;

import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;

    private OperationType type;

    private String description;
}

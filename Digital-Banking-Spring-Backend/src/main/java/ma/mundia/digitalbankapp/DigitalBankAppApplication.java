package ma.mundia.digitalbankapp;

import ma.mundia.digitalbankapp.Dtos.BankAccountDTO;
import ma.mundia.digitalbankapp.Dtos.CurrentBankAccountDTO;
import ma.mundia.digitalbankapp.Dtos.CustomerDTO;
import ma.mundia.digitalbankapp.Dtos.SavingBankAccountDTO;
import ma.mundia.digitalbankapp.Entities.*;
import ma.mundia.digitalbankapp.Enums.AccountStatus;
import ma.mundia.digitalbankapp.Enums.OperationType;
import ma.mundia.digitalbankapp.Exceptions.BalanceNotSufficentException;
import ma.mundia.digitalbankapp.Exceptions.BankAccountNotFountException;
import ma.mundia.digitalbankapp.Exceptions.CustomerNotFoundException;
import ma.mundia.digitalbankapp.Repositories.AccountOperationRepository;
import ma.mundia.digitalbankapp.Repositories.BankAccountRepository;
import ma.mundia.digitalbankapp.Repositories.CustomerRepository;
import ma.mundia.digitalbankapp.Services.BankAccountService;
import ma.mundia.digitalbankapp.Services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBankAppApplication.class, args);
	}

	//@Bean
	CommandLineRunner start(CustomerRepository customerRepository,
							BankAccountRepository bankAccountRepository,
							AccountOperationRepository accountOperationRepository){
		return args -> {
			Stream.of("Amine", "safaa", "Mohammed").forEach(name -> {
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				customerRepository.save(customer);
			});
			
			customerRepository.findAll().forEach(c -> {
				CurrentAccount currentAccount = new CurrentAccount();
				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setBalance(Math.random()*9000);
				currentAccount.setCreatedAt(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				currentAccount.setCustomer(c);
				currentAccount.setOverDraft(9000);

				bankAccountRepository.save(currentAccount);


				SavingAccount savingAccount = new SavingAccount();
				savingAccount.setId(UUID.randomUUID().toString());
				savingAccount.setBalance(Math.random()*9000);
				savingAccount.setCreatedAt(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setCustomer(c);
				savingAccount.setInterestRate(5.5);

				bankAccountRepository.save(savingAccount);
			});

			bankAccountRepository.findAll().forEach(acc -> {
				for (int i = 0; i < 10; i++) {
					AccountOperation accountOperation = new AccountOperation();
					accountOperation.setOperationDate(new Date());
					accountOperation.setAmount(Math.random()*12000);
					accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
					accountOperation.setBankAccount(acc);
					accountOperationRepository.save(accountOperation);
				}
			});

		};
	}

	@Bean
	CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
		return args -> {
			Stream.of("Amine", "Fati", "Said", "Safaa").forEach(name -> {
				CustomerDTO customer = new CustomerDTO();
				customer.setName(name);
				customer.setEmail(name + "@gmail.com");
				bankAccountService.saveCustomer(customer);
			});
			bankAccountService.listCustomer().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random() * 90000, 9000, customer.getId());
					bankAccountService.saveSavingBankAccount(Math.random() * 120000, 5.5, customer.getId());

				} catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });

			List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();

			for (BankAccountDTO bankAccount:bankAccounts){
				for (int i = 0; i < 10; i++) {
					String accountId;
					if (bankAccount instanceof SavingBankAccountDTO){
						accountId = ((SavingBankAccountDTO) bankAccount).getId();
					}else {
						accountId = ((CurrentBankAccountDTO) bankAccount).getId();
					}
					bankAccountService.credit(accountId, 10000 + Math.random() * 120000, "credit");
					bankAccountService.debit(accountId, 1000 + Math.random() * 9000, "debit");
				}
			}
		};
	}
}

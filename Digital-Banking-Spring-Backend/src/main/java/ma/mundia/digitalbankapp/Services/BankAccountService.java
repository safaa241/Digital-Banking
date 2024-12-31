package ma.mundia.digitalbankapp.Services;

import ma.mundia.digitalbankapp.Dtos.*;
import ma.mundia.digitalbankapp.Exceptions.BalanceNotSufficentException;
import ma.mundia.digitalbankapp.Exceptions.BankAccountNotFountException;
import ma.mundia.digitalbankapp.Exceptions.CustomerNotFoundException;

import java.util.List;


public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;

    List<CustomerDTO> listCustomer();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFountException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFountException, BalanceNotSufficentException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFountException, BalanceNotSufficentException;

    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BalanceNotSufficentException, BankAccountNotFountException;

    List<BankAccountDTO> bankAccountList();

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFountException;
}

package sn.groupeisi.ebankigbackend.services;

import sn.groupeisi.ebankigbackend.dto.BankAccountDTO;
import sn.groupeisi.ebankigbackend.dto.CurrentBankAccountDTO;
import sn.groupeisi.ebankigbackend.dto.CustomerDTO;
import sn.groupeisi.ebankigbackend.dto.SavingBankAccountDTO;
import sn.groupeisi.ebankigbackend.entities.BankAccount;
import sn.groupeisi.ebankigbackend.exceptions.BalanceNotSufficientException;
import sn.groupeisi.ebankigbackend.exceptions.BankAccountNotFoundExecption;
import sn.groupeisi.ebankigbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<CustomerDTO> listCustomers();
    CustomerDTO getCustomer(Long customerId);

    List<BankAccountDTO> listBankAccounts();

    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundExecption;
    void debit(String accountId,double amount,String description) throws BankAccountNotFoundExecption, BalanceNotSufficientException;
    void credit(String accountId,double amount,String description) throws BankAccountNotFoundExecption;
    void transfer(String accountIdSource,String accountIdDestination,double amount) throws BalanceNotSufficientException, BankAccountNotFoundExecption;
}

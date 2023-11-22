package sn.groupeisi.ebankigbackend.services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.groupeisi.ebankigbackend.dto.CustomerDTO;
import sn.groupeisi.ebankigbackend.entities.*;
import sn.groupeisi.ebankigbackend.enums.OprationType;
import sn.groupeisi.ebankigbackend.exceptions.BalanceNotSufficientException;
import sn.groupeisi.ebankigbackend.exceptions.BankAccountNotFoundExecption;
import sn.groupeisi.ebankigbackend.exceptions.CustomerNotFoundException;
import sn.groupeisi.ebankigbackend.mappers.BankAcountMapperImpl;
import sn.groupeisi.ebankigbackend.repository.AccountOperationRepository;
import sn.groupeisi.ebankigbackend.repository.BankAccountRepository;
import sn.groupeisi.ebankigbackend.repository.CustomerRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
@Slf4j
public class BankAccountServiceImpl implements BankAccountService  {

    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;
    private BankAcountMapperImpl dtoMapper;

    @Autowired
    public BankAccountServiceImpl(
            CustomerRepository customerRepository,
            BankAccountRepository bankAccountRepository,
            AccountOperationRepository accountOperationRepository,
            BankAcountMapperImpl dtoMapper ) {
        this.customerRepository = customerRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.accountOperationRepository = accountOperationRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("Saving new customer");
        Customer customer=dtoMapper.fromCustomerDTO(customerDTO);
        Customer saveCustomer=customerRepository.save(customer);
        return dtoMapper.fromCustomer(saveCustomer);
    }
    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        log.info("Saving new customer");
        Customer customer=dtoMapper.fromCustomerDTO(customerDTO);
        Customer saveCustomer=customerRepository.save(customer);
        return dtoMapper.fromCustomer(saveCustomer);
    }
    @Override
    public void deleteCustomer(Long customerId){
    customerRepository.deleteById(customerId);
    }
    @Override
    public List<CustomerDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> dtoMapper.fromCustomer(customer))
                .toList();
    }
    @Override
    public CustomerDTO getCustomer(Long customerId){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException("customer not found"));
        return dtoMapper.fromCustomer(customer);
    }

    @Override
    public SavingAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer==null)
            throw new CustomerNotFoundException("customer not found");

        SavingAccount   savingAccount= new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setInterestRate(overDraft);
        savingAccount.setCustomer(customer);
        return bankAccountRepository.save(savingAccount);
    }

    @Override
    public CurrentAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer==null)
            throw new CustomerNotFoundException("customer not found");

        CurrentAccount  currentAccount= new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverDraft(interestRate);
        currentAccount.setCustomer(customer);
        return bankAccountRepository.save(currentAccount);
    }

    @Override
    public List<BankAccount> listBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount getBankAccount(String accountId) throws BankAccountNotFoundExecption {
        return bankAccountRepository.findById(accountId)
                .orElseThrow(()->new BankAccountNotFoundExecption("BankAccount not found"));
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundExecption, BalanceNotSufficientException {
        BankAccount bankAccount=getBankAccount(accountId);
        if (bankAccount.getBalance()<amount)
            throw new BalanceNotSufficientException("balance not sufficient ");
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OprationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        //pour debuter un montant
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundExecption {
        BankAccount bankAccount=getBankAccount(accountId);
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OprationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        //pour debuter un montant
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BalanceNotSufficientException, BankAccountNotFoundExecption {
    debit(accountIdSource,amount,"Tranfere to"+accountIdDestination);
    credit(accountIdDestination,amount,"transfer from"+accountIdSource);
    }
}

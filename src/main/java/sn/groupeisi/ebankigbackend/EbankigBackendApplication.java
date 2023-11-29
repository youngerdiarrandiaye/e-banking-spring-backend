package sn.groupeisi.ebankigbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sn.groupeisi.ebankigbackend.dto.BankAccountDTO;
import sn.groupeisi.ebankigbackend.dto.CurrentBankAccountDTO;
import sn.groupeisi.ebankigbackend.dto.CustomerDTO;
import sn.groupeisi.ebankigbackend.dto.SavingBankAccountDTO;

import sn.groupeisi.ebankigbackend.entities.CurrentAccount;
import sn.groupeisi.ebankigbackend.entities.Customer;
import sn.groupeisi.ebankigbackend.entities.SavingAccount;
import sn.groupeisi.ebankigbackend.enums.AccountStatus;
import sn.groupeisi.ebankigbackend.exceptions.CustomerNotFoundException;
import sn.groupeisi.ebankigbackend.repository.AccountOperationRepository;
import sn.groupeisi.ebankigbackend.repository.BankAccountRepository;
import sn.groupeisi.ebankigbackend.repository.CustomerRepository;
import sn.groupeisi.ebankigbackend.services.BankAccountService;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankigBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(EbankigBackendApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
        return args -> {
            Stream.of("Hassan","Imane","Mohamed").forEach(name->{
                CustomerDTO customer=new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomers().forEach(customer->{
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000,customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5,customer.getId());

                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });
            List<BankAccountDTO> bankAccounts = bankAccountService.listBankAccounts();
            for (BankAccountDTO bankAccount:bankAccounts){
                for (int i = 0; i <10 ; i++) {
                    String accountId;
                    if(bankAccount instanceof SavingBankAccountDTO){
                        accountId=((SavingBankAccountDTO) bankAccount).getId();
                    } else{
                        accountId=((CurrentBankAccountDTO) bankAccount).getId();
                    }
                    bankAccountService.credit(accountId,10000+Math.random()*120000,"Credit");
                    bankAccountService.debit(accountId,1000+Math.random()*9000,"Debit");
                }
            }
        };
    }

    //@Bean
    CommandLineRunner  start(CustomerRepository customerRepository,
                             BankAccountRepository bankAccountRepository,
                             AccountOperationRepository accountOperationRepository){
        return args -> {
            Stream.of("OUSSOU","PAPA","SIDI").forEach(name->{
                Customer customer =new Customer();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(cust->{
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*9000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverDraft(9800);
                bankAccountRepository.save(currentAccount);
            });

            customerRepository.findAll().forEach(custs->{
                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()*9000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(custs);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);
            });



        };
    }

}

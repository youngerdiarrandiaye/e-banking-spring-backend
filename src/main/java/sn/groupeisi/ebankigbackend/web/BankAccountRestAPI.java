package sn.groupeisi.ebankigbackend.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sn.groupeisi.ebankigbackend.dto.BankAccountDTO;
import sn.groupeisi.ebankigbackend.exceptions.BankAccountNotFoundExecption;
import sn.groupeisi.ebankigbackend.services.BankAccountService;

import java.util.List;

@RestController
public class BankAccountRestAPI {
    private BankAccountService bankAccountService;

    public BankAccountRestAPI(BankAccountService bankAccountService) {

        this.bankAccountService = bankAccountService;
    }
    @GetMapping("/account/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable  String accoundId) throws BankAccountNotFoundExecption {
        return bankAccountService.getBankAccount(accoundId);
    }
    @GetMapping("/account")
    public List<BankAccountDTO> listAccounts(){
        return bankAccountService.listBankAccounts();
    }
}

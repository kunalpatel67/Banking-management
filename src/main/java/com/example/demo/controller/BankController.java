package com.example.demo.controller;


import com.example.demo.model.Account;
import com.example.demo.model.AccountDTO;
import com.example.demo.model.Statement;
import com.example.demo.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bank")
public class BankController {
    @Autowired
    BankService bankService;

    @PostMapping("open")
    public void openAccount(@RequestBody AccountDTO accountDTO){
        bankService.openAccount(accountDTO);
    }

    @PutMapping("withdraw/{accNo}/{amount}/{date}")
    public String withdraw(@PathVariable int accNo, @PathVariable int amount, @PathVariable String date) {
        return bankService.withdraw(accNo,amount,date);
    }

    @PutMapping("deposit/{accNo}/{amount}/{date}")
    public String deposit(@PathVariable int accNo, @PathVariable int amount, @PathVariable String date) {
        return bankService.deposit(accNo, amount, date);
    }

    @GetMapping("balance/{accNo}")
    public String showBalance(@PathVariable int accNo) {
        return bankService.showBalance(accNo);
    }

    @PostMapping("transfer")
    public String transfer(@RequestBody AccountDTO accountDTO){
        return bankService.transfer(accountDTO);
    }

    @GetMapping("statement/{accNo}")
    public List<Statement> statements(@PathVariable int accNo){
        return bankService.statement(accNo);
    }
}

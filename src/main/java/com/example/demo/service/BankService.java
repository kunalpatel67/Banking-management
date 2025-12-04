package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.AccountDTO;
import com.example.demo.model.Statement;
import com.example.demo.model.TypeEnum;
import com.example.demo.repository.AccountRepo;
import com.example.demo.repository.StatementRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    @Autowired
    AccountRepo accountRepo;

    @Autowired
    StatementRepo statementRepo;

    public void openAccount(AccountDTO accountDTO) {
        int accNo = (int)(Math.random() * 900000) + 100000;
        Account account = new Account(accountDTO.getName(),accountDTO.getAmount(),accNo);
        accountRepo.save(account);
        Statement statement = new Statement(accNo,accountDTO.getAmount(), TypeEnum.DEPOSIT, accountDTO.getDate());
        statementRepo.save(statement);
    }

    public String withdraw(int accNo, int amount, String date) {

            Account account = accountRepo.findById(accNo).orElse(null);

            if (account == null){
                return "account not found";
            }

            if (account.getBalance() < amount){
                return "Insufficient balance";
            }

            account.setBalance(account.getBalance() - amount);
            accountRepo.save(account);

            Statement statement = new Statement(accNo,amount,TypeEnum.WITHDRAW,date);
            statementRepo.save(statement);

            return amount + "withdraw successfully in account" + accNo;

    }

    public String deposit(int accNo, int amount, String date) {
        Account acc = accountRepo.findById(accNo).orElse(null);

        if (acc == null) {
            return "Account not found!";
        }

        acc.setBalance(acc.getBalance() + amount);
        accountRepo.save(acc);

        Statement st = new Statement(accNo, amount, TypeEnum.DEPOSIT, date);
        statementRepo.save(st);

        return amount + " deposited successfully in account " + accNo;
    }

    public String showBalance(int accNo) {
        Account acc = accountRepo.findById(accNo).orElse(null);

        if (acc == null) {
            return "Account not found!";
        }

        return "Account No: " + accNo + ", Name: " + acc.getName() + ", Balance: ₹" + acc.getBalance();
    }

    @Transactional
    public String transfer(AccountDTO accountDTO) {
        Account fromAcc = accountRepo.findById(accountDTO.getFromAcc()).orElseThrow(() -> new RuntimeException("sender account not found"));
        Account toAcc = accountRepo.findById(accountDTO.getToAcc()).orElseThrow(() -> new RuntimeException("receiver account not found"));

        if (fromAcc.getBalance() < accountDTO.getAmount()){
            return "Insufficient balance in sender account";
        }

        fromAcc.setBalance(fromAcc.getBalance() - accountDTO.getAmount());
        accountRepo.save(fromAcc);
        toAcc.setBalance(toAcc.getBalance() + accountDTO.getAmount());
        accountRepo.save(toAcc);

        Statement withdrawStmt = new Statement(accountDTO.getFromAcc(),accountDTO.getAmount(),TypeEnum.WITHDRAW,accountDTO.getDate());
        statementRepo.save(withdrawStmt);

        Statement depoStmt = new Statement(accountDTO.getToAcc(),accountDTO.getAmount(),TypeEnum.DEPOSIT,accountDTO.getDate());
        statementRepo.save(depoStmt);

        return accountDTO.getAmount() + " transferred successfully from Account " + accountDTO.getFromAcc() + " to Account " + accountDTO.getToAcc();
    }

    public List<Statement> statement(int accNo) {
        List<Statement> statements = statementRepo.findByAccNo(accNo);

        if (statements.isEmpty()) {
            throw new RuntimeException("No transactions found for account number: " + accNo);
        }
        return statements;
    }
}

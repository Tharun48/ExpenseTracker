package com.expense_tracker.Expense.Tracker.service;

import com.expense_tracker.Expense.Tracker.exceptionhandler.BadRequestException;
import com.expense_tracker.Expense.Tracker.model.CompareMonthlySavingsDTO;
import com.expense_tracker.Expense.Tracker.model.SavingsResponseDTO;
import com.expense_tracker.Expense.Tracker.model.Transaction;
import com.expense_tracker.Expense.Tracker.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionDetailsImp implements TransactionDetails{


    TransactionRepository transactionRepository;
    User user;

    TransactionDetailsImp(TransactionRepository transactionRepository,User user){
        this.transactionRepository=transactionRepository;
        this.user=user;
    }

    private boolean isValidTransactionCategory(int category) {
        return category==1  || category==2;
    }

    @Override
    public int saveTransaction(Transaction transaction) {
        if(!isValidTransactionCategory(transaction.getTransactionCategory())) {
            throw new IllegalArgumentException("Invalid transaction category "+ transaction.getTransactionCategory() + " category should be 1 or 2");
        }
        Transaction transactional= transactionRepository.save(transaction);
        return transactional.getTransactionId();
    }

    @Override
    public Transaction getTransaction(int transactionId) {
        Transaction transaction = transactionRepository.findByTransactionId(transactionId);
        if(transaction==null) throw new IllegalArgumentException("Transaction details not found for the transactionId: " + transactionId);
        return transaction;
    }

    @Override
    @Transactional
    public int modifyTransactionDetails(Transaction transaction) {
        if(!isValidTransactionCategory(transaction.getTransactionCategory())) {
            throw new IllegalArgumentException("Invalid transaction category "+ transaction.getTransactionCategory() + " category should be 1 or 2");
        }
        Transaction transactional=  transactionRepository.save(transaction);
        return transactional.getTransactionId();
    }

    @Override
    @Transactional
    public int deleteTransaction(int transactionId) {
        Transaction transaction = getTransaction(transactionId);
        transactionRepository.delete(transaction);
        return transactionId;
    }

    @Override
    public SavingsResponseDTO savings(int userId, LocalDate fromDate, LocalDate toDate) {
        List<Transaction> transactionList = getTransactionWithDate(userId,fromDate,toDate);
        double totalSavings=0;
        Map<String,Double> map = new HashMap<>();
        for(Transaction transaction:transactionList) {
            totalSavings+=(transaction.getTransactionCategory()==1) ? transaction.getTransactionAmount() : -1*transaction.getTransactionAmount();
            String description = transaction.getDescription();
            description = description.trim();
            description=description.toLowerCase();
            if(transaction.getTransactionCategory()==2) {
                if(map.containsKey(description)) {
                    map.put(description,map.get(description)+transaction.getTransactionAmount());
                }
                else{
                    map.put(description,transaction.getTransactionAmount());
                }
            }
        }
        return new SavingsResponseDTO(totalSavings,map);
    }

    @Override
    public List<Transaction> getTransactionUser(int userId) {
        return transactionRepository.getALlTransaction(userId);
    }

    @Override
    public Page<Transaction> getTransactionUser1(int userId,Pageable pageable) {
        return transactionRepository.getALlTransactionCheck(userId,pageable);
    }

    @Override
    public List<Transaction> getTransactionWithDate(int userId, LocalDate fromDate, LocalDate toDate) {
        user.getUser(userId);
        if(fromDate!=null && toDate!=null) {
            if(fromDate.isAfter(toDate)) throw new BadRequestException("fromDate should be less than toDate");
            return transactionRepository.getTransactionBetweenDateRange(userId,fromDate,toDate);
        }
        else if(fromDate==null && toDate==null){
            return transactionRepository.getALlTransaction(userId);
        }
        else if(fromDate!=null) {
            return transactionRepository.getTransactionFromDate(userId,fromDate);
        }
        return transactionRepository.getTransactionToDate(userId,toDate);
    }

    @Override
    public CompareMonthlySavingsDTO comparingMonthlySavingsDTO(int userId, int firstYear, int firstMonth, int secondYear, int secondMonth) {

        if(firstMonth<0||firstMonth>12||secondMonth<0||secondMonth>12)
        {
            throw new IllegalArgumentException("Month value is not valid");
        }

        List<Transaction> month1 = transactionRepository.getTransactionBasedOnMonthYear(userId,firstYear,firstMonth,2);
        List<Transaction> month2 = transactionRepository.getTransactionBasedOnMonthYear(userId,secondYear,secondMonth,2);

        Map<String,double[]> map = new HashMap<>();
        List<List<Transaction>> transactionList = new ArrayList<>();
        transactionList.add(month1);
        transactionList.add(month2);
        int size = transactionList.size();
        for(int i=0;i<size;i++) {
            List<Transaction> transactions = transactionList.get(i);
            for(Transaction transaction : transactions ) {
                int transactionCategory = transaction.getTransactionCategory();
                if(transactionCategory==1) continue;
                String transactionName = transaction.getDescription();
                transactionName = transactionName.trim().toLowerCase();
                double amount=transaction.getTransactionAmount();
                if(!map.containsKey(transactionName)) {
                    double[] monthlyAmount = new double[size];
                    monthlyAmount[i]=amount;
                    map.put(transactionName,monthlyAmount);
                }
                else{
                    double[] monthlyAmount = map.get(transactionName);
                    monthlyAmount[i]+=amount;
                    map.put(transactionName,monthlyAmount);
                }
            }
        }
        return new CompareMonthlySavingsDTO(map);
    }



}

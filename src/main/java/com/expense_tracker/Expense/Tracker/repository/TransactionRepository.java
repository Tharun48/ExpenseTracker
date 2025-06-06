package com.expense_tracker.Expense.Tracker.repository;

import com.expense_tracker.Expense.Tracker.model.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
@EnableJpaRepositories
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    Transaction findByTransactionId(int transactionId);

    @Query("SELECT t FROM Transaction t WHERE t.user_transaction_id.userId = :userId AND t.createdOn BETWEEN :fromDate AND :toDate")
    List<Transaction> getTransactionBetweenDateRange(@Param("userId") int userId, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

    @Query("SELECT t FROM Transaction t WHERE t.user_transaction_id.userId = :userId AND t.createdOn >= :fromDate")
    List<Transaction> getTransactionFromDate(@Param("userId") int userId, @Param("fromDate") LocalDate fromDate);

    @Query("SELECT t FROM Transaction t WHERE t.user_transaction_id.userId = :userId AND t.createdOn <= :toDate")
    List<Transaction> getTransactionToDate(@Param("userId") int userId, @Param("toDate") LocalDate toDate);

    @Query("SELECT t FROM Transaction t WHERE t.user_transaction_id.userId = :userId")
    List<Transaction> getALlTransaction(@Param("userId") int userId);


    @Query("SELECT t FROM Transaction t WHERE t.user_transaction_id.userId = :userId AND YEAR(t.createdOn) = :year AND MONTH(t.createdOn) = :month AND t.transactionCategory = :category")
    List<Transaction> getTransactionBasedOnMonthYear(@Param("userId") int userId,@Param("year") int year,@Param("month") int month,@Param("category") int transactionCategory);


}
package com.expensetracker.expensetracker.repository;

import com.expensetracker.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // Find expenses by category
    List<Expense> findByCategory(String category);

    // Find expenses by date range
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);
}

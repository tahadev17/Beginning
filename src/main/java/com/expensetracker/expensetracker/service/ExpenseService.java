package com.expensetracker.expensetracker.service;


import com.expensetracker.expensetracker.model.Expense;
import com.expensetracker.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    // Get all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Get expenses by category
    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }

    // Get expenses by date range
    public List<Expense> getExpensesByDateRange(LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findByDateBetween(startDate, endDate);
    }

    // Add an expense
    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    // Update an expense
    public Expense updateExpense(Long id, Expense updatedExpense) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
        expense.setAmount(updatedExpense.getAmount());
        expense.setCategory(updatedExpense.getCategory());
        expense.setDate(updatedExpense.getDate());
        expense.setDescription(updatedExpense.getDescription());
        return expenseRepository.save(expense);
    }

    // Delete an expense
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}

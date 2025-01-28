package com.expensetracker.expensetracker.controller;

import com.expensetracker.expensetracker.model.Expense;
import com.expensetracker.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Display all expenses
    @GetMapping
    public String getAllExpenses(Model model) {
        List<Expense> expenses = expenseService.getAllExpenses();
        model.addAttribute("expenses", expenses);
        return "expenses"; // Thymeleaf template name
    }

    // Display form to add a new expense
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("expense", new Expense());
        return "add-expense"; // Thymeleaf template name
    }

    // Add a new expense
    @PostMapping("/add")
    public String addExpense(@ModelAttribute Expense expense) {
        expenseService.addExpense(expense);
        return "redirect:/expenses";
    }

    // Display form to edit an expense
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Expense expense = expenseService.getAllExpenses().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        model.addAttribute("expense", expense);
        return "edit-expense"; // Thymeleaf template name
    }

    // Update an expense
    @PostMapping("/edit/{id}")
    public String updateExpense(@PathVariable Long id, @ModelAttribute Expense updatedExpense) {
        expenseService.updateExpense(id, updatedExpense);
        return "redirect:/expenses";
    }

    // Delete an expense
    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }
}
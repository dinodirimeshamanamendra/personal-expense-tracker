package org.example.model;

import java.util.List;

public class ExpenseReport {
    private List<Expense> expenses;

    public ExpenseReport(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public double getTotal() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }
}

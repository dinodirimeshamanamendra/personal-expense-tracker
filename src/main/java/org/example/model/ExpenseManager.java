package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {
    private static final List<Expense> expenses = new ArrayList<>();

    public static void addExpense(Expense e) {
        expenses.add(e);
    }

    public static List<Expense> getExpenses() {
        return expenses;
    }
}

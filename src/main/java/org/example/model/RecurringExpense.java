package org.example.model;

import java.time.LocalDate;

public class RecurringExpense extends Expense {

    private String frequency;

    public RecurringExpense(String type, String category, double amount,
                            LocalDate date, String frequency) {

        super(type, category, amount, date);
        this.frequency = frequency;
    }

    public String getFrequency() {
        return frequency;
    }
}

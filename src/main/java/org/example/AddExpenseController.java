package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.model.Expense;
import org.example.model.ExpenseManager;

public class AddExpenseController {

    @FXML private ComboBox<String> typeBox;
    @FXML private ComboBox<String> categoryBox;
    @FXML private TextField amountField;
    @FXML private DatePicker datePicker;

    @FXML
    public void initialize() {
        typeBox.getItems().addAll("Income", "Expense");
        categoryBox.getItems().addAll(
                "Food", "Household", "Transport", "Salary", "Entertainment"
        );
    }

    @FXML
    public void saveExpense() {
        Expense expense = new Expense(
                typeBox.getValue(),
                categoryBox.getValue(),
                Double.parseDouble(amountField.getText()),
                datePicker.getValue()
        );

        ExpenseManager.addExpense(expense);

        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/expenses.fxml"))
            ));
            stage.setTitle("Expense List");
            stage.show();

            ((Stage) amountField.getScene().getWindow()).close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

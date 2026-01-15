package org.example;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.model.Expense;
import org.example.model.ExpenseManager;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ReportController {

    @FXML private Label incomeLabel;
    @FXML private Label expenseLabel;
    @FXML private Label netTotalLabel;

    @FXML private TableView<Map.Entry<String, Double>> summaryTable;
    @FXML private TableColumn<Map.Entry<String, Double>, String> colCategory;
    @FXML private TableColumn<Map.Entry<String, Double>, Double> colTotal;

    @FXML
    public void initialize() {
        colCategory.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getKey()));
        colTotal.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getValue()));
    }

    public void weeklyReport() {
        generateReport(LocalDate.now().minusDays(7));
    }

    public void monthlyReport() {
        generateReport(LocalDate.now().minusMonths(1));
    }

    private void generateReport(LocalDate fromDate) {

        List<Expense> filtered = ExpenseManager.getExpenses().stream()
                .filter(e -> e.getDate().isAfter(fromDate))
                .collect(Collectors.toList());

        double income = filtered.stream()
                .filter(e -> e.getType().equals("Income"))
                .mapToDouble(Expense::getAmount).sum();

        double expense = filtered.stream()
                .filter(e -> e.getType().equals("Expense"))
                .mapToDouble(Expense::getAmount).sum();

        incomeLabel.setText("Total Income: Rs " + income);
        expenseLabel.setText("Total Expense: Rs " + expense);
        netTotalLabel.setText("Net Balance: Rs " + (income - expense));

        Map<String, Double> categoryTotals = filtered.stream()
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        Collectors.summingDouble(Expense::getAmount)
                ));

        summaryTable.setItems(FXCollections.observableArrayList(
                categoryTotals.entrySet()
        ));
    }

    public void viewAll() {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/expenses.fxml"))
            ));
            stage.setTitle("All Expenses");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goBack() {
        ((Stage) summaryTable.getScene().getWindow()).close();
    }
}

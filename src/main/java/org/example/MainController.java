package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    public void openAdd() {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/add_expense.fxml"))
            ));
            stage.setTitle("Add Expense");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeApp() {
        System.exit(0);
    }
    public void openReport() {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/report.fxml"))
            ));
            stage.setTitle("Expense Report");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

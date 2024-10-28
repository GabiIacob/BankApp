import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;

public class SetAtmButton {
    private Stage primaryStage;
    private AdminDashboard adminDashboard;
    private double atmFunds;

    public SetAtmButton(Stage primaryStage, AdminDashboard adminDashboard) {
        this.primaryStage = primaryStage;
        this.adminDashboard = adminDashboard;
        this.atmFunds = 0;
    }

    public void execute() {
        Pane setATMPane = new Pane();
        setATMPane.setStyle("-fx-background-color: white;");

        Label titleLabel = new Label("Set ATM Funds");
        titleLabel.setLayoutX(150);
        titleLabel.setLayoutY(20);

        Label amountLabel = new Label("Enter the amount to load into ATM:");
        amountLabel.setLayoutX(30);
        amountLabel.setLayoutY(60);

        TextField amountField = new TextField();
        amountField.setLayoutX(230);
        amountField.setLayoutY(60);

        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(150);
        submitButton.setLayoutY(120);

        Button backButton = new Button("Go Back");
        backButton.setLayoutX(250);
        backButton.setLayoutY(120);

        submitButton.setOnAction(e -> {
            double amount = Double.parseDouble(amountField.getText());
            atmFunds = amount;
            JOptionPane.showMessageDialog(null, "ATM loaded with: " + atmFunds);


            adminDashboard.showAdminDashboard();
        });

        backButton.setOnAction(e -> adminDashboard.showAdminDashboard());

        setATMPane.getChildren().addAll(titleLabel, amountLabel, amountField, submitButton, backButton);
        primaryStage.setScene(new Scene(setATMPane, 400, 200));
    }


}
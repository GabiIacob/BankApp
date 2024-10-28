import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;

public class CashDepositButton {
    private Stage primaryStage;
    private AdminDashboard adminDashboard;

    public CashDepositButton(Stage primaryStage, AdminDashboard adminDashboard) {
        this.primaryStage = primaryStage;
        this.adminDashboard = adminDashboard;
    }

    public void execute() {
        Pane cashDepositPane = new Pane();
        cashDepositPane.setStyle("-fx-background-color: white;");

        Label titleLabel = new Label("Cash Deposit");
        titleLabel.setLayoutX(150);
        titleLabel.setLayoutY(20);

        Label nameLabel = new Label("Enter the client's name:");
        nameLabel.setLayoutX(30);
        nameLabel.setLayoutY(60);

        TextField nameField = new TextField();
        nameField.setLayoutX(200);
        nameField.setLayoutY(60);

        Label amountLabel = new Label("Enter amount to deposit:");
        amountLabel.setLayoutX(30);
        amountLabel.setLayoutY(100);

        TextField amountField = new TextField();
        amountField.setLayoutX(200);
        amountField.setLayoutY(100);

        Button depositButton = new Button("Deposit");
        depositButton.setLayoutX(30);
        depositButton.setLayoutY(140);

        depositButton.setOnAction(e -> {
            String clientName = nameField.getText();
            double amount = Double.parseDouble(amountField.getText());
            ContClient.depunere(clientName, amount);
            JOptionPane.showConfirmDialog(null, "Deposit Made", "Result",
                    JOptionPane.CLOSED_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            adminDashboard.showAdminDashboard();
        });

        Button backButton = new Button("Go Back");
        backButton.setLayoutX(150);
        backButton.setLayoutY(140);
        backButton.setOnAction(e -> adminDashboard.showAdminDashboard());

        cashDepositPane.getChildren().addAll(titleLabel, nameLabel, nameField, amountLabel, amountField, depositButton, backButton);
        primaryStage.setScene(new Scene(cashDepositPane, 400, 200));
    }
}

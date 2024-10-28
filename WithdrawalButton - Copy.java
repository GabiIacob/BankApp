import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;

public class WithdrawalButton {
    private Stage primaryStage;
    private ClientDashboard clientDashboard;
    private ContClient loggedInClient;

    public WithdrawalButton(Stage primaryStage, ClientDashboard clientDashboard, ContClient loggedInClient) {
        this.primaryStage = primaryStage;
        this.clientDashboard = clientDashboard;
        this.loggedInClient = loggedInClient;
    }

    public void execute() {
        Pane cashDepositPane = new Pane();
        cashDepositPane.setStyle("-fx-background-color: white;");

        Label titleLabel = new Label("Cash withdrawal");
        titleLabel.setLayoutX(150);
        titleLabel.setLayoutY(20);

        Label amountLabel = new Label("Enter amount to withdraw:");
        amountLabel.setLayoutX(30);
        amountLabel.setLayoutY(60);

        TextField amountField = new TextField();
        amountField.setLayoutX(200);
        amountField.setLayoutY(60);

        Button depositButton = new Button("Withdraw");
        depositButton.setLayoutX(30);
        depositButton.setLayoutY(100);

        depositButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount < loggedInClient.suma) {
                    loggedInClient.retragere2(amount);
                    JOptionPane.showMessageDialog(null, "Withdrawal made", "Result",
                            JOptionPane.INFORMATION_MESSAGE);
                    clientDashboard.showClientDashboard();
                } else {
                    JOptionPane.showMessageDialog(null, "Amount must be lower.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid amount entered.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        Button backButton = new Button("Go Back");
        backButton.setLayoutX(150);
        backButton.setLayoutY(100);
        backButton.setOnAction(e -> clientDashboard.showClientDashboard());

        cashDepositPane.getChildren().addAll(titleLabel, amountLabel, amountField, depositButton, backButton);
        primaryStage.setScene(new Scene(cashDepositPane, 400, 200));
    }
}

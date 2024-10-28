import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;

public class TransferButon {
    private Stage primaryStage;
    private ClientDashboard clientDashboard;
    private ContClient loggedInClient;

    public TransferButon(Stage primaryStage, ClientDashboard clientDashboard, ContClient loggedInClient) {
        this.primaryStage = primaryStage;
        this.clientDashboard = clientDashboard;
        this.loggedInClient = loggedInClient;
    }

    public void execute() {
        Pane transferPane = new Pane();
        transferPane.setStyle("-fx-background-color: white;");

        Label titleLabel = new Label("Transfer Money");
        titleLabel.setLayoutX(150);
        titleLabel.setLayoutY(20);

        Label recipientLabel = new Label("Enter recipient name:");
        recipientLabel.setLayoutX(30);
        recipientLabel.setLayoutY(60);

        TextField recipientField = new TextField();
        recipientField.setLayoutX(200);
        recipientField.setLayoutY(60);

        Label amountLabel = new Label("Enter amount to transfer:");
        amountLabel.setLayoutX(30);
        amountLabel.setLayoutY(100);

        TextField amountField = new TextField();
        amountField.setLayoutX(200);
        amountField.setLayoutY(100);

        Button transferButton = new Button("Transfer");
        transferButton.setLayoutX(30);
        transferButton.setLayoutY(140);

        transferButton.setOnAction(e -> {
            try {
                String recipientName = recipientField.getText();
                double amount = Double.parseDouble(amountField.getText());


                if (amount <= 0) {
                    JOptionPane.showMessageDialog(null, "Amount must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (amount > loggedInClient.suma) {
                    JOptionPane.showMessageDialog(null, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ContClient recipient = findClientByName(recipientName);
                if (recipient == null) {
                    JOptionPane.showMessageDialog(null, "Recipient not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                loggedInClient.retragere2(amount);
                recipient.depunere2(amount);

                JOptionPane.showMessageDialog(null, "Transfer successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clientDashboard.showClientDashboard();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        Button backButton = new Button("Go Back");
        backButton.setLayoutX(150);
        backButton.setLayoutY(140);
        backButton.setOnAction(e -> clientDashboard.showClientDashboard());

        transferPane.getChildren().addAll(titleLabel, recipientLabel, recipientField, amountLabel, amountField, transferButton, backButton);
        primaryStage.setScene(new Scene(transferPane, 400, 200));
    }

    private ContClient findClientByName(String name) {
        for (int i = 0; i < ContClient.clientCounter; i++) {
            if (ContClient.clienti[i].nume.equals(name)) {
                return ContClient.clienti[i];
            }
        }
        return null;
    }
}

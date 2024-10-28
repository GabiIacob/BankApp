import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

public class TransferButton {
    private Stage primaryStage;
    private AdminDashboard adminDashboard;

    public TransferButton(Stage primaryStage, AdminDashboard adminDashboard) {
        this.primaryStage = primaryStage;
        this.adminDashboard = adminDashboard;
    }

    public void execute() {
        Pane transferPane = new Pane();
        transferPane.setStyle("-fx-background-color: white;");

        Label titleLabel = new Label("Amount Transfer");
        titleLabel.setLayoutX(150);
        titleLabel.setLayoutY(20);

        Label nameLabel = new Label("Enter the account name:");
        nameLabel.setLayoutX(30);
        nameLabel.setLayoutY(60);

        TextField nameField = new TextField();
        nameField.setLayoutX(200);
        nameField.setLayoutY(60);

        Label transferLabel = new Label("Enter the account to deposit:");
        transferLabel.setLayoutX(30);
        transferLabel.setLayoutY(100);

        TextField transferField = new TextField();
        transferField.setLayoutX(200);
        transferField.setLayoutY(100);

        Label newSumLabel = new Label("Enter Transfer Sum:");
        newSumLabel.setLayoutX(30);
        newSumLabel.setLayoutY(140);

        TextField newSumField = new TextField();
        newSumField.setLayoutX(200);
        newSumField.setLayoutY(140);

        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(150);
        submitButton.setLayoutY(220);

        Button backButton = new Button("Go Back");
        backButton.setLayoutX(250);
        backButton.setLayoutY(220);


        submitButton.setOnAction(e -> {
            String name = nameField.getText();
            String nameToDeposit = transferField.getText();
            double transferAmount;

            try {
                transferAmount = Double.parseDouble(newSumField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            ContClient sourceClient = null;
            ContClient destinationClient = null;

            for (int i = 0; i < ContClient.clientCounter; i++) {
                if (ContClient.clienti[i].nume.equals(name)) {
                    sourceClient = ContClient.clienti[i];
                }
                if (ContClient.clienti[i].nume.equals(nameToDeposit)) {
                    destinationClient = ContClient.clienti[i];
                }
            }


            if (sourceClient == null) {
                JOptionPane.showMessageDialog(null, "Source account not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (destinationClient == null) {
                JOptionPane.showMessageDialog(null, "Destination account not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            if (sourceClient.suma < transferAmount) {
                JOptionPane.showMessageDialog(null, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                adminDashboard.showAdminDashboard(); // Revenim la AdminDashboard
                return;
            }

            sourceClient.suma -= transferAmount;
            sourceClient.tranzactiiefectuate++;

            destinationClient.suma += transferAmount;
            destinationClient.tranzactiiefectuate++;

            JOptionPane.showMessageDialog(null, "Transfer successful!", "Success", JOptionPane.INFORMATION_MESSAGE);


            adminDashboard.showAdminDashboard();
        });


        backButton.setOnAction(e -> adminDashboard.showAdminDashboard());

        transferPane.getChildren().addAll(titleLabel, nameLabel, nameField, transferLabel, transferField, newSumLabel, newSumField, submitButton, backButton);
        primaryStage.setScene(new Scene(transferPane, 400, 300));
    }
}

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CashWithdrawalButton {
    private Stage primaryStage;
    private AdminDashboard adminDashboard;

    public CashWithdrawalButton(Stage primaryStage, AdminDashboard adminDashboard) {
        this.primaryStage = primaryStage;
        this.adminDashboard = adminDashboard;
    }

    public void execute() {
        Pane cashWithdrawalPane = new Pane();
        cashWithdrawalPane.setStyle("-fx-background-color: white;");

        Label titleLabel = new Label("Cash Withdrawal");
        titleLabel.setLayoutX(150);
        titleLabel.setLayoutY(20);

        Label nameLabel = new Label("Enter the client's name:");
        nameLabel.setLayoutX(30);
        nameLabel.setLayoutY(60);

        TextField nameField = new TextField();
        nameField.setLayoutX(200);
        nameField.setLayoutY(60);

        Label amountLabel = new Label("Enter amount to withdraw:");
        amountLabel.setLayoutX(30);
        amountLabel.setLayoutY(100);

        TextField amountField = new TextField();
        amountField.setLayoutX(200);
        amountField.setLayoutY(100);

        Button withdrawButton = new Button("Withdraw");
        withdrawButton.setLayoutX(30);
        withdrawButton.setLayoutY(140);

        withdrawButton.setOnAction(e -> {
            String clientName = nameField.getText();
            double amount = Double.parseDouble(amountField.getText());
            ContClient.retragere(clientName, amount);
            adminDashboard.showAdminDashboard();
        });

        Button backButton = new Button("Go Back");
        backButton.setLayoutX(150);
        backButton.setLayoutY(140);
        backButton.setOnAction(e -> adminDashboard.showAdminDashboard());

        cashWithdrawalPane.getChildren().addAll(titleLabel, nameLabel, nameField, amountLabel, amountField, withdrawButton, backButton);
        primaryStage.setScene(new Scene(cashWithdrawalPane, 400, 200));
    }
}

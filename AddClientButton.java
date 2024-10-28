import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddClientButton {
    private Stage primaryStage;
    private AdminDashboard adminDashboard;

    public AddClientButton(Stage primaryStage, AdminDashboard adminDashboard) {
        this.primaryStage = primaryStage;
        this.adminDashboard = adminDashboard;
    }

    public void execute() {
        Pane addClientPane = new Pane();
        addClientPane.setStyle("-fx-background-color: white;");

        Label label = new Label("Add New Client");
        label.setLayoutX(150);
        label.setLayoutY(20);

        TextField nameField = new TextField();
        nameField.setPromptText("Enter client name");
        nameField.setLayoutX(100);
        nameField.setLayoutY(60);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter client password");
        passwordField.setLayoutX(100);
        passwordField.setLayoutY(100);

        TextField amountField = new TextField();
        amountField.setPromptText("Enter initial amount");
        amountField.setLayoutX(100);
        amountField.setLayoutY(140);

        TextField transactionField = new TextField();
        transactionField.setPromptText("Enter transaction count");
        transactionField.setLayoutX(100);
        transactionField.setLayoutY(180);

        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(150);
        submitButton.setLayoutY(220);

        Button backButton = new Button("Go Back");
        backButton.setLayoutX(250);
        backButton.setLayoutY(220);

        submitButton.setOnAction(e -> {
            String name = nameField.getText();
            String password = passwordField.getText();
            double initialAmount = Double.parseDouble(amountField.getText());
            int transactionCount = Integer.parseInt(transactionField.getText());


            ContClient.clienti[ContClient.clientCounter] = new ContClient(name, password, initialAmount, transactionCount);
            ContClient.clientCounter++;


            adminDashboard.showAdminDashboard();
        });

        backButton.setOnAction(e -> adminDashboard.showAdminDashboard());

        addClientPane.getChildren().addAll(label, nameField, passwordField, amountField, transactionField, submitButton, backButton);
        primaryStage.setScene(new Scene(addClientPane, 400, 300));
    }
}

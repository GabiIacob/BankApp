import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PinSwitchButton {
    private Stage primaryStage;
    private AdminDashboard adminDashboard;

    public PinSwitchButton(Stage primaryStage, AdminDashboard adminDashboard) {
        this.primaryStage = primaryStage;
        this.adminDashboard = adminDashboard;
    }

    public void execute() {
        Pane pinSwitchPane = new Pane();
        pinSwitchPane.setStyle("-fx-background-color: white;");

        Label titleLabel = new Label("PIN Switch");
        titleLabel.setLayoutX(150);
        titleLabel.setLayoutY(20);

        Label nameLabel = new Label("Enter the account name:");
        nameLabel.setLayoutX(30);
        nameLabel.setLayoutY(60);

        TextField nameField = new TextField();
        nameField.setLayoutX(200);
        nameField.setLayoutY(60);

        Label newPinLabel = new Label("Enter new PIN:");
        newPinLabel.setLayoutX(30);
        newPinLabel.setLayoutY(100);

        PasswordField newPinField = new PasswordField();
        newPinField.setLayoutX(200);
        newPinField.setLayoutY(100);

        Button switchButton = new Button("Change PIN");
        switchButton.setLayoutX(30);
        switchButton.setLayoutY(140);

        switchButton.setOnAction(e -> {
            String clientName = nameField.getText();
            String newPin = newPinField.getText();
            ContClient.modificaPIN(clientName, newPin);
            adminDashboard.showAdminDashboard();
        });

        Button backButton = new Button("Go Back");
        backButton.setLayoutX(150);
        backButton.setLayoutY(140);
        backButton.setOnAction(e -> adminDashboard.showAdminDashboard());

        pinSwitchPane.getChildren().addAll(titleLabel, nameLabel, nameField, newPinLabel, newPinField, switchButton, backButton);
        primaryStage.setScene(new Scene(pinSwitchPane, 400, 200));
    }
}

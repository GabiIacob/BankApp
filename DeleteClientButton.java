import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DeleteClientButton {
    private Stage primaryStage;
    private AdminDashboard adminDashboard;

    public DeleteClientButton(Stage primaryStage, AdminDashboard adminDashboard) {
        this.primaryStage = primaryStage;
        this.adminDashboard = adminDashboard;
    }

    public void execute() {
        Pane deleteClientPane = new Pane();
        deleteClientPane.setStyle("-fx-background-color: white;");

        Label titleLabel = new Label("Delete Client");
        titleLabel.setLayoutX(150);
        titleLabel.setLayoutY(20);

        Label nameLabel = new Label("Enter the client's name:");
        nameLabel.setLayoutX(30);
        nameLabel.setLayoutY(60);

        TextField nameField = new TextField();
        nameField.setLayoutX(200);
        nameField.setLayoutY(60);

        Button deleteButton = new Button("Delete Client");
        deleteButton.setLayoutX(30);
        deleteButton.setLayoutY(100);

        deleteButton.setOnAction(e -> {
            String clientName = nameField.getText();
            boolean clientFound = false;

            for (int i = 0; i < ContClient.clientCounter; i++) {
                if (ContClient.clienti[i].nume.equals(clientName)) {
                    clientFound = true;
                    for (int j = i; j < ContClient.clientCounter - 1; j++) {
                        ContClient.clienti[j] = ContClient.clienti[j + 1];
                    }
                    ContClient.clientCounter--;
                    System.out.println("Clientul a fost șters cu succes.");
                    break;
                }
            }

            if (!clientFound) {
                System.out.println("Clientul cu acest nume nu a fost gasit.");
            }

            adminDashboard.showAdminDashboard();
        });

        Button backButton = new Button("Go Back");
        backButton.setLayoutX(150);
        backButton.setLayoutY(100);

        backButton.setOnAction(e -> adminDashboard.showAdminDashboard());

        deleteClientPane.getChildren().addAll(titleLabel, nameLabel, nameField, deleteButton, backButton);
        primaryStage.setScene(new Scene(deleteClientPane, 400, 200));
    }
}

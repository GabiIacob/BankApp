import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AllClientButton {
    private Stage primaryStage;
    private AdminDashboard adminDashboard;

    public AllClientButton(Stage primaryStage, AdminDashboard adminDashboard) {
        this.primaryStage = primaryStage;
        this.adminDashboard = adminDashboard;
    }

    public void execute() {
        GridPane allClientsPane = new GridPane();
        allClientsPane.setStyle("-fx-background-color: white;");
        allClientsPane.setVgap(10);
        allClientsPane.setHgap(10);


        allClientsPane.add(new Label("Index"), 0, 0);
        allClientsPane.add(new Label("Nume"), 1, 0);
        allClientsPane.add(new Label("Parola"), 2, 0);
        allClientsPane.add(new Label("Suma"), 3, 0);
        allClientsPane.add(new Label("Nr. Tranzactii"), 4, 0);

        for (int i = 0; i < ContClient.clientCounter; i++) {
            ContClient client = ContClient.clienti[i];
            allClientsPane.add(new Label(String.valueOf(i)), 0, i + 1); // Index
            allClientsPane.add(new Label(client.nume), 1, i + 1); // Nume
            allClientsPane.add(new Label(client.parola), 2, i + 1); // Parola
            allClientsPane.add(new Label(String.valueOf(client.suma)), 3, i + 1); // Suma
            allClientsPane.add(new Label(String.valueOf(client.tranzactiiefectuate)), 4, i + 1);
        }

        Button backButton = new Button("Go Back");
        backButton.setLayoutX(150);
        backButton.setLayoutY(220);
        backButton.setOnAction(e -> adminDashboard.showAdminDashboard());

        allClientsPane.add(backButton, 1, ContClient.clientCounter + 1);

        primaryStage.setScene(new Scene(allClientsPane, 500, 300));
    }
}

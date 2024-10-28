import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AccInfo {
    private Stage primaryStage;
    private ClientDashboard clientDashboard;
    private ContClient loggedInClient;

    public AccInfo(Stage primaryStage, ClientDashboard clientDashboard, ContClient loggedInClient) {
        this.primaryStage = primaryStage;
        this.clientDashboard = clientDashboard;
        this.loggedInClient = loggedInClient;
    }

    public void execute() {
        Pane accInfoPane = new Pane();
        accInfoPane.setStyle("-fx-background-color: white;");

        Label titleLabel = new Label("Account Information");
        titleLabel.setLayoutX(150);
        titleLabel.setLayoutY(20);

        Label nameLabel = new Label("Name: " + loggedInClient.getNume());
        nameLabel.setLayoutX(30);
        nameLabel.setLayoutY(60);

        Label passwordLabel = new Label("Password: " + loggedInClient.getParola());
        passwordLabel.setLayoutX(30);
        passwordLabel.setLayoutY(90);

        Label balanceLabel = new Label("Balance: " + loggedInClient.suma + " RON");
        balanceLabel.setLayoutX(30);
        balanceLabel.setLayoutY(120);

        Label transactionsLabel = new Label("Transactions Count: " + loggedInClient.tranzactiiefectuate);
        transactionsLabel.setLayoutX(30);
        transactionsLabel.setLayoutY(150);


        Button backButton = new Button("Go Back");
        backButton.setLayoutX(150);
        backButton.setLayoutY(200);
        backButton.setOnAction(e -> clientDashboard.showClientDashboard());

        accInfoPane.getChildren().addAll(titleLabel, nameLabel, passwordLabel, balanceLabel, transactionsLabel, backButton);
        primaryStage.setScene(new Scene(accInfoPane, 400, 300));
    }
}


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminDashboard extends Panel {

    private Stage primaryStage;
    private Scene mainScene;

    public AdminDashboard(Stage primaryStage, Scene mainScene) {
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;
    }

    public void showAdminDashboard() {
        Pane adminDashboard = new Pane();
        adminDashboard.setStyle("-fx-background-color: lightgray;");

        Label welcomeLabel = new Label("Welcome, Admin!");
        welcomeLabel.setLayoutX(150);
        welcomeLabel.setLayoutY(20);

        Button AddClient = new Button("AddClient");
        AddClient.setLayoutX(30);
        AddClient.setLayoutY(30);

        Button DeleteClient = new Button("DeleteClient");
        DeleteClient.setLayoutX(30);
        DeleteClient.setLayoutY(70);

        Button AllClients = new Button("AllClients");
        AllClients.setLayoutX(30);
        AllClients.setLayoutY(110);

        Button SetATM = new Button("SetATM");
        SetATM.setLayoutX(30);
        SetATM.setLayoutY(150);

        Button Transfer = new Button("Transfer");
        Transfer.setLayoutX(290);
        Transfer.setLayoutY(30);

        Button CashDeposit = new Button("CashDeposit");
        CashDeposit.setLayoutX(290);
        CashDeposit.setLayoutY(70);

        Button CashWithdrawal = new Button("CashWithdrawal");
        CashWithdrawal.setLayoutX(290);
        CashWithdrawal.setLayoutY(110);

        Button PinSwitch = new Button("PinSwitch");
        PinSwitch.setLayoutX(290);
        PinSwitch.setLayoutY(150);


        Button logoutButton = new Button("Logout");
        logoutButton.setLayoutX(170);
        logoutButton.setLayoutY(200);


        AddClientButton addClientButton = new AddClientButton(primaryStage, this);
        DeleteClientButton deleteClientButton= new DeleteClientButton(primaryStage,this);
        AllClientButton allClientsButton= new AllClientButton(primaryStage, this);
        CashWithdrawalButton cashWithdrawalButton = new CashWithdrawalButton(primaryStage, this);
        CashDepositButton cashDepositButton = new CashDepositButton(primaryStage, this);
        PinSwitchButton pinSwitchButton = new PinSwitchButton(primaryStage, this);
        TransferButton transferButton= new TransferButton(primaryStage,this);
        SetAtmButton SetAtm= new SetAtmButton(primaryStage,this);


        AddClient.setOnAction(e -> addClientButton.execute());
        AllClients.setOnAction(e -> allClientsButton.execute());
        DeleteClient.setOnAction(e->deleteClientButton.execute());
        CashWithdrawal.setOnAction(e -> cashWithdrawalButton.execute());
        CashDeposit.setOnAction(e -> cashDepositButton.execute());
        PinSwitch.setOnAction(e -> pinSwitchButton.execute());
        Transfer.setOnAction(e -> transferButton.execute());
        SetATM.setOnAction(e -> SetAtm.execute());
        logoutButton.setOnAction(e -> primaryStage.setScene(mainScene));


        adminDashboard.getChildren().addAll(welcomeLabel, logoutButton, AddClient, DeleteClient, AllClients, SetATM, PinSwitch, CashWithdrawal, CashDeposit, Transfer);
        Scene scene = new Scene(adminDashboard, 400, 300);
        primaryStage.setScene(scene);
    }
}
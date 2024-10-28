import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.HashMap;

public class ClientDashboard extends Panel{

    private Stage primaryStage;
    private Scene mainScene;
    private ContClient loggedInClient;
    private HashMap<Companie, Integer> actiuniClient;

    // Constructor
    public ClientDashboard(Stage primaryStage, Scene mainScene, ContClient loggedInClient) {
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;
        this.loggedInClient = loggedInClient;
        this.actiuniClient = new HashMap<>();
    }

    public void showClientDashboard() {

        Pane clientDashboard = new Pane();
        clientDashboard.setStyle("-fx-background-color: lightgray;");

        Label welcomeLabel = new Label("Welcome, " + loggedInClient.getNume() + "!");
        welcomeLabel.setLayoutX(150);
        welcomeLabel.setLayoutY(20);

        Button cashDepButton = new Button("Cash Deposit");
        cashDepButton.setLayoutX(30);
        cashDepButton.setLayoutY(30);

        cashDepButton.setOnAction(e -> {
            CashDepButton cashDeposit = new CashDepButton(primaryStage, this, loggedInClient);
            cashDeposit.execute();
        });

        Button withdrawalButton = new Button("Withdrawal");
        withdrawalButton.setLayoutX(30);
        withdrawalButton.setLayoutY(70);

        withdrawalButton.setOnAction(e -> {
            WithdrawalButton withdraw = new WithdrawalButton(primaryStage, this, loggedInClient);
            withdraw.execute();
        });

        Button accountInfoButton = new Button("Account Info");
        accountInfoButton.setLayoutX(30);
        accountInfoButton.setLayoutY(110);

        accountInfoButton.setOnAction(e -> {
            AccInfo accInfo = new AccInfo(primaryStage, this, loggedInClient);
            accInfo.execute();
        });

        Button transferButton = new Button("Transfer");
        transferButton.setLayoutX(290);
        transferButton.setLayoutY(110);
        transferButton.setOnAction(e -> {
            TransferButon transfer = new TransferButon(primaryStage, this, loggedInClient);
            transfer.execute();
        });

        Button atmInfoButton = new Button("ATM Info");
        atmInfoButton.setLayoutX(290);
        atmInfoButton.setLayoutY(30);
        atmInfoButton.setOnAction(e -> {
            ATMInfo atmInfo = new ATMInfo(primaryStage, this);
            atmInfo.execute();
        });

        Button showPricesButton = new Button("Show Prices");
        showPricesButton.setLayoutX(290);
        showPricesButton.setLayoutY(70);
        showPricesButton.setOnAction(e -> {
            ShowPricesButton showPrices = new ShowPricesButton(primaryStage);
            showPrices.execute();
        });

        Button eWalletButton = new Button("Wallet");
        eWalletButton.setLayoutX(30);
        eWalletButton.setLayoutY(150);
        eWalletButton.setOnAction(e->{
            eWallet eWalletbutton=new eWallet(loggedInClient,actiuniClient);
            eWalletbutton.execute();
        });

        Button buyActionsButton = new Button("Buy Actions");
        buyActionsButton.setLayoutX(290);
        buyActionsButton.setLayoutY(150);
        buyActionsButton.setOnAction(e -> {
            buyActions buyActions = new buyActions(primaryStage, loggedInClient, actiuniClient);
            buyActions.execute();
        });

        Button sellActionsButton = new Button("Sell Actions");
        sellActionsButton.setLayoutX(290);
        sellActionsButton.setLayoutY(190);
        sellActionsButton.setOnAction(e -> {
            SellActions sellActions = new SellActions(primaryStage, loggedInClient, actiuniClient);
            sellActions.execute();
        });
        Button grafic = new Button("Data");
        grafic.setLayoutX(30);
        grafic.setLayoutY(190);
        grafic.setOnAction(e -> openChart());

        Button logoutButton = new Button("Logout");
        logoutButton.setLayoutX(170);
        logoutButton.setLayoutY(270);

        Button loanCalculatorButton = new Button("Loan Calculator");
        loanCalculatorButton.setLayoutX(150);
        loanCalculatorButton.setLayoutY(220);

        loanCalculatorButton.setOnAction(e -> {
            LoanCalculatorButton loanCalculator = new LoanCalculatorButton(primaryStage, this, loggedInClient, this.mainScene);
            loanCalculator.showLoanCalculator();
        });

        clientDashboard.getChildren().add(loanCalculatorButton);
        logoutButton.setOnAction(e -> primaryStage.setScene(mainScene));

        clientDashboard.getChildren().addAll(welcomeLabel, cashDepButton, buyActionsButton, sellActionsButton,
                eWalletButton, withdrawalButton, accountInfoButton, transferButton, atmInfoButton,
                showPricesButton, logoutButton,grafic);

        Scene scene = new Scene(clientDashboard, 400, 300);
        primaryStage.setScene(scene);
    }
    private void openChart() {
        StockChartFX chartFX = new StockChartFX();
        chartFX.start(new Stage());
    }
}

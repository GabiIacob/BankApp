import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoanCalculatorButton {

    private Stage primaryStage;
    private ClientDashboard clientDashboard;
    private ContClient loggedInClient;
    private Scene mainScene;

    public LoanCalculatorButton(Stage primaryStage, ClientDashboard clientDashboard, ContClient loggedInClient, Scene mainScene) {
        this.primaryStage = primaryStage;
        this.clientDashboard = clientDashboard;
        this.loggedInClient = loggedInClient;
        this.mainScene = mainScene;
    }

    public void showLoanCalculator() {
        Stage loanStage = new Stage();
        Pane loanPane = new Pane();

        Label loanAmountLabel = new Label("Loan Amount:");
        loanAmountLabel.setLayoutX(30);
        loanAmountLabel.setLayoutY(30);

        TextField loanAmountField = new TextField();
        loanAmountField.setLayoutX(150);
        loanAmountField.setLayoutY(30);

        Label interestRateLabel = new Label("Interest Rate (%):");
        interestRateLabel.setLayoutX(30);
        interestRateLabel.setLayoutY(70);

        TextField interestRateField = new TextField();
        interestRateField.setLayoutX(150);
        interestRateField.setLayoutY(70);

        Label periodLabel = new Label("Period (years):");
        periodLabel.setLayoutX(30);
        periodLabel.setLayoutY(110);

        TextField periodField = new TextField();
        periodField.setLayoutX(150);
        periodField.setLayoutY(110);

        Button calculateButton = new Button("Calculate");
        calculateButton.setLayoutX(150);
        calculateButton.setLayoutY(150);

        Label monthlyPaymentLabel = new Label();
        monthlyPaymentLabel.setLayoutX(30);
        monthlyPaymentLabel.setLayoutY(190);

        Button backButton = new Button("Back");
        backButton.setLayoutX(150);
        backButton.setLayoutY(250);

        calculateButton.setOnAction(e -> {
            try {
                double loanAmount = Double.parseDouble(loanAmountField.getText());
                double interestRate = Double.parseDouble(interestRateField.getText());
                int years = Integer.parseInt(periodField.getText());

                double monthlyInterestRate = interestRate / 12 / 100;
                int totalMonths = years * 12;
                double monthlyPayment = (loanAmount * monthlyInterestRate) /
                        (1 - Math.pow(1 + monthlyInterestRate, -totalMonths));

                monthlyPaymentLabel.setText("Monthly Payment: " + String.format("%.2f", monthlyPayment));

                startLoanDeduction(monthlyPayment);

            } catch (NumberFormatException ex) {
                monthlyPaymentLabel.setText("Invalid input!");
            }
        });

        backButton.setOnAction(e -> {
            loanStage.close();
            clientDashboard.showClientDashboard();
        });

        loanPane.getChildren().addAll(loanAmountLabel, loanAmountField, interestRateLabel, interestRateField,
                periodLabel, periodField, calculateButton, monthlyPaymentLabel, backButton);

        Scene loanScene = new Scene(loanPane, 400, 300);
        loanStage.setScene(loanScene);
        loanStage.show();
    }

    private void startLoanDeduction(double monthlyPayment) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
            if (loggedInClient.getSuma() >= monthlyPayment) {
                loggedInClient.retragere2(monthlyPayment);
                System.out.println("Rata lunara dedusa. Sold actual: " + loggedInClient.getSuma() + " RON.");
            } else {
                System.out.println("Fonduri insuficiente. Alimentati contul.");
                ((Timeline) e.getSource()).stop();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}

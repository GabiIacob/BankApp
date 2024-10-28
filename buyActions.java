import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.HashMap;

public class buyActions {

    private Stage primaryStage;
    private ContClient loggedInClient;
    private HashMap<Companie, Integer> actiuniClient;

    public buyActions(Stage primaryStage, ContClient loggedInClient, HashMap<Companie, Integer> actiuniClient) {
        this.primaryStage = primaryStage;
        this.loggedInClient = loggedInClient;
        this.actiuniClient = actiuniClient;
    }

    public void execute() {
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 20; -fx-background-color: white;");

        ObservableList<Companie> companiiList = FXCollections.observableArrayList(Bursa.getCompanii());
        ComboBox<Companie> companieComboBox = new ComboBox<>(companiiList);
        companieComboBox.setPromptText("Select a company");

        Label actiuniDisponibileLabel = new Label();
        Label pretActiuneLabel = new Label();

        companieComboBox.setOnAction(e -> {
            Companie selectedCompanie = companieComboBox.getSelectionModel().getSelectedItem();
            if (selectedCompanie != null) {
                actiuniDisponibileLabel.setText("Shares Available: " + selectedCompanie.getNumarActiuni());
                pretActiuneLabel.setText("Price per share: " + selectedCompanie.getPretActiune() + " RON");
            }
        });

        TextField actiuniInputField = new TextField();
        actiuniInputField.setPromptText("Number of shares to buy");

        Button confirmButton = new Button("Confirm Purchase");
        confirmButton.setOnAction(e -> {
            Companie selectedCompanie = companieComboBox.getSelectionModel().getSelectedItem();
            if (selectedCompanie != null) {
                int numarActiuniDeCumparat;
                try {
                    numarActiuniDeCumparat = Integer.parseInt(actiuniInputField.getText());

                    if (numarActiuniDeCumparat <= 0) {
                        showAlert("Invalid number", "Please enter a valid number of shares.");
                        return;
                    }

                    if (numarActiuniDeCumparat > selectedCompanie.getNumarActiuni()) {
                        showAlert("Insufficient shares", "Not enough shares available.");
                        return;
                    }

                    double costTotal = numarActiuniDeCumparat * Double.parseDouble(selectedCompanie.getPretActiune());

                    if (loggedInClient.suma < costTotal) {
                        showAlert("Insufficient funds", "You don't have enough money to make this purchase.");
                        return;
                    }

                    loggedInClient.suma -= costTotal;
                    selectedCompanie.setNumarActiuni(selectedCompanie.getNumarActiuni() - numarActiuniDeCumparat);

                    actiuniClient.put(selectedCompanie, actiuniClient.getOrDefault(selectedCompanie, 0) + numarActiuniDeCumparat);

                    showAlert("Purchase successful", "You have successfully purchased " + numarActiuniDeCumparat + " shares of " + selectedCompanie.getNume());

                } catch (NumberFormatException ex) {
                    showAlert("Invalid input", "Please enter a valid number.");
                }
            } else {
                showAlert("No company selected", "Please select a company.");
            }
        });

        vbox.getChildren().addAll(
                companieComboBox,
                actiuniDisponibileLabel,
                pretActiuneLabel,
                actiuniInputField,
                confirmButton
        );

        Scene scene = new Scene(vbox, 300, 250);
        Stage buyStage = new Stage();
        buyStage.setScene(scene);
        buyStage.setTitle("Buy Shares");
        buyStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public int getActiuniDetinute(Companie companie) {
        return actiuniClient.getOrDefault(companie, 0);
    }
}

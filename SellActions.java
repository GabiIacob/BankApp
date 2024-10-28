import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;

public class SellActions {

    private Stage primaryStage;
    private ContClient loggedInClient;
    private HashMap<Companie, Integer> actiuniClient;

    public SellActions(Stage primaryStage, ContClient loggedInClient, HashMap<Companie, Integer> actiuniClient) {
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

        Label actiuniDetinuteLabel = new Label();
        Label pretActiuneLabel = new Label();

        companieComboBox.setOnAction(e -> {
            Companie selectedCompanie = companieComboBox.getSelectionModel().getSelectedItem();
            if (selectedCompanie != null) {
                actiuniDetinuteLabel.setText("Your Shares: " + actiuniClient.getOrDefault(selectedCompanie, 0));
                pretActiuneLabel.setText("Price per share: " + selectedCompanie.getPretActiune() + " RON");
            }
        });

        TextField actiuniInputField = new TextField();
        actiuniInputField.setPromptText("Number of shares to sell");

        Button confirmButton = new Button("Confirm Sale");
        confirmButton.setOnAction(e -> {
            Companie selectedCompanie = companieComboBox.getSelectionModel().getSelectedItem();
            if (selectedCompanie != null) {
                int numarActiuniDeVandut;
                try {
                    numarActiuniDeVandut = Integer.parseInt(actiuniInputField.getText());

                    if (numarActiuniDeVandut <= 0) {
                        showAlert("Invalid number", "Please enter a valid number of shares.");
                        return;
                    }

                    int actiuniDetinute = actiuniClient.getOrDefault(selectedCompanie, 0);
                    if (numarActiuniDeVandut > actiuniDetinute) {
                        showAlert("Insufficient shares", "You do not have enough shares to sell.");
                        return;
                    }

                    double venitTotal = numarActiuniDeVandut * Double.parseDouble(selectedCompanie.getPretActiune());

                    loggedInClient.suma += venitTotal;
                    selectedCompanie.setNumarActiuni(selectedCompanie.getNumarActiuni() + numarActiuniDeVandut);

                    actiuniClient.put(selectedCompanie, actiuniDetinute - numarActiuniDeVandut);

                    showAlert("Sale successful", "You have successfully sold " + numarActiuniDeVandut + " shares of " + selectedCompanie.getNume());

                } catch (NumberFormatException ex) {
                    showAlert("Invalid input", "Please enter a valid number.");
                }
            } else {
                showAlert("No company selected", "Please select a company.");
            }
        });

        vbox.getChildren().addAll(
                companieComboBox,
                actiuniDetinuteLabel,
                pretActiuneLabel,
                actiuniInputField,
                confirmButton
        );

        Scene scene = new Scene(vbox, 300, 250);
        Stage sellStage = new Stage();
        sellStage.setScene(scene);
        sellStage.setTitle("Sell Shares");
        sellStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

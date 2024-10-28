import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;

public class eWallet {

    private ContClient loggedInClient;
    private HashMap<Companie, Integer> actiuniClient;

    public eWallet(ContClient loggedInClient, HashMap<Companie, Integer> actiuniClient) {
        this.loggedInClient = loggedInClient;
        this.actiuniClient = actiuniClient;
    }

    public void execute() {
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 20; -fx-background-color: white;");

        Label titleLabel = new Label("Your eWallet");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ListView<String> actiuniListView = new ListView<>();
        ObservableList<String> actiuniList = FXCollections.observableArrayList();

        for (Companie companie : actiuniClient.keySet()) {
            int numarActiuni = actiuniClient.get(companie);
            double pretActiune = Double.parseDouble(companie.getPretActiune());
            actiuniList.add(companie.getNume() + ": " + numarActiuni + " shares @ " + pretActiune + " RON each");
        }

        actiuniListView.setItems(actiuniList);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        });

        vbox.getChildren().addAll(titleLabel, actiuniListView, closeButton);

        Scene scene = new Scene(vbox, 300, 250);
        Stage eWalletStage = new Stage();
        eWalletStage.setScene(scene);
        eWalletStage.setTitle("eWallet");
        eWalletStage.show();
    }
}

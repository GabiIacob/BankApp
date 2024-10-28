import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowPricesButton {
    private Stage primaryStage;

    // Constructor
    public ShowPricesButton(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void execute() {

        TableView<Companie> tableView = new TableView<>();

        TableColumn<Companie, String> nameColumn = new TableColumn<>("Company Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));

        TableColumn<Companie, String> priceColumn = new TableColumn<>("Price (RON)");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("pretActiune"));

        TableColumn<Companie, Integer> sharesColumn = new TableColumn<>("Shares Available");
        sharesColumn.setCellValueFactory(new PropertyValueFactory<>("numarActiuni"));

        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(priceColumn);
        tableView.getColumns().add(sharesColumn);

        ObservableList<Companie> data = FXCollections.observableArrayList(Bursa.getCompanii());
        tableView.setItems(data);

        VBox vbox = new VBox(tableView);
        vbox.setStyle("-fx-padding: 10; -fx-background-color: white;");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        });
        vbox.getChildren().add(closeButton);

        Scene scene = new Scene(vbox, 400, 300);
        Stage priceStage = new Stage();
        priceStage.setScene(scene);
        priceStage.setTitle("Stock Prices");
        priceStage.show();
    }
}

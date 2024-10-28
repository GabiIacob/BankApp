import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ATMInfo {
    private Stage primaryStage;
    private ClientDashboard clientDashboard;

    public ATMInfo(Stage primaryStage, ClientDashboard clientDashboard) {
        this.primaryStage = primaryStage;
        this.clientDashboard = clientDashboard;
    }

    public void execute() {
        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: white;");


        Image image;
        try {
            FileInputStream input = new FileInputStream("C:/Users/gabii/Desktop/bancomate.jpg");
            image = new Image(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }


        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(800);
        imageView.setPreserveRatio(true);

        vbox.getChildren().add(imageView);

        Button backButton = new Button("Go Back");
        backButton.setOnAction(e -> clientDashboard.showClientDashboard());

        vbox.getChildren().add(backButton);

        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ATM Information");
        primaryStage.show();
    }
}

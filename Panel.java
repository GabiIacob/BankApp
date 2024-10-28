import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Random;
import javax.swing.*;
import java.io.File;

public class Panel extends Application {
    Administrator admin = new Administrator("Mihai Morar", "sefdebanca");
    private Pane mainPane;
    private Scene mainScene;

    @Override
    public void start(Stage primaryStage) {
        mainPane = createMainPane();
        mainScene = new Scene(mainPane, 400, 300);

        primaryStage.setTitle("Open Bank App");
        primaryStage.setScene(mainScene);
        primaryStage.show();

        Button welcomeButton = new Button("WELCOME");
        welcomeButton.setLayoutX(100);
        welcomeButton.setLayoutY(130);
        welcomeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-border-color: black;");

        mainPane.getChildren().add(welcomeButton);

        welcomeButton.setOnAction(event -> showSecondPane(primaryStage));
    }

    private Pane createMainPane() {
        Pane mainPane = new Pane();

        String imagePath = "C:/Users/gabii/Desktop/fundal1.jpg";
        Image backgroundImage = new Image(new File(imagePath).toURI().toString());

        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
        );

        mainPane.setBackground(new Background(background));

        return mainPane;
    }

    private void showSecondPane(Stage primaryStage) {
        Pane secondPane = createMainPane();
        Button AdminButton = new Button("Admin register");
        AdminButton.setLayoutX(70);
        AdminButton.setLayoutY(120);
        AdminButton.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-border-color: black;");

        Button ClientButton = new Button("Client register");
        ClientButton.setLayoutX(70);
        ClientButton.setLayoutY(170);
        ClientButton.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-border-color: black;");

        AdminButton.setOnAction(e -> showAdminRegistrationPane(primaryStage));
        ClientButton.setOnAction(e -> showClientRegistrationPane(primaryStage));

        secondPane.getChildren().addAll(AdminButton, ClientButton);
        primaryStage.setScene(new Scene(secondPane, 400, 300));
    }


    private void showAdminRegistrationPane(Stage primaryStage) {
        Pane adminPane = new Pane();
        adminPane.setStyle("-fx-background-color: white;");

        Label label = new Label("Admin Registration");
        label.setLayoutX(150);
        label.setLayoutY(20);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Admin Username");
        usernameField.setLayoutX(100);
        usernameField.setLayoutY(80);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Admin Password");
        passwordField.setLayoutX(100);
        passwordField.setLayoutY(120);

        Button registerButton = new Button("Register Admin");
        registerButton.setLayoutX(150);
        registerButton.setLayoutY(160);

        Button backButton = new Button("Go Back");
        backButton.setLayoutX(150);
        backButton.setLayoutY(200);


        registerButton.setOnAction(e -> {
            if (usernameField.getText().equals(admin.nume) && passwordField.getText().equals(admin.parola)) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to log in?", "Confirmation",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {

                    Stage modalStage = new Stage();
                    modalStage.initModality(Modality.APPLICATION_MODAL);
                    modalStage.initOwner(primaryStage);
                    modalStage.setTitle("Product Key Verification");

                    Label productKeyLabel = new Label("Please enter your product key:");
                    productKeyLabel.setLayoutX(50);
                    productKeyLabel.setLayoutY(20);

                    TextField productKeyField = new TextField();
                    productKeyField.setLayoutX(50);
                    productKeyField.setLayoutY(60);
                    productKeyField.setPromptText("Enter product key");

                    Button submitButton = new Button("Submit");
                    submitButton.setLayoutX(90);
                    submitButton.setLayoutY(100);

                    Random random = new Random();
                    int a = random.nextInt(1000000);
                    String b = String.valueOf(a);


                    System.out.println("Your productKey is: " + b);  // Corectat aici
                    submitButton.setOnAction(event -> {

                        String productKey = productKeyField.getText();

                        if (productKey.equals(b)) {
                            modalStage.close();

                            AdminDashboard adminDashboard = new AdminDashboard(primaryStage, mainScene);
                            adminDashboard.showAdminDashboard();
                        } else {

                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Invalid Product Key");
                            alert.setHeaderText("The product key is incorrect.");
                            alert.setContentText("Please enter a valid product key.");
                            alert.showAndWait();
                        }
                    });

                    Pane modalPane = new Pane(productKeyLabel, productKeyField, submitButton);
                    Scene modalScene = new Scene(modalPane, 300, 200);


                    modalStage.setScene(modalScene);
                    modalStage.showAndWait();

                } else if (result == JOptionPane.NO_OPTION) {

                    primaryStage.setScene(mainScene);
                }
            } else {
                JOptionPane.showConfirmDialog(null, "USERNAME/PASSWORD WRONG", "Result",
                        JOptionPane.CLOSED_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                primaryStage.setScene(mainScene);
            }
        });

        backButton.setOnAction(e -> primaryStage.setScene(mainScene));


        adminPane.getChildren().addAll(label, usernameField, passwordField, registerButton, backButton);
        primaryStage.setScene(new Scene(adminPane, 400, 300));
    }


    private void showClientRegistrationPane(Stage primaryStage) {
        Pane clientPane = new Pane();
        clientPane.setStyle("-fx-background-color: white;");

        Label label = new Label("Client Login");
        label.setLayoutX(150);
        label.setLayoutY(20);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Client Username");
        usernameField.setLayoutX(100);
        usernameField.setLayoutY(80);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Client Password");
        passwordField.setLayoutX(100);
        passwordField.setLayoutY(120);

        Button loginButton = new Button("Login");
        loginButton.setLayoutX(150);
        loginButton.setLayoutY(160);

        Button backButton = new Button("Go Back");
        backButton.setLayoutX(150);
        backButton.setLayoutY(200);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            ContClient loggedInClient = null;

            for (int i = 0; i < ContClient.clientCounter; i++) {
                ContClient client = ContClient.clienti[i];
                if (client.getNume().equals(username) && client.getParola().equals(password)) {
                    loggedInClient = client;
                    break;
                }
            }

            if (loggedInClient != null) {
                ClientDashboard clientDashboard = new ClientDashboard(primaryStage, mainScene, loggedInClient);
                clientDashboard.showClientDashboard();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText("Incorrect Username or Password");
                alert.setContentText("Please try again.");
                alert.showAndWait();
            }
        });

        backButton.setOnAction(e -> primaryStage.setScene(mainScene));

        clientPane.getChildren().addAll(label, usernameField, passwordField, loginButton, backButton);
        primaryStage.setScene(new Scene(clientPane, 400, 300));
    }

}

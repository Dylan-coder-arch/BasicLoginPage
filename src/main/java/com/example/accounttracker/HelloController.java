package com.example.accounttracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HelloController {

    public TextField txtEmail;
    public TextField txtPassword;

    public static final List<String> savedCredentials = new ArrayList<>();

    public void loginButtonHandler(ActionEvent e) throws FileNotFoundException {
        // Extracting user details
        String userEmail = txtEmail.getText();
        String userPass = txtPassword.getText();
        System.out.println("This is user pass: " + userPass);
        System.out.println("This is user email: " + userEmail);


        // Compare with list of email and password in textfile (Database implementation later)
        if (!savedCredentials.contains(userEmail) || (!savedCredentials.get(savedCredentials.indexOf(userEmail) + 1).equals(userPass))) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error!, login information maybe be wrong");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Success");
        alert.setContentText("The login has worked!");
        alert.showAndWait();

    }

    public void registerButtonHandler(ActionEvent e) throws IOException {
        // display a different scene
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register-view.fxml")));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        File txtCredentials = new File("credentials.txt");
        System.out.println(txtCredentials.getName());
        try {

            Scanner myReader = new Scanner(txtCredentials);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String email = data.substring(0, data.indexOf(":"));
                System.out.println(email);
                String pass = data.substring(data.indexOf(":") + 1);
                System.out.println(pass);
                savedCredentials.add(email);
                savedCredentials.add(pass);
            }
            myReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
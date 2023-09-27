package com.example.accounttracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class RegisterViewController {


    public TextField txtREmail;
    public TextField txtRPassword;
    public TextField txtCRPassword;

    public List<String> savedCredentials = HelloController.savedCredentials;

    public void RegisterEventHandler(ActionEvent e) {
        // So if any of the fields are empty - return
        if (txtREmail.getText().isBlank() || txtRPassword.getText().isBlank() || txtCRPassword.getText().isBlank()) {
            Alert blankFields = new Alert(Alert.AlertType.ERROR, "Error!, please don't leave any blank fields.");
            blankFields.showAndWait();
            return;
        }
        // If the email already exists - return
        if (savedCredentials.contains(txtREmail.getText())) {
            Alert alreadyExist = new Alert(Alert.AlertType.ERROR, "Error!, this email is already in use.");
            alreadyExist.showAndWait();
            return;
        }

        // if the passwords do not match - return
        if (!txtRPassword.getText().equals(txtCRPassword.getText())) {
            Alert mismatchPassword = new Alert(Alert.AlertType.ERROR, "Error!, please make sure your passwords match.");
            mismatchPassword.showAndWait();
            return;
        }
        // If the password do not meet complexity - return
        // let's just make complexity 8 characters for now -- will update complexity later
        if (txtRPassword.getText().length() < 8) {
            Alert complexityError = new Alert(Alert.AlertType.ERROR, "Error!, please make sure your password is at least 8 characters long.");
            complexityError.showAndWait();
            return;
        }

        // if all checks pass, then we'll just add it to txtFile
        File txtCredentials = new File("credentials.txt");
        try {
            FileWriter fw = new FileWriter(txtCredentials, true);
            fw.write(txtREmail.getText() + ":" + txtRPassword.getText());
            fw.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        Alert successRegister = new Alert(Alert.AlertType.CONFIRMATION, "Successfully registered!");
        successRegister.showAndWait();
        // go back to login here
        // call BackHandler
    }

    public void BackHandler(ActionEvent e) throws IOException {
        // swap scene back to log in scene
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}

package com.example.accounttracker;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelloController {

    public TextField txtEmail;
    public TextField txtPassword;

    private final List<String> savedCredentials = new ArrayList<>();

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

    public void registerButtonHandler(ActionEvent e) {
        // display a different scene



    }

    public void onLoad() {
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
package com.palabraventura.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PalabraventuraController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
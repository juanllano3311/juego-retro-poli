package com.palabraventura.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class PalabraventuraApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/palabraventura/view/start-view.fxml"));
        URL url = getClass().getResource("/com/palabraventura/images/fondo.jpg");
        System.out.println(url);
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Palabraventura");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

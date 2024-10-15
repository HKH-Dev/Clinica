package co.edu.uniquindio.clinica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClinicaApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(ClinicaApplication.class.getResource(""));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Clinica");
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(ClinicaApplication.class, args);
    }




}

package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.model.Clinica;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import java.net.URL;
import java.util.ResourceBundle;

public class PanelControlador2 implements Initializable {


    @FXML
    private Tab tab1;

    @FXML
    private Tab tab2;

    @FXML
    private Tab tab3;

    @FXML
    private Tab tab4;

    private final Clinica clinica;

    public PanelControlador2() {
        this.clinica = new Clinica(); // Se crea una única instancia de la clase Clinica
    }



    @Override
    public void initialize (URL location, ResourceBundle resources) {
        try{
            cargarTab(tab1, "/registroPaciente.fxml");
            cargarTab(tab2, "/listaPacientes.fxml");
            //Agregar los otros tabs…
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarTab(Tab tab, String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent content = loader.load();
        ((AbstractControlador)loader.getController()).inicializarClinica(clinica);
        tab.setContent(content);
    }
}


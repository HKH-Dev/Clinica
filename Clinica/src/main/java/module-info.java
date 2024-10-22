module co.edu.uniquindio.clinica {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires static lombok;
    requires org.simplejavamail.core;
    requires org.simplejavamail;

    opens co.edu.uniquindio.clinica to javafx.fxml;
    exports co.edu.uniquindio.clinica;
    exports co.edu.uniquindio.clinica.controladores;
    opens co.edu.uniquindio.clinica.controladores to javafx.fxml;
    exports co.edu.uniquindio.clinica.model.factory;
    opens co.edu.uniquindio.clinica.model.factory to javafx.fxml;
    exports co.edu.uniquindio.clinica.model.servicio; // Add this line
    opens co.edu.uniquindio.clinica.model to javafx.base;
    exports co.edu.uniquindio.clinica.model;
}
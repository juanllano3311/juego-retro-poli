module com.palabraventura.palabraventura {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires junit;
    requires java.desktop;

    exports com.palabraventura.app;
    opens com.palabraventura.app to javafx.fxml;

    exports com.palabraventura.controller;
    opens com.palabraventura.controller to javafx.fxml;

    exports com.palabraventura.model;
    opens com.palabraventura.model to javafx.fxml;

    opens com.palabraventura.view to javafx.fxml; // <-- necesario si hay controladores asociados al FXML
}

module com.example.typathon2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.logging;
    requires java.sql;

    opens com.example.typathon2 to javafx.fxml;
    exports com.example.typathon2;
    exports com.example.typathon2.Controllers;
    opens com.example.typathon2.Controllers to javafx.fxml;
    opens com.example.typathon2.dao to javafx.fxml;
    exports com.example.typathon2.dao;
    exports com.example.typathon2.Models;
    opens com.example.typathon2.Models to javafx.fxml;
}
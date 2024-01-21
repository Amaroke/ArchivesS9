@SuppressWarnings("Java9RedundantRequiresStatement") module projet.aos.frontendappvehicules {
    requires javafx.graphics;
    requires javafx.fxml;
    requires transitive javafx.controls;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens projet.aos.frontendappvehicules to javafx.fxml;

    exports projet.aos.frontendappvehicules;
    exports projet.aos.frontendappvehicules.controllers;

    opens projet.aos.frontendappvehicules.controllers to javafx.fxml;
}
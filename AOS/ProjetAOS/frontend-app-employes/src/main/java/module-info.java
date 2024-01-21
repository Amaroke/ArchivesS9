@SuppressWarnings("Java9RedundantRequiresStatement") module projet.aos.frontendappemployes {
    requires javafx.graphics;
    requires javafx.fxml;
    requires transitive javafx.controls;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens projet.aos.frontendappemployes to javafx.fxml;

    exports projet.aos.frontendappemployes;
    exports projet.aos.frontendappemployes.controllers;

    opens projet.aos.frontendappemployes.controllers to javafx.fxml;
}
module com.viruswar.viruswarmultiplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.viruswarmultiplayer to javafx.fxml;
    exports com.viruswarmultiplayer;

    exports com.viruswarmultiplayer.Client;
    opens com.viruswarmultiplayer.Client to javafx.fxml;
    opens com.viruswarmultiplayer.View to javafx.fxml;
    opens com.viruswarmultiplayer.images to javafx.fxml;
    exports com.viruswarmultiplayer.CreateGame;
    exports com.viruswarmultiplayer.Exception;
    opens com.viruswarmultiplayer.Exception to javafx.fxml;
    exports com.viruswarmultiplayer.GameModel;
    opens com.viruswarmultiplayer.GameModel to javafx.fxml;
}
module bomber_man {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;
    requires javafx.media;

    opens bomber_man to javafx.fxml;
    exports bomber_man;
}
module com.example.dnd2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dnd2 to javafx.fxml;
    exports com.example.dnd2;
}
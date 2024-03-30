module org.example.superfastline {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.superfastline to javafx.fxml;
    exports org.example.superfastline;
}
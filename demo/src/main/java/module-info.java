module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.assignment to javafx.fxml;
    exports com.assignment;
}
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SpecialAlert {
    
    Alert alert = new Alert(AlertType.NONE);
    
    public void show(String title, String message, AlertType alertType) {
        alert.setTitle(title);
        alert.setHeaderText(title);  // next to the warning or error photo
        alert.setContentText(message);
        alert.setAlertType(alertType);  // error , warning , information or confirmation
        alert.showAndWait();
    }
}

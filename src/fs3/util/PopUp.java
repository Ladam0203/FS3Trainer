package fs3.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class PopUp {
    public static void showError(String message) {
        showExceptionDialog(message, null);
    }

    public static void showError(String message, Throwable throwable) {
        showExceptionDialog(message, throwable);
    }

    //based on java2s.com - A dialog that shows and prints the stacktrace
    private static void showExceptionDialog(String message, Throwable throwable) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Oops!");
        alert.setContentText(message);

        //an exception was thrown
        if (throwable != null) {
            alert.setHeaderText("We tried our best...");

            throwable.printStackTrace();

            Label label = new Label("Stacktrace:");

            TextArea textArea = new TextArea(throwable.getMessage());
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            alert.getDialogPane().setExpandableContent(expContent);
        } //no exception was thrown
        else {
            alert.setHeaderText("Something is not right...");
        }

        alert.showAndWait();
    }
}

/**
 * @Description: The AlertInfo is used to show a alert dialog
 * @Author: Li Liao
 * @Date: 03/07/2021
 */

package util;

import javafx.scene.control.Alert;

public class AlertInfo {

    /**
     * Show a simple alert dialog.
     *
     * @param title     alert title
     * @param content   alert message
     */
    public static void showSimpleDialog (String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Show a alert error alert.
     *
     * @param title     error title
     * @param content   error message
     */
    public static void showErrorMessage (String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();

    }
}

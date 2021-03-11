/**
 * @Description: add_item stage controller
 * @Author: Li Liao
 * @Date: 03/04/2021
 */

package service.add_item;

import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import util.AlertInfo;
import java.net.URL;
import java.util.ResourceBundle;


public class itemAddController implements Initializable {
    private DatabaseHandler handler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handler = DatabaseHandler.getInstance();
    }

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtUnit;
    @FXML
    private TextField txtManufacturer;

    /**
     * This method is called to end current window and return to Item page
     *
     * @param event event
     */
    @FXML
    private void backButtonPressed (ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    /**
     * This method is called to add input data, alert for invalidate execution or inform when successful
     * Then clear textfield
     *
     * @param event event
     */
    @FXML
    private void saveButtonPressed(ActionEvent event) {
        String name = txtName.getText();
        String unit = txtUnit.getText();
        String manufacturer = txtManufacturer.getText();
        if (name.isEmpty() || unit.isEmpty() || manufacturer.isEmpty()) {
            AlertInfo.showSimpleDialog("Insufficient Data","Please enter data in all fields.");
        }
            DatabaseHandler handler = DatabaseHandler.getInstance();
            String update = String.format(
                            "INSERT INTO Items (name, unit, manufacturer) VALUES (\"%s\", \"%s\", \"%s\");",
                            name,
                            unit,
                            manufacturer);
            int result = handler.execUpdate(update);
            if (result > 0) {
                AlertInfo.showSimpleDialog("Success", "item data added.");
            }
            clearInput();
    }

    /**
     * Is called by the saveButtonPressed method to clear input text.
     */
    private void clearInput() {
        txtName.clear();
        txtUnit.clear();
        txtManufacturer.clear();
    }
}

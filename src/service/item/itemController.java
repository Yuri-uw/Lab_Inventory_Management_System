package service.item;

import database.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.stage.StageStyle;
import model.Item;
import util.AlertInfo;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @Description: Item page Controller
 * @Author: Li Liao
 * @Date: 03/04/2021
 */

public class itemController implements Initializable {

    private ObservableList<Item> observableList = FXCollections.observableArrayList();
    // private final static Logger logger = Logger.getLogger(itemController.class.getName());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumn();
        loadData();
    }

    @FXML
    private TableView<Item> table;
    @FXML
    private TableColumn<Item, String> col_item_no;
    @FXML
    private TableColumn<Item, String> col_name;
    @FXML
    private TableColumn<Item, String> col_unit;
    @FXML
    private TableColumn<Item, String> col_manufacturer;
    @FXML
    private TableColumn<Item, String> col_status;

    @FXML
    private void delete() {
        ObservableList<Item> selectedItems = table.getSelectionModel().getSelectedItems();
        selectedItems.forEach(observableList::remove);
        ArrayList<Integer> ids = new ArrayList<>();
        for (Item item : selectedItems) {
            ids.add(item.getItemId());
        }
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String query = "DELETE FROM Items WHERE id IN (" + ids.toString() + ");";
        handler.execQuery(query);
    }

    @FXML
    private void edit() {
        Item selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            AlertInfo.showErrorMessage("No user selected", "Please select a user for edit.");
        }
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("service/add_item/item_add.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("edit Item");
            stage.setScene(scene);
            stage.show();
            stage.setOnHiding((e) -> {
                refresh(new ActionEvent());
            });
        } catch (IOException exception) {
            // logger.log(Level.SEVERE, "Exception occur", exception);
            exception.printStackTrace();
        }
    }

    @FXML
    private void refresh(ActionEvent actionEvent) {
        loadData();
    }

    @FXML
    private void add() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("service/add_item/item_add.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("add Item");
            stage.setScene(scene);
            stage.show();
            stage.setOnHiding((e) -> {
                refresh(new ActionEvent());
            });
        } catch (IOException exception) {
            // logger.log(Level.SEVERE, "Exception occur", exception);
            exception.printStackTrace();
        }
    }

    @FXML
    private void returnToHomePage(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        // loadIndex();
    }

    /**
     * load data into table from database
     */
    private void loadData() {
        observableList.clear();
        DatabaseHandler handler = DatabaseHandler.getInstance();
        ResultSet result = handler.execQuery("select * from Items;");
        try {
            while (result.next()) {
                Integer itemId = result.getInt(1);
                String name = result.getString(2);
                String unit = result.getString(3);
                String manufacturer = result.getString(4);
                observableList.add(new Item(itemId,name,unit,manufacturer));
            }
        } catch (SQLException exception) {
            // logger.log(Level.SEVERE, "Exception occur", exception);
            exception.printStackTrace();
        }
        table.setItems(observableList);
    }

    /**
     * initialize column
     */
    private void initColumn() {
        col_item_no.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        col_manufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        // col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }


    /*
    /**
     * return to home page
    private void loadIndex() {
        try {
            // load home fxml
            Parent parent = FXMLLoader.load(getClass().getResource("../../index/index.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("index");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException exception) {
            // logger.log(Level.SEVERE, "Exception occur", exception);
            exception.printStackTrace();
        }
    }
     */
}

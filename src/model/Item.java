package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    private final SimpleIntegerProperty itemId;
    private final SimpleStringProperty name;
    private final SimpleStringProperty unit;
    private final SimpleStringProperty manufacturer;
    // private final SimpleStringProperty status;

    public Item(Integer itemId, String name, String unit, String manufacturer) {
        this.itemId = new SimpleIntegerProperty(itemId);
        this.name = new SimpleStringProperty(name);
        this.unit = new SimpleStringProperty(unit);
        this.manufacturer = new SimpleStringProperty(manufacturer);
        // this.status = new SimpleStringProperty(status);
    }

    public int getItemId() {
        return itemId.get();
    }
    public void setItemId(int itemId) {
        this.itemId.set(itemId);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }

    public String getUnit() {
        return unit.get();
    }
    public void setUnit(String unit) {
        this.unit.set(unit);
    }

    public String getManufacturer() {
        return manufacturer.get();
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer.set(manufacturer);
    }
}


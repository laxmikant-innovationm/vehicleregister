package com.st.vehicleregister.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vehicle")
public class VehicleModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "fuelType")
    private String fuelType;

    @ColumnInfo(name = "regNumber")
    private String regNumber;

    @ColumnInfo(name = "make")
    private String make;

    @ColumnInfo(name = "model")
    private String model;

    @ColumnInfo(name = "transmission")
    private String transmission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
}

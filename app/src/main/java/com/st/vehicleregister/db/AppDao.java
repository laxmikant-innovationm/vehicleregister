package com.st.vehicleregister.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.st.vehicleregister.model.VehicleModel;

import java.util.List;

@Dao
public interface AppDao {

    @Insert
    void saveVehicleData(VehicleModel model);

    @Query("Select * from vehicle")
    LiveData<List<VehicleModel>> fetchVehicle();
}

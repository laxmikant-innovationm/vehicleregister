package com.st.vehicleregister.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.st.vehicleregister.R;
import com.st.vehicleregister.databinding.FragmentHomeBinding;
import com.st.vehicleregister.db.AppDatabase;
import com.st.vehicleregister.model.VehicleModel;
import com.st.vehicleregister.ui.ItemClickListener;
import com.st.vehicleregister.ui.adapter.HomeVehicleAdapter;
import com.st.vehicleregister.utils.Constants;

import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private HomeVehicleAdapter vehicleAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected FragmentHomeBinding getInflater(LayoutInflater inflater, ViewGroup container, boolean attachToRoot) {
        return FragmentHomeBinding.inflate(inflater, container, attachToRoot);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        vehicleAdapter = new HomeVehicleAdapter(new ItemClickListener() {
            @Override
            public void onItemClick(Object vehicle) {
                prepareBundleAndNavigate((VehicleModel) vehicle);
            }
        });
        binding.inctoolbar.ivBack.setVisibility(View.GONE);
        binding.rvVehicleList.setLayoutManager(new LinearLayoutManager(context));
        binding.rvVehicleList.setAdapter(vehicleAdapter);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(HomeFragmentDirections.actionHomeFragmentToCreateProfileFragment());
            }
        });
        AppDatabase.getInstance(context).appDao().fetchVehicle().observe(this, new Observer<List<VehicleModel>>() {
            @Override
            public void onChanged(List<VehicleModel> vehicleModels) {
                vehicleAdapter.setVehicleList(vehicleModels);
            }
        });
    }

    private void prepareBundleAndNavigate(VehicleModel vehicle) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_VEHCILE_MODEL, vehicle.getModel());
        bundle.putString(Constants.KEY_VEHCILE_MAKE, vehicle.getMake());
        bundle.putString(Constants.KEY_VEHCILE_FUEL, vehicle.getFuelType());
        bundle.putString(Constants.KEY_VEHCILE_TRANSMISSION, vehicle.getTransmission());
        bundle.putString(Constants.KEY_VEHICLE_REG_NUM, vehicle.getRegNumber());
        navController.navigate(R.id.action_homeFragment_to_vehicleProfileFragment, bundle);
    }
}
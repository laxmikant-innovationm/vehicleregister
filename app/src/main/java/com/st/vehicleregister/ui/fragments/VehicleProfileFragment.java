package com.st.vehicleregister.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.st.vehicleregister.databinding.FragmentVehicleProfileBinding;
import com.st.vehicleregister.utils.Constants;

public class VehicleProfileFragment extends BaseFragment<FragmentVehicleProfileBinding> {
    private String model, make, regNumber, fuelType, transmission;

    public VehicleProfileFragment() {
        // Required empty public constructor
    }

    public static VehicleProfileFragment newInstance() {
        VehicleProfileFragment fragment = new VehicleProfileFragment();
        return fragment;
    }

    @Override
    protected FragmentVehicleProfileBinding getInflater(LayoutInflater inflater, ViewGroup container, boolean attachToRoot) {
        return FragmentVehicleProfileBinding.inflate(inflater, container, attachToRoot);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        model = getArguments().getString(Constants.KEY_VEHCILE_MODEL);
        regNumber = getArguments().getString(Constants.KEY_VEHICLE_REG_NUM);
        fuelType = getArguments().getString(Constants.KEY_VEHCILE_FUEL);
        make = getArguments().getString(Constants.KEY_VEHCILE_MAKE);
        transmission = getArguments().getString(Constants.KEY_VEHCILE_TRANSMISSION);

        binding.tvVehicleName.setText(model);
        binding.tvModel.setText(model);
        binding.tvVehicleRegNumber.setText(regNumber);
        binding.tvFuel.setText(fuelType);
        binding.tvFueltype.setText(fuelType);
        binding.tvMake.setText(make);
        binding.tvTranmission.setText(transmission);

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });
    }
}
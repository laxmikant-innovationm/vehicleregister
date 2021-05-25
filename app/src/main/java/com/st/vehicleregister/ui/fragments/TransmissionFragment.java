package com.st.vehicleregister.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.st.vehicleregister.R;
import com.st.vehicleregister.databinding.FragmentTransmissionBinding;
import com.st.vehicleregister.db.AppDatabase;
import com.st.vehicleregister.db.AppExecutors;
import com.st.vehicleregister.model.VehicleModel;
import com.st.vehicleregister.ui.ItemClickListener;
import com.st.vehicleregister.ui.adapter.VehicleAdapter;
import com.st.vehicleregister.utils.Constants;


public class TransmissionFragment extends BaseFragment<FragmentTransmissionBinding> {
    private VehicleAdapter vehicleAdapter;
    String[] transmissionList = {"Manual", "Automatic"};

    public TransmissionFragment() {
        // Required empty public constructor
    }

    public static TransmissionFragment newInstance() {
        TransmissionFragment fragment = new TransmissionFragment();
        return fragment;
    }

    @Override
    protected FragmentTransmissionBinding getInflater(LayoutInflater inflater, ViewGroup container, boolean attachToRoot) {
        return FragmentTransmissionBinding.inflate(inflater, container, attachToRoot);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        binding.inctoolbar.tvTitle.setText("Select Transmission");
        binding.inctoolbar.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });
        vehicleAdapter = new VehicleAdapter(new ItemClickListener() {
            @Override
            public void onItemClick(Object transmission) {
                prepareBundleAndNavigate((String) transmission);
            }
        });
        vehicleAdapter.setCollection(transmissionList);
        binding.rvTransmissionList.setLayoutManager(new LinearLayoutManager(context));
        binding.rvTransmissionList.setAdapter(vehicleAdapter);
    }

    private void prepareBundleAndNavigate(String transmission) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                VehicleModel vehicleModel = new VehicleModel();
                vehicleModel.setFuelType(getArguments().getString(Constants.KEY_VEHCILE_FUEL));
                vehicleModel.setMake(getArguments().getString(Constants.KEY_VEHCILE_MAKE));
                vehicleModel.setModel(getArguments().getString(Constants.KEY_VEHCILE_MODEL));
                vehicleModel.setRegNumber(getArguments().getString(Constants.KEY_VEHICLE_REG_NUM));
                vehicleModel.setTransmission(transmission);
                AppDatabase.getInstance(context).appDao().saveVehicleData(vehicleModel);
                navigateToProfileScreen(transmission);
            }
        });
    }

    private void navigateToProfileScreen(String transmission) {
        AppExecutors.getInstance().mainThread().execute(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = getArguments();
                bundle.putString(Constants.KEY_VEHCILE_TRANSMISSION, transmission);
                navController.navigate(R.id.action_transmissionFragment_to_vehicleProfileFragment, bundle);
            }
        });
    }
}
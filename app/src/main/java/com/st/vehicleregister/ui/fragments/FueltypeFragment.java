package com.st.vehicleregister.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.st.vehicleregister.R;
import com.st.vehicleregister.databinding.FragmentFueltypeBinding;
import com.st.vehicleregister.ui.ItemClickListener;
import com.st.vehicleregister.ui.adapter.VehicleAdapter;
import com.st.vehicleregister.utils.Constants;

public class FueltypeFragment extends BaseFragment<FragmentFueltypeBinding> {
    private VehicleAdapter vehicleAdapter;
    String[] fuelList = {"Petrol", "Diesel", "CNG", "Petrol + CNG", "Electric", "Hybrid"};

    public FueltypeFragment() {
        // Required empty public constructor
    }

    public static FueltypeFragment newInstance() {
        FueltypeFragment fragment = new FueltypeFragment();
        return fragment;
    }

    @Override
    protected FragmentFueltypeBinding getInflater(LayoutInflater inflater, ViewGroup container, boolean attachToRoot) {
        return FragmentFueltypeBinding.inflate(inflater, container, attachToRoot);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        binding.inctoolbar.tvTitle.setText("Select Fuel Type");
        binding.inctoolbar.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });
        vehicleAdapter = new VehicleAdapter(new ItemClickListener() {
            @Override
            public void onItemClick(Object fuel) {
                prepareBundleAndNavigate((String) fuel);
            }
        });
        vehicleAdapter.setCollection(fuelList);
        binding.rvFuelList.setLayoutManager(new LinearLayoutManager(context));
        binding.rvFuelList.setAdapter(vehicleAdapter);
    }

    private void prepareBundleAndNavigate(String fuel) {
        Bundle bundle = getArguments();
        bundle.putString(Constants.KEY_VEHCILE_FUEL, fuel);
        navController.navigate(R.id.action_fuelTypeFragment_to_transmissionFragment, bundle);
    }

}
package com.st.vehicleregister.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.st.vehicleregister.R;
import com.st.vehicleregister.databinding.FragmentModelBinding;
import com.st.vehicleregister.network.ApiClient;
import com.st.vehicleregister.network.ApiInterface;
import com.st.vehicleregister.ui.ItemClickListener;
import com.st.vehicleregister.ui.adapter.VehicleAdapter;
import com.st.vehicleregister.utils.Constants;
import com.st.vehicleregister.utils.Toaster;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelFragment extends BaseFragment<FragmentModelBinding> {

    private VehicleAdapter vehicleAdapter;
    private String vehicleRegNum, vehicleClass, vehicleMake;

    public ModelFragment() {
        // Required empty public constructor
    }

    public static ModelFragment newInstance() {
        ModelFragment fragment = new ModelFragment();
        return fragment;
    }

    @Override
    protected FragmentModelBinding getInflater(LayoutInflater inflater, ViewGroup container, boolean attachToRoot) {
        return FragmentModelBinding.inflate(inflater, container, attachToRoot);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        binding.inctoolbar.tvTitle.setText("Select Model");
        binding.inctoolbar.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });
        vehicleRegNum = getArguments().getString(Constants.KEY_VEHICLE_REG_NUM);
        vehicleClass = getArguments().getString(Constants.KEY_VEHICLE_CLASS);
        vehicleMake = getArguments().getString(Constants.KEY_VEHCILE_MAKE);

        vehicleAdapter = new VehicleAdapter(new ItemClickListener() {
            @Override
            public void onItemClick(Object model) {
                prepareBundleAndNavigateToNextScreen((String) model);
            }
        });
        binding.rvList.setLayoutManager(new LinearLayoutManager(context));
        binding.rvList.setAdapter(vehicleAdapter);
        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<String[]> call = apiInterface.getModels(vehicleClass, vehicleMake);
        call.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                if (response.isSuccessful()) {
                    vehicleAdapter.setCollection(response.body());
                } else {
                    Toaster.showToast(context, "Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {

            }
        });
    }

    private void prepareBundleAndNavigateToNextScreen(String model) {
        Bundle bundle = getArguments();
        bundle.putString(Constants.KEY_VEHCILE_MODEL, model);
        navController.navigate(R.id.action_modelFragment_to_fuelTypeFragment, bundle);
    }
}
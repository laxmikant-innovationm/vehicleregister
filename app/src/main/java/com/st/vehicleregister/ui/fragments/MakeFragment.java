package com.st.vehicleregister.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.st.vehicleregister.R;
import com.st.vehicleregister.databinding.FragmentMakeBinding;
import com.st.vehicleregister.network.ApiClient;
import com.st.vehicleregister.network.ApiInterface;
import com.st.vehicleregister.ui.ItemClickListener;
import com.st.vehicleregister.ui.adapter.VehicleAdapter;
import com.st.vehicleregister.utils.Constants;
import com.st.vehicleregister.utils.Toaster;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeFragment extends BaseFragment<FragmentMakeBinding> {

    private VehicleAdapter vehicleAdapter;
    private MakeFragmentArgs args;

    public MakeFragment() {
        // Required empty public constructor
    }

    public static MakeFragment newInstance() {
        MakeFragment fragment = new MakeFragment();
        return fragment;
    }

    @Override
    protected FragmentMakeBinding getInflater(LayoutInflater inflater, ViewGroup container, boolean attachToRoot) {
        return FragmentMakeBinding.inflate(inflater, container, attachToRoot);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        binding.inctoolbar.tvTitle.setText("Select Make");
        binding.inctoolbar.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });
        args = MakeFragmentArgs.fromBundle(getArguments());
        vehicleAdapter = new VehicleAdapter(new ItemClickListener() {
            @Override
            public void onItemClick(Object make) {
                prepareBundleAndNavigateToOtherScreen((String) make);
//                navController.navigate(MakeFragmentDirections.actionMakeFragmentToModelFragment());
            }
        });
        binding.rvMake.setLayoutManager(new LinearLayoutManager(context));
        binding.rvMake.setAdapter(vehicleAdapter);
        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<String[]> call = apiInterface.getMakes(args.getVehicleclass());
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

    private void prepareBundleAndNavigateToOtherScreen(String make) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_VEHICLE_REG_NUM, args.getVehicleName());
        bundle.putString(Constants.KEY_VEHICLE_CLASS, args.getVehicleclass());
        bundle.putString(Constants.KEY_VEHCILE_MAKE, make);
        navController.navigate(R.id.action_makeFragment_to_modelFragment, bundle);
    }
}
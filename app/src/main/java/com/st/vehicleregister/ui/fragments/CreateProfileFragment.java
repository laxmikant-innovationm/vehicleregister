package com.st.vehicleregister.ui.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.st.vehicleregister.R;
import com.st.vehicleregister.databinding.FragmentCreateProfileBinding;
import com.st.vehicleregister.utils.Constants;

public class CreateProfileFragment extends BaseFragment<FragmentCreateProfileBinding> {

    public CreateProfileFragment() {
        // Required empty public constructor
    }

    public static CreateProfileFragment newInstance() {
        CreateProfileFragment fragment = new CreateProfileFragment();
        return fragment;
    }

    @Override
    protected FragmentCreateProfileBinding getInflater(LayoutInflater inflater, ViewGroup container, boolean attachToRoot) {
        return FragmentCreateProfileBinding.inflate(inflater, container, attachToRoot);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        binding.incToolbar.tvTitle.setText("Create New Vehicle Profile");
        binding.incToolbar.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });
        binding.fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogForVehicleClass();
//                navController.navigate(CreateProfileFragmentDirections.actionCreateProfileFragmentToMakeFragment());
            }
        });
    }

    private void openDialogForVehicleClass() {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_class);

        dialog.findViewById(R.id.tvTwo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToMakeScreen(Constants.TWO_WHEELER_CLASS);
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.tvFour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToMakeScreen(Constants.FOUR_WHEELER_CLASS);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void navigateToMakeScreen(String vehicleClass) {
        navController.navigate(CreateProfileFragmentDirections.actionCreateProfileFragmentToMakeFragment(binding.etVehicleNumber.getText().toString(), vehicleClass));
    }
}
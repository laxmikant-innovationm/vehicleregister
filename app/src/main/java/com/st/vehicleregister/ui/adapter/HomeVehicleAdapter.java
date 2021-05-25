package com.st.vehicleregister.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.st.vehicleregister.databinding.ItemVehicleBinding;
import com.st.vehicleregister.model.VehicleModel;
import com.st.vehicleregister.ui.ItemClickListener;

import java.util.List;

public class HomeVehicleAdapter extends RecyclerView.Adapter<HomeVehicleAdapter.HomeVehicleVH> {
    private List<VehicleModel> models;
    private ItemClickListener itemClickListener;

    public HomeVehicleAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public HomeVehicleVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVehicleBinding binding = ItemVehicleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HomeVehicleVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeVehicleVH holder, int position) {
        holder.bind(models.get(position));
    }

    @Override
    public int getItemCount() {
        if (models == null) {
            return 0;
        }
        return models.size();
    }

    public void setVehicleList(List<VehicleModel> vehicleList) {
        this.models = vehicleList;
        notifyDataSetChanged();
    }

    public class HomeVehicleVH extends RecyclerView.ViewHolder {
        private ItemVehicleBinding binding;

        public HomeVehicleVH(@NonNull ItemVehicleBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        private void bind(VehicleModel model) {
            binding.tvName.setText(model.getModel());
            binding.tvNumber.setText(model.getRegNumber());
            binding.clMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(model);
                }
            });
        }
    }
}

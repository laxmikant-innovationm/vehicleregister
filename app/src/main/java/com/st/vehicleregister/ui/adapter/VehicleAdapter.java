package com.st.vehicleregister.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.st.vehicleregister.databinding.ItemMakeBinding;
import com.st.vehicleregister.ui.ItemClickListener;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleVH> {

    private String[] collection;
    private ItemClickListener itemClickListener;

    public VehicleAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public VehicleVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMakeBinding view = ItemMakeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new VehicleVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleVH holder, int position) {
        holder.bind(collection[position]);
    }

    @Override
    public int getItemCount() {
        if (collection == null) {
            return 0;
        }
        return collection.length;
    }

    public void setCollection(String[] collection) {
        this.collection = collection;
        notifyDataSetChanged();
    }

    public class VehicleVH extends RecyclerView.ViewHolder {
        private ItemMakeBinding binding;

        public VehicleVH(@NonNull ItemMakeBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        private void bind(String name) {
            binding.tvNumber.setText(name);
            binding.cvMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(name);
                }
            });
        }
    }
}

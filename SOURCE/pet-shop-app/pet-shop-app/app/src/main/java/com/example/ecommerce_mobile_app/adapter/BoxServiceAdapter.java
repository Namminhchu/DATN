package com.example.ecommerce_mobile_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce_mobile_app.R;
import com.example.ecommerce_mobile_app.databinding.ListItemServiceBinding;
import com.example.ecommerce_mobile_app.model.Service;
import com.example.ecommerce_mobile_app.view.DetailsServiceActivity;

import java.util.List;

public class BoxServiceAdapter extends RecyclerView.Adapter<BoxServiceAdapter.ServiceViewHolder> {
    private List<Service> mServices;
    private List<Service> mOldServices;

    private Context context;

    public void setmListService(List<Service> mServices) {
        this.mServices = mServices;
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemServiceBinding listItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_service, parent, false);
        return new ServiceViewHolder(listItemBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service service = mServices.get(position);
        holder.listItemBinding.setService(service);

        holder.listItemBinding.LayoutItemService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsServiceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("service", service);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mServices != null ? mServices.size() : 0;
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        private ListItemServiceBinding listItemBinding;

        public ServiceViewHolder(@NonNull ListItemServiceBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;
        }

    }
}

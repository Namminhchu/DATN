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
import com.example.ecommerce_mobile_app.databinding.ListItemWorkerBinding;
import com.example.ecommerce_mobile_app.model.Service;
import com.example.ecommerce_mobile_app.model.Worker;
import com.example.ecommerce_mobile_app.view.DetailsWorkerActivity;

import java.util.List;

public class BoxWorkerAdapter extends RecyclerView.Adapter<BoxWorkerAdapter.WorkerViewHolder> {
    private List<Worker> mItems;
    private Service servicePicked;

    private Context context;

    public void setItems(List<Worker> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    public void setServicePicked(Service service) {
        this.servicePicked = service;
    }

    @NonNull
    @Override
    public BoxWorkerAdapter.WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemWorkerBinding listItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_worker, parent, false);
        return new BoxWorkerAdapter.WorkerViewHolder(listItemBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull BoxWorkerAdapter.WorkerViewHolder holder, int position) {
        Worker worker = mItems.get(position);
        holder.listItemBinding.setItem(worker);

        holder.listItemBinding.LayoutItemService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsWorkerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", worker);
                bundle.putSerializable("service", servicePicked);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    public class WorkerViewHolder extends RecyclerView.ViewHolder {
        private ListItemWorkerBinding listItemBinding;

        public WorkerViewHolder(@NonNull ListItemWorkerBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;
        }

    }
}

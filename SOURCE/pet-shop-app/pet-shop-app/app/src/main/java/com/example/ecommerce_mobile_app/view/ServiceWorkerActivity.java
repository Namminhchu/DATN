package com.example.ecommerce_mobile_app.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce_mobile_app.adapter.BoxWorkerAdapter;
import com.example.ecommerce_mobile_app.api.CONSTANT;
import com.example.ecommerce_mobile_app.api.RetrofitClient;
import com.example.ecommerce_mobile_app.databinding.ActivityServiceWorkerBinding;
import com.example.ecommerce_mobile_app.databinding.FragmentServiceBinding;
import com.example.ecommerce_mobile_app.model.Service;
import com.example.ecommerce_mobile_app.model.Worker;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceWorkerActivity extends AppCompatActivity {

    ActivityServiceWorkerBinding activityServiceWorkerBinding;

    RecyclerView rcvItems;
    BoxWorkerAdapter boxWorkerAdapter = new BoxWorkerAdapter();

    Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityServiceWorkerBinding = ActivityServiceWorkerBinding.inflate(getLayoutInflater());
        setContentView(activityServiceWorkerBinding.getRoot());

        // get service picked
        Bundle bundle = getIntent().getExtras();
        service = (Service) bundle.getSerializable("service");

        setItems();

        activityServiceWorkerBinding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceWorkerActivity.super.onBackPressed();
            }
        });
    }

    public void setItems() {
        rcvItems = activityServiceWorkerBinding.rvPopularHome;

        RetrofitClient.getInstance().getAllWorkers().enqueue(new Callback<List<Worker>>() {
            @Override
            public void onResponse(Call<List<Worker>> call, Response<List<Worker>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    response.body().forEach(item -> item.setAvatar(CONSTANT.PATH_IMAGE_PREFIX + item.getAvatar()));
                    List<Worker> workers = response.body();
                    boxWorkerAdapter.setItems(workers);
                    boxWorkerAdapter.setContext(ServiceWorkerActivity.this);
                    boxWorkerAdapter.setServicePicked(service);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(ServiceWorkerActivity.this, 2);
                    rcvItems.setLayoutManager(gridLayoutManager);
                    rcvItems.setAdapter(boxWorkerAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Worker>> call, Throwable t) {

            }
        });
    }
}
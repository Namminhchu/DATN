package com.example.ecommerce_mobile_app.view;

import static androidx.databinding.library.baseAdapters.BR.id;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ecommerce_mobile_app.adapter.BookingAdapter;
import com.example.ecommerce_mobile_app.api.RetrofitClient;
import com.example.ecommerce_mobile_app.databinding.ActivityMyServiceBinding;
import com.example.ecommerce_mobile_app.model.BookingService;
import com.example.ecommerce_mobile_app.model.Customer;
import com.example.ecommerce_mobile_app.model.response.ResponseNormal;
import com.example.ecommerce_mobile_app.util.CustomToast;
import com.example.ecommerce_mobile_app.util.PrefManager;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceActivity extends AppCompatActivity {
    private ActivityMyServiceBinding activityMyServiceBinding;
    Customer customer;
    private final PrefManager prefManager = new PrefManager(this);
    private final BookingAdapter bookingAdapter = new BookingAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMyServiceBinding = ActivityMyServiceBinding.inflate(getLayoutInflater());
        setContentView(activityMyServiceBinding.getRoot());

        customer = prefManager.getCustomer();
        bookingAdapter.setiOnClickItem(new BookingAdapter.IOnClickItem() {
            @Override
            public void clickItem(BookingService bookingService) {
                Intent intent = new Intent(getApplicationContext(), BookingDetailActivity.class);
                intent.putExtra("booking", new Gson().toJson(bookingService));
                startActivity(intent);
            }
        });
        // truyền
        setListService(customer.getId());

        activityMyServiceBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {ServiceActivity.super.onBackPressed();}
        });
    }

    public void setListService(int id) {

        RetrofitClient.getInstance().getServiceByUser(id).enqueue(new Callback<List<BookingService>>() {
            @Override
            public void onResponse(Call<List<BookingService>> call, Response<List<BookingService>> response) {
                if (response.isSuccessful()) {
                    List<BookingService> bookingServices = response.body();
                    bookingAdapter.setmItems(bookingServices);
                    activityMyServiceBinding.rvMyServiceList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    activityMyServiceBinding.rvMyServiceList.setAdapter(bookingAdapter);
                } else {
                    CustomToast.showFailMessage(getApplicationContext(), "Loading your service is failure!");
                }
            }

            @Override
            public void onFailure(Call<List<BookingService>> call, Throwable t) {
                CustomToast.showFailMessage(getApplicationContext(), "Loading your service is failure!");
            }
        });
    }
}

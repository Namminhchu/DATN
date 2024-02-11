package com.example.ecommerce_mobile_app.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ecommerce_mobile_app.adapter.LineProductAdapter;
import com.example.ecommerce_mobile_app.databinding.ActivityMyBookingDetailsBinding;
import com.example.ecommerce_mobile_app.model.BookingService;
import com.example.ecommerce_mobile_app.model.CartItem;
import com.example.ecommerce_mobile_app.util.Constant;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BookingDetailActivity extends AppCompatActivity {
    private ActivityMyBookingDetailsBinding myBookingDetailsBinding;
    private BookingService booking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myBookingDetailsBinding = ActivityMyBookingDetailsBinding.inflate(getLayoutInflater());
        setContentView(myBookingDetailsBinding.getRoot());
        booking = new Gson().fromJson(getIntent().getExtras().getString("booking"), BookingService.class);
        myBookingDetailsBinding.setBooking(booking);
        setService();
        myBookingDetailsBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookingDetailActivity.super.onBackPressed();
            }
        });
    }
    public void setService(){

        List<CartItem> cartItems = new ArrayList<>();

        CartItem item = new CartItem();
        item.setShortName(booking.getServiceName());
        item.setMobileName(booking.getServiceName());

        int randomNum = ThreadLocalRandom.current().nextInt(0, Constant.IMAGES_SERVICE.size() - 1);
        item.setImage(Constant.IMAGES_SERVICE.get(randomNum));
        item.setQuantity(1);
        item.setSubtotal(booking.getPrice());
        cartItems.add(item);

        LineProductAdapter lineProductAdapter = new LineProductAdapter();
        lineProductAdapter.setmListCartItems(cartItems);

        myBookingDetailsBinding.rvMyOrderListProduct.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myBookingDetailsBinding.rvMyOrderListProduct.setAdapter(lineProductAdapter);
    }
}
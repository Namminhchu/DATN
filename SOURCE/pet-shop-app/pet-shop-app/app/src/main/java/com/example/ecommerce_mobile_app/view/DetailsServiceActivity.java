package com.example.ecommerce_mobile_app.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ecommerce_mobile_app.adapter.DescriptionAdapter;
import com.example.ecommerce_mobile_app.adapter.ImageAdapter;
import com.example.ecommerce_mobile_app.databinding.ActivityItemDetailsServiceBinding;
import com.example.ecommerce_mobile_app.model.Description;
import com.example.ecommerce_mobile_app.model.Image;
import com.example.ecommerce_mobile_app.model.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DetailsServiceActivity extends AppCompatActivity {
    ActivityItemDetailsServiceBinding activityItemDetailsBinding;
    Service service;

    private Timer timer;
    int totalItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityItemDetailsBinding = ActivityItemDetailsServiceBinding.inflate(getLayoutInflater());
        setContentView(activityItemDetailsBinding.getRoot());

        Bundle bundle = getIntent().getExtras();

        service = (Service) bundle.getSerializable("service");
        activityItemDetailsBinding.setService(service);

        setImageSlide();
        setDescription();
        autoSlideImages();

        activityItemDetailsBinding.btnChooseWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ServiceWorkerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("service", service);
                //Đẩy dữ liệu sang activity mới thông qua bundle
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        activityItemDetailsBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailsServiceActivity.super.onBackPressed();
            }
        });
    }

    public void setImageSlide(){
        List<Image> images = new ArrayList<>();
        images.add(new Image(service.getImage()));
        images.add(new Image(service.getImage()));
        images.add(new Image(service.getImage()));

        totalItem = images.size() - 1; // lấy toltal để làm auto slide
        ImageAdapter imageAdapter = new ImageAdapter(getApplicationContext(), images);
        activityItemDetailsBinding.viewPagerItemDetails.setAdapter(imageAdapter);
        activityItemDetailsBinding.circleIndicator.setViewPager(activityItemDetailsBinding.viewPagerItemDetails);
        imageAdapter.registerDataSetObserver(activityItemDetailsBinding.circleIndicator.getDataSetObserver());
    }

    public void setDescription(){
        List<Description> descriptions = new ArrayList<>();
        Description description = new Description();
        description.setName("INFO");
        description.setValue(service.getDescription());
        descriptions.add(description);

        DescriptionAdapter descriptionAdapter = new DescriptionAdapter();
        descriptionAdapter.setList(descriptions);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        //Đặt layout.... cho rvDes
        activityItemDetailsBinding.rvDes.setLayoutManager(linearLayoutManager);
        //Kết nối adpter hiển thị dữ liệu
        activityItemDetailsBinding.rvDes.setAdapter(descriptionAdapter);
    }

    private void autoSlideImages(){
        if(timer == null){
            timer = new Timer();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = activityItemDetailsBinding.viewPagerItemDetails.getCurrentItem();
                        int total = totalItem;
                        if(currentItem < total){
                            currentItem++;
                            activityItemDetailsBinding.viewPagerItemDetails.setCurrentItem(currentItem);
                        }else {
                            activityItemDetailsBinding.viewPagerItemDetails.setCurrentItem(0);
                        }
                    }
                });
            }
        },3000,3000);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
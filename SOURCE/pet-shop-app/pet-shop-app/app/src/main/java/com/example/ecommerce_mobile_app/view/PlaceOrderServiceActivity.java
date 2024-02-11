package com.example.ecommerce_mobile_app.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce_mobile_app.R;
import com.example.ecommerce_mobile_app.adapter.LineProductAdapter;
import com.example.ecommerce_mobile_app.api.RetrofitClient;
import com.example.ecommerce_mobile_app.databinding.ActivityPlaceOrderServiceBinding;
import com.example.ecommerce_mobile_app.model.BookingService;
import com.example.ecommerce_mobile_app.model.CartItem;
import com.example.ecommerce_mobile_app.model.Customer;
import com.example.ecommerce_mobile_app.model.Service;
import com.example.ecommerce_mobile_app.model.Worker;
import com.example.ecommerce_mobile_app.model.response.ResponseNormal;
import com.example.ecommerce_mobile_app.util.Constant;
import com.example.ecommerce_mobile_app.util.CustomToast;
import com.example.ecommerce_mobile_app.util.DateUtils;
import com.example.ecommerce_mobile_app.util.OrderSuccessDialog;
import com.example.ecommerce_mobile_app.util.PrefManager;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceOrderServiceActivity extends AppCompatActivity {
    ActivityPlaceOrderServiceBinding activityPlaceOrderBinding;
    PrefManager prefManager = new PrefManager(this);
    Customer customer;
    List<CartItem> cartItemList = new ArrayList<>();
    LineProductAdapter lineProductAdapter = new LineProductAdapter();
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    private Service service;
    private Worker worker;

    private final BookingService booking = new BookingService();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPlaceOrderBinding = ActivityPlaceOrderServiceBinding.inflate(getLayoutInflater());
        setContentView(activityPlaceOrderBinding.getRoot());

        customer = prefManager.getCustomer();
        service = (Service) getIntent().getSerializableExtra("service");
        worker = (Worker) getIntent().getSerializableExtra("worker");

        booking.setClientId(customer.getId() + "");
        booking.setServiceId(service.getId() + "");
        booking.setServiceCode("MDV" + service.getId());
        booking.setServiceName(service.getName());
        booking.setFullname(customer.getFullname());
        booking.setPhone(customer.getPhoneNumber());
        booking.setServiceWorkerId(worker.getId() + "");

        activityPlaceOrderBinding.setBooking(booking);
        activityPlaceOrderBinding.setWorker(worker);
        activityPlaceOrderBinding.setService(service);

        recyclerView = activityPlaceOrderBinding.rvServices;

        CartItem item = new CartItem();
        item.setShortName(service.getName());
        item.setMobileName(service.getName());
        item.setImage(service.getImage());
        item.setQuantity(1);
        item.setSubtotal(service.getPrice());
        cartItemList.add(item);

        lineProductAdapter.setmListCartItems(cartItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(lineProductAdapter);


        activityPlaceOrderBinding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlaceOrderServiceActivity.super.onBackPressed();
            }
        });

        final View dialogView = View.inflate(this, R.layout.date_time_picker, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);
        TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.time_picker);

        activityPlaceOrderBinding.btnChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.setView(dialogView);
                alertDialog.show();
            }
        });

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                setTime(datePicker, timePicker);
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                setTime(datePicker, timePicker);
            }
        });

        activityPlaceOrderBinding.btnBookService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check booking information
                if (Objects.isNull(booking.getAppointmentDate())) {
                    CustomToast.showFailMessage(getApplicationContext(), "Vui lòng chọn thời gian hẹn");
                    return;
                }

                booking.setPetName(activityPlaceOrderBinding.tvReceiver.getText().toString());
                booking.setPetType(activityPlaceOrderBinding.tvPetType.getText().toString());


                if (StringUtils.isBlank(booking.getPetName()) || StringUtils.isBlank(booking.getPetType())) {
                    CustomToast.showFailMessage(getApplicationContext(), "Vui lòng điền đầy đủ thông tin thú cưng");
                    return;
                }

                bookService();
            }
        });
    }

    public void bookService() {
        progressDialog = new ProgressDialog(PlaceOrderServiceActivity.this);
        progressDialog.setMessage("Wait a second...");
        progressDialog.show();
        PrefManager prefManager = new PrefManager(getApplicationContext());

        RetrofitClient.getInstance().book(booking, prefManager.getToken()).enqueue(new Callback<ResponseNormal<Void>>() {
            @Override
            public void onResponse(Call<ResponseNormal<Void>> call, Response<ResponseNormal<Void>> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    CustomToast.showSuccessMessage(getApplicationContext(), response.body().getMsg());
                    OrderSuccessDialog orderSuccessDialog = new OrderSuccessDialog(Constant.Type.SERVICE);
                    orderSuccessDialog.show(getSupportFragmentManager(), "Đặt lịch dịch vụ thành công");
                } else {
                    progressDialog.dismiss();
                    CustomToast.showFailMessage(getApplicationContext(), "Đặt lịch thất bại!");
                }
            }

            @Override
            public void onFailure(Call<ResponseNormal<Void>> call, Throwable t) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setTime(DatePicker datePicker, TimePicker timePicker) {
        Calendar calendar = new GregorianCalendar(
                datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());

        booking.setAppointmentDate(calendar.toInstant().toString());
        activityPlaceOrderBinding.tvOrderDate.setText(DateUtils.toString(calendar.toInstant()));
    }
}
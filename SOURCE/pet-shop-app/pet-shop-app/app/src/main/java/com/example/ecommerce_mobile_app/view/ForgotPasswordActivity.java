package com.example.ecommerce_mobile_app.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerce_mobile_app.R;
import com.example.ecommerce_mobile_app.api.RetrofitClient;
import com.example.ecommerce_mobile_app.databinding.ActivityForgotPasswordBinding;
import com.example.ecommerce_mobile_app.model.Customer;
import com.example.ecommerce_mobile_app.model.request.ForgotPasswordRequest;
import com.example.ecommerce_mobile_app.model.response.ResponseNormal;
import com.example.ecommerce_mobile_app.util.Constant;
import com.example.ecommerce_mobile_app.util.CustomToast;
import com.example.ecommerce_mobile_app.util.PrefManager;
import com.google.gson.Gson;

import org.apache.commons.lang3.StringUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ActivityForgotPasswordBinding activityForgotPasswordBinding;
    private Constant constant;
    private int seconds;
    private int codeOTP;
    private CountDownTimer mCountDownTimer;
    private static final long START_TIME_IN_MILLIS = 30000; //30s
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private final ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
    private final PrefManager prefManager = new PrefManager(this); // lưu trữ & truy xuất dử liệu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityForgotPasswordBinding = ActivityForgotPasswordBinding.inflate(LayoutInflater.from(getApplicationContext()));
        setContentView(activityForgotPasswordBinding.getRoot());

        activityForgotPasswordBinding.btnBackSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPasswordActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        activityForgotPasswordBinding.btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = activityForgotPasswordBinding.etEmailSignIn.getText().toString();

                if (StringUtils.isBlank(email)) {
                    CustomToast.showFailMessage(getApplicationContext(), "Vui lòng nhập email của bạn");
                    return;
                }

                // Get customer by email
                RetrofitClient.getInstance().getCustomerByEmail(email).enqueue(new Callback<ResponseNormal<Customer>>() {
                    @Override
                    public void onResponse(Call<ResponseNormal<Customer>> call, Response<ResponseNormal<Customer>> response) {
                        if (response.isSuccessful() && response.body() != null) {

                            if (Constant.STATUS_SUCCESS.equals(response.body().getHttpStatus())) {

                                activityForgotPasswordBinding.btnSendCode.setEnabled(false);
                                setBtnSendWait();
                                Intent intent = new Intent(ForgotPasswordActivity.this, UpdatePasswordActivity.class);
                                intent.putExtra("customer", new Gson().toJson(response.body().getData()));

                                startActivity(intent);
                                finish();
                            } else {
                                CustomToast.showFailMessage(getApplicationContext(), response.body().getMsg());
                            }
                        } else {
                            CustomToast.showSystemError(getApplicationContext());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseNormal<Customer>> call, Throwable t) {
                        CustomToast.showSystemError(getApplicationContext());
                    }
                });
            }
        });
    }

    private void setBtnSendWait() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                seconds = seconds - 1;
                String textWait = "Resend " + seconds + "s";
                activityForgotPasswordBinding.btnSendCode.setText(textWait);
            }

            @Override
            public void onFinish() {
                codeOTP = 999999999;
                seconds = 30;

                activityForgotPasswordBinding.btnSendCode.setEnabled(true);
                activityForgotPasswordBinding.btnSendCode.setBackground(getDrawable(R.drawable.custom_btn_dialog_delete_item_cart_yes));
                activityForgotPasswordBinding.btnSendCode.setTextColor(getApplication().getResources().getColor(R.color.white));
                activityForgotPasswordBinding.btnSendCode.setText("Send Code");

                mTimeLeftInMillis = START_TIME_IN_MILLIS;
            }
        }.start();
    }
}
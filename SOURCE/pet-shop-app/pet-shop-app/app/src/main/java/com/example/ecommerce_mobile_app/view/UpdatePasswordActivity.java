package com.example.ecommerce_mobile_app.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerce_mobile_app.R;
import com.example.ecommerce_mobile_app.api.RetrofitClient;
import com.example.ecommerce_mobile_app.databinding.ActivityChangePasswordBinding;
import com.example.ecommerce_mobile_app.model.Customer;
import com.example.ecommerce_mobile_app.model.request.UpdatePasswordRequest;
import com.example.ecommerce_mobile_app.model.response.ResponseNormal;
import com.example.ecommerce_mobile_app.util.Constant;
import com.example.ecommerce_mobile_app.util.CustomToast;
import com.example.ecommerce_mobile_app.util.PrefManager;
import com.example.ecommerce_mobile_app.util.SendOTP;
import com.google.gson.Gson;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePasswordActivity extends AppCompatActivity {

    private static final String UPDATE_ACTION = "CHANGE_PASSWORD";
    private static final String FORGOT_ACTION = "FORGOT_PASSWORD";

    private static final int TIME_OTP = 120;
    private static final int OTP_INVALID = 999999999;


    private ActivityChangePasswordBinding activityChangePasswordBinding;
    Customer customer;
    private String oldPassword, newPassword, confirmPassword;
    private final PrefManager prefManager = new PrefManager(this);

    private CountDownTimer mCountDownTimer;
    private static final long START_TIME_IN_MILLIS = 30000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;


    private int seconds;
    private int codeOTP = OTP_INVALID;

    private String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChangePasswordBinding = ActivityChangePasswordBinding.inflate(LayoutInflater.from(getApplicationContext()));
        setContentView(activityChangePasswordBinding.getRoot());

        Customer customerFromForgotPass = new Gson().fromJson(getIntent().getExtras().getString("customer"), Customer.class);
        if (customerFromForgotPass == null) {
            action = UPDATE_ACTION;
            customer = prefManager.getCustomer();
        } else {
            action = FORGOT_ACTION;
            customer = customerFromForgotPass;
        }

        activityChangePasswordBinding.setCustomer(customer);
        activityChangePasswordBinding.btnSave.setOnClickListener(view -> {
            oldPassword = activityChangePasswordBinding.etPasswordChange.getText().toString().trim();
            newPassword = activityChangePasswordBinding.etNewPasswordChange.getText().toString();
            confirmPassword = activityChangePasswordBinding.etConfirmPasswordChange.getText().toString();

            if (StringUtils.isBlank(newPassword) || StringUtils.isBlank(confirmPassword)) {
                CustomToast.showFailMessage(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin");
                return;
            }

            if (UPDATE_ACTION.equals(action) && StringUtils.isBlank(oldPassword)) {
                CustomToast.showFailMessage(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin");
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                CustomToast.showFailMessage(getApplicationContext(), "2 mật khẩu không khớp nhau");
                return;
            }

            UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest();
            updatePasswordRequest.setOldPassword(oldPassword);
            updatePasswordRequest.setNewPassword(newPassword);

            doChangePassword(updatePasswordRequest);
        });

        activityChangePasswordBinding.backChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdatePasswordActivity.super.onBackPressed();
            }
        });

        activityChangePasswordBinding.btnSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                codeOTP = random.nextInt(8999) + 1000;
                SendOTP.sendMailOtp(codeOTP, customer.getEmail());
                seconds = TIME_OTP;
                activityChangePasswordBinding.btnSendOTP.setEnabled(false);
                activityChangePasswordBinding.btnSendOTP.setBackground(getDrawable(R.drawable.custom_btn_dialog_delete_item_cart_cancel));
                activityChangePasswordBinding.btnSendOTP.setTextColor(getApplication().getResources().getColor(R.color.black));
                setBtnSendWait();
            }
        });

        activityChangePasswordBinding.btnSubmitChangePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputCode = activityChangePasswordBinding.firstPinView.getText().toString();
                if (OTP_INVALID != codeOTP && inputCode.equals(String.valueOf(codeOTP))) {
                    if (FORGOT_ACTION.equals(action)) {
                        activityChangePasswordBinding.tvPasswordChange.setVisibility(View.GONE);
                        activityChangePasswordBinding.etPasswordChange.setVisibility(View.GONE);
                    }
                    activityChangePasswordBinding.LayoutConfirmOTP.setVisibility(View.GONE);
                    activityChangePasswordBinding.LayoutEditProfileDetail.setVisibility(View.VISIBLE);
                    CustomToast.showSuccessMessage(getApplicationContext(), "Otp code is valid");
                } else {
                    CustomToast.showFailMessage(getApplicationContext(), "OTP is invalid!");
                }
            }
        });
    }

    public void doChangePassword(UpdatePasswordRequest request) {
        RetrofitClient.getInstance().updatePassword(customer.getId(), request).enqueue(new Callback<ResponseNormal<Customer>>() {
            @Override
            public void onResponse(Call<ResponseNormal<Customer>> call, Response<ResponseNormal<Customer>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    if (Constant.STATUS_SUCCESS.equals(response.body().getHttpStatus())) {
                        CustomToast.showSuccessMessage(getApplicationContext(), response.body().getMsg());

                        // Check action update or forgot password
                        if (FORGOT_ACTION.equals(action)) {
                            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            UpdatePasswordActivity.super.onBackPressed();
                        }

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

    private void setBtnSendWait() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                seconds = seconds - 1;
                String textWait = "Resend " + seconds + "s";
                activityChangePasswordBinding.btnSendOTP.setText(textWait);
            }

            @Override
            public void onFinish() {
                codeOTP = OTP_INVALID;
                seconds = TIME_OTP;

                activityChangePasswordBinding.btnSendOTP.setEnabled(true);
                activityChangePasswordBinding.btnSendOTP.setBackground(getDrawable(R.drawable.custom_btn_dialog_delete_item_cart_yes));
                activityChangePasswordBinding.btnSendOTP.setTextColor(getApplication().getResources().getColor(R.color.white));
                activityChangePasswordBinding.btnSendOTP.setText("Send Code");

                mTimeLeftInMillis = START_TIME_IN_MILLIS;
            }
        }.start();
    }
}

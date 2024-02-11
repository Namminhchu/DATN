package com.example.ecommerce_mobile_app.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerce_mobile_app.databinding.FragmentProfileBinding;
import com.example.ecommerce_mobile_app.util.PrefManager;
import com.example.ecommerce_mobile_app.view.CustomerDetailActivity;
import com.example.ecommerce_mobile_app.view.OrderActivity;
import com.example.ecommerce_mobile_app.view.ServiceActivity;
import com.example.ecommerce_mobile_app.view.SignInActivity;
import com.example.ecommerce_mobile_app.view.UpdatePasswordActivity;

import android.net.Uri;


public class ProfileFragment extends Fragment {
    FragmentProfileBinding fragmentProfileBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProfileBinding = FragmentProfileBinding.inflate(inflater,container,false);
        fragmentProfileBinding.LayoutUserDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CustomerDetailActivity.class);
                startActivity(intent);
            }
        });


        fragmentProfileBinding.btnLogoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefManager prefManager = new PrefManager(getContext());

                prefManager.removeCustomer();
                prefManager.removeToken();

                Intent intent = new Intent(getContext(), SignInActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        fragmentProfileBinding.LayoutMyOrderProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), OrderActivity.class);
                startActivity(intent);
            }
        });
        fragmentProfileBinding.LayoutService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ServiceActivity.class));
            }
        });


        fragmentProfileBinding.Layoutzalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gắn link Zalo của bạn
                String yourZaloLink = "https://zalo.me/0971132155";  // Thay your_zalo_id bằng ID Zalo của bạn

                // Tạo Intent để mở ứng dụng Zalo
                Intent zaloIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(yourZaloLink));

                // Kiểm tra xem ứng dụng Zalo đã được cài đặt trên điện thoại hay chưa
                if (zaloIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    // Nếu đã cài đặt, mở liên kết trong ứng dụng Zalo
                    startActivity(zaloIntent);
                } else {
                    // Nếu chưa cài đặt, mở liên kết Zalo trên trình duyệt web
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(yourZaloLink));
                    startActivity(webIntent);
                }
            }
        });


        fragmentProfileBinding.LayoutFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ID của người dùng trên Facebook, thay thế bằng ID thực của người dùng
                String facebookUserId = "100053668959067";

                // Tạo URI cho trang cá nhân của người dùng trên Facebook
                String facebookProfileUri = "fb://profile/" + facebookUserId;

                // Tạo Intent với URI của trang cá nhân
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookProfileUri));

                // Kiểm tra xem ứng dụng Facebook đã được cài đặt trên điện thoại hay chưa
                if (facebookIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    // Nếu đã cài đặt, mở trang cá nhân trong ứng dụng Facebook
                    startActivity(facebookIntent);
                } else {
                    // Nếu chưa cài đặt, mở trang cá nhân trên trình duyệt web
                    String facebookProfileUrl = "https://www.facebook.com/profile.php?id=" + facebookUserId;
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookProfileUrl));
                    startActivity(webIntent);
                }
            }
        });


        fragmentProfileBinding.LayoutChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UpdatePasswordActivity.class);
                intent.putExtra("customer", "");
                startActivity(intent);
            }
        });
        return fragmentProfileBinding.getRoot();
    }
}
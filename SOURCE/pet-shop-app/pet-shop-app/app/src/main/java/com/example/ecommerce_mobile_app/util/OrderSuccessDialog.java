package com.example.ecommerce_mobile_app.util;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ecommerce_mobile_app.R;
import com.example.ecommerce_mobile_app.view.MainActivity;

public class OrderSuccessDialog extends DialogFragment {
    private String type; // SERVICE _ PRODUCT

    public OrderSuccessDialog(String type) {
        this.type = type;
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dialog_successful_order, container, false);
        TextView textView = view.findViewById(R.id.tv_desc);

        if (Constant.Type.PRODUCT.equals(type)) {
            textView.setText("Order complete! Thank you so much for choosing us!");
        } else {
            textView.setText("Booking complete! Thank you so much for choosing us!");
        }

        view.findViewById(R.id.btnContinueShop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        return view;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        goStore();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        goStore();
    }

    public void goStore() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("change_to", Constant.Type.PRODUCT.equals(type) ? "store" : "service");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

package com.example.ecommerce_mobile_app.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce_mobile_app.adapter.BoxServiceAdapter;
import com.example.ecommerce_mobile_app.api.RetrofitClient;
import com.example.ecommerce_mobile_app.databinding.FragmentServiceBinding;
import com.example.ecommerce_mobile_app.model.Service;
import com.example.ecommerce_mobile_app.util.Constant;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ServiceFragment extends Fragment {

    FragmentServiceBinding fragmentServiceBinding;
    RecyclerView rcvServices;

    BoxServiceAdapter boxServiceAdapter = new BoxServiceAdapter();

    boolean checkPop = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentServiceBinding = FragmentServiceBinding.inflate(inflater, container, false);

        setListService();

        fragmentServiceBinding.tvPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPop) {
                    fragmentServiceBinding.rvPopularService.setVisibility(View.GONE);
                    checkPop = false;
                } else {
                    fragmentServiceBinding.rvPopularService.setVisibility(View.VISIBLE);
                    checkPop = true;
                }
            }
        });

        return fragmentServiceBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setListService() {
        rcvServices = fragmentServiceBinding.rvPopularService;

        RetrofitClient.getInstance().getAllServices().enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;

                    response.body().forEach(s -> {
                        int randomNum = ThreadLocalRandom.current().nextInt(0, Constant.IMAGES_SERVICE.size() - 1);
                        s.setImage(Constant.IMAGES_SERVICE.get(randomNum));
                    });

                    List<Service> services = response.body();
                    boxServiceAdapter.setmListService(services);
                    boxServiceAdapter.setContext(getContext());
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    rcvServices.setLayoutManager(gridLayoutManager);
                    rcvServices.setAdapter(boxServiceAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {

            }
        });

    }
}
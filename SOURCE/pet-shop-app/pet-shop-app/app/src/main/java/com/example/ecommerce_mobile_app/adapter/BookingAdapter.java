package com.example.ecommerce_mobile_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce_mobile_app.R;
import com.example.ecommerce_mobile_app.databinding.ListItemMyBookingBinding;
import com.example.ecommerce_mobile_app.databinding.ListItemMyOrderBinding;
import com.example.ecommerce_mobile_app.model.BookingService;
import com.example.ecommerce_mobile_app.model.response.OrdersDTO;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.OrderViewHolder>{


    private List<BookingService> mItems;
    private IOnClickItem iOnClickItem;
    public interface IOnClickItem {
        public void clickItem(BookingService bookingService);
    }

    public void setiOnClickItem(IOnClickItem iOnClickItem) {
        this.iOnClickItem = iOnClickItem;
        notifyDataSetChanged();
    }

    public void setmItems(List<BookingService> mItems) {
        this.mItems = mItems;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemMyBookingBinding listItemMyBookingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_my_booking,parent,false);
        return new OrderViewHolder(listItemMyBookingBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        BookingService item = mItems.get(position);
        holder.listItemMyBookingBinding.setBooking(item);
        holder.listItemMyBookingBinding.Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickItem.clickItem(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        private ListItemMyBookingBinding listItemMyBookingBinding;
        public OrderViewHolder(@NonNull ListItemMyBookingBinding listItemMyBookingBinding) {
            super(listItemMyBookingBinding.getRoot());
            this.listItemMyBookingBinding = listItemMyBookingBinding;
        }
    }
}

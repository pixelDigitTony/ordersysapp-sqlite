package com.example.ordersysapp.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordersysapp.Interface.ItemClickListener;
import com.example.ordersysapp.Model.Request;
import com.example.ordersysapp.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private Context mCtx;
    private List<Request> requestList;
    private ItemClickListener itemClickListener;


    public OrderAdapter(Context mCtx, List<Request> requestList, ItemClickListener itemClickListener) {
        this.mCtx = mCtx;
        this.requestList = requestList;
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.order_item, null);
        return new OrderViewHolder(view, itemClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, final int position) {
        Request request = (Request) requestList.get(position);

        orderViewHolder.txtOrderID.setText(request.getRequestId());
        orderViewHolder.txtOrderdateTime.setText(request.getRequestdateTime());
        orderViewHolder.txtOrderStatus.setText(convertCodeToStatus(request.getRequestStatus()));
        orderViewHolder.txtOrderNumber.setText(request.getRequestNumber());

    }

    private String convertCodeToStatus(String requestStatus) {
        if(requestStatus.equals("PENDING")){
            return "Pending Order";
        }else if(requestStatus.equals("COMPLETE")){
            return "Complete";
        }else if (requestStatus.equals("CANCELLED")){
            return "Cancelled";
        }else{
            return "Status MISSING";
        }
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }


    class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView txtOrderID, txtOrderdateTime, txtOrderStatus, txtOrderNumber;
        ItemClickListener itemClickListener;


        public OrderViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            txtOrderNumber = (TextView) itemView.findViewById(R.id.order_number);
            txtOrderID = (TextView) itemView.findViewById(R.id.order_id);
            txtOrderdateTime = (TextView) itemView.findViewById(R.id.order_date);
            txtOrderStatus = (TextView) itemView.findViewById(R.id.order_status);
            this.itemClickListener = itemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onClick(int position);
    }
}

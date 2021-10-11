package com.example.ordersysapp.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordersysapp.MenuList;
import com.example.ordersysapp.Model.Menu;
import com.example.ordersysapp.R;


import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private Context mCtx;
    private List<Menu> menuList;
    private  ItemClickListener itemClickListener;


    public MenuAdapter(MenuList mCtx, List<Menu> menuList, MenuList itemClickListener) {
        this.mCtx = mCtx;
        this.menuList = menuList;
        this.itemClickListener = (ItemClickListener) itemClickListener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.menu_item, null);
        return new MenuViewHolder(view, itemClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Menu menu = (Menu) menuList.get(position);
        holder.textView.setText(menu.getName());
    }


    @Override
    public int getItemCount() {
        return menuList.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView;
        RelativeLayout relativeLayout;
        ItemClickListener itemClickListener;


        public MenuViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.menu_image);
            textView = itemView.findViewById(R.id.menu_name);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.menu_relative);
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

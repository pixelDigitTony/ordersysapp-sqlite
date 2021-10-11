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

import com.example.ordersysapp.MainActivity;
import com.example.ordersysapp.Model.Category;
import com.example.ordersysapp.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context mCtx;
    private List<Category> categoryList;
    private  ItemClickListener itemClickListener;


    public CategoryAdapter(MainActivity mCtx, List<Category> categoryList, MainActivity itemClickListener) {
        this.mCtx = mCtx;
        this.categoryList = categoryList;
        this.itemClickListener = itemClickListener;
    }



    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.type_item, null);
        return  new CategoryViewHolder(view, itemClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = (Category) categoryList.get(position);
        holder.textView.setText(category.getName());
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView textView;
        RelativeLayout relativeLayout;
        ItemClickListener itemClickListener;


        public CategoryViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.type_image);
            textView = itemView.findViewById(R.id.type_name);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.type_relative);
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

package com.example.carstore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


    Context context;
    List<Item> items;
    MainActivity ma;
    public MyAdapter(Context context, List<Item> items, MainActivity ma) {
        this.context = context;
        this.items = items;
        this.ma = ma;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false), ma, items);
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.position = position;
        holder.nameView.setText(items.get(position).getName());
        holder.emailView.setText(items.get(position).getEmail());
        items.get(position).getMainActivity().loadCarImage(holder.imgvw, items.get(position).getImgUrl());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}

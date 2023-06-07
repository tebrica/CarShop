package com.example.carstore;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView nameView,emailView;
    ImageView imgvw;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.name);
        emailView = itemView.findViewById(R.id.email);
        imgvw = itemView.findViewById(R.id.imgvw);
    }
}

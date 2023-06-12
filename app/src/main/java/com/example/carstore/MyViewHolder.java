package com.example.carstore;

import static androidx.core.content.ContextCompat.startActivities;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView nameView,emailView;
    ImageView imgvw;
    int position;
    MainActivity ma;
    List<Item> items;
    public MyViewHolder(@NonNull View itemView, MainActivity ma, List<Item> items) {
        super(itemView);
        this.items = items;
        this.ma = ma;
        nameView = itemView.findViewById(R.id.name);
        emailView = itemView.findViewById(R.id.email);
        imgvw = itemView.findViewById(R.id.imgvw);
        itemView.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("asd","KLIKNUTOOOOOOOOOOOOOOOOOOO" + position);
            }
        });
        itemView.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("asd","KLIKNUTOOOOOOOOOOOOOOOOOOO" + position);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(items.get(position).url));
                startActivity(ma, i, null);
            }
        });
    }

}

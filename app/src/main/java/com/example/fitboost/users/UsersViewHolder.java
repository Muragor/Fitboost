package com.example.fitboost.users;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitboost.R;

public class UsersViewHolder extends RecyclerView.ViewHolder {
    TextView username_tv;
    public UsersViewHolder(@NonNull View itemView) {
        super(itemView);
        username_tv = itemView.findViewById(R.id.nickname);
    }
}

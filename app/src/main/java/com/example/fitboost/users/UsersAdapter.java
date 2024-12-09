package com.example.fitboost.users;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitboost.R;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {
    public UsersAdapter(ArrayList<User> users) {
        this.users = users;
    }

    private ArrayList<User> users = new ArrayList<>();
    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item_rv, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        holder.username_tv.setText(users.get(position).username);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}

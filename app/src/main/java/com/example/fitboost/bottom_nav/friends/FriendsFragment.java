package com.example.fitboost.bottom_nav.friends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fitboost.databinding.FragmentFriendBinding;
import com.example.fitboost.databinding.FragmentHomeBinding;
import com.example.fitboost.users.User;
import com.example.fitboost.users.UsersAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FriendsFragment extends Fragment {
    private FragmentFriendBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFriendBinding.inflate(inflater, container, false);

        loadUsers();
        return binding.getRoot();
    }
    private void loadUsers(){
        ArrayList<User> users = new ArrayList<User>();
        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot userSnapshot : snapshot.getChildren()) {
                    if (userSnapshot.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        continue;
                    }
                    try {
                    String username = userSnapshot.child("username").getValue().toString();
                    users.add(new User(username)); }
                    catch (Exception e) {
                        Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                binding.usersRv.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.usersRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                binding.usersRv.setAdapter(new UsersAdapter(users));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

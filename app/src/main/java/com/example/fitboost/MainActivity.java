package com.example.fitboost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.fitboost.bottom_nav.friends.FriendsFragment;
import com.example.fitboost.bottom_nav.home.HomeFragment;
import com.example.fitboost.bottom_nav.profile.ProfileFragment;
import com.example.fitboost.bottom_nav.vyzov.VyzovFragment;
import com.example.fitboost.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (FirebaseAuth.getInstance().getCurrentUser()==null) {
            startActivity(new Intent(MainActivity.this, Registration.class));
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), new HomeFragment()).commit();
        binding.bottomNav.setSelectedItemId(R.id.home);
        Map<Integer, Fragment> fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.home, new HomeFragment());
        fragmentMap.put(R.id.friends, new FriendsFragment());
        fragmentMap.put(R.id.vysov, new VyzovFragment());
        fragmentMap.put(R.id.profile, new ProfileFragment());
        binding.bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = fragmentMap.get(item.getItemId());
            getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(),fragment).commit();
            return true;
        });

    }

}
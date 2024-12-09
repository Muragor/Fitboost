package com.example.fitboost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fitboost.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration extends AppCompatActivity {

    private ActivityRegistrationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rgstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.emailEdTxt.getText().toString().isEmpty() || binding.pswrdEdTxt.getText().toString().isEmpty() || binding.userName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Не заполнены поля", Toast.LENGTH_SHORT).show();
                }
                else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.emailEdTxt.getText().toString(), binding.pswrdEdTxt.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        HashMap<String, String> userInfo = new HashMap<>();
                                        userInfo.put("email", binding.emailEdTxt.getText().toString());
                                        userInfo.put("username", binding.userName.getText().toString());
                                        userInfo.put("profileImage", "");
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(userInfo);
                                        startActivity(new Intent(Registration.this, MainActivity.class));
                                    }
                                }
                            });
                }
            }
        });
        binding.toLgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, LoginActivity.class));
            }
        });
    }
}
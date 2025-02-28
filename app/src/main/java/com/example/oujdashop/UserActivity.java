package com.example.oujdashop;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class UserActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail;
    private ImageView imageViewProfile;
    private Switch switchTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);


        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        imageViewProfile = findViewById(R.id.imageViewProfile);
        switchTheme = findViewById(R.id.switchTheme);

        switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(UserActivity.this, "Thème sombre activé", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(UserActivity.this, "Thème clair activé", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

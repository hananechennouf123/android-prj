package com.example.oujdashop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oujdashop.ProductActivity;

public class MainActivity extends AppCompatActivity {

    private Button productButton, cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        productButton = findViewById(R.id.productButton);
        cartButton = findViewById(R.id.cartButton);


        if (productButton != null) {
            productButton.setOnClickListener(v -> openProductActivity());
        } else {
            Toast.makeText(this, "Erreur : bouton produit introuvable", Toast.LENGTH_SHORT).show();
        }

        if (cartButton != null) {
            cartButton.setOnClickListener(v -> openCartActivity());
        } else {
            Toast.makeText(this, "Erreur : bouton panier introuvable", Toast.LENGTH_SHORT).show();
        }
    }

    private void openProductActivity() {
        Intent intent = new Intent(MainActivity.this, ProductActivity.class);
        startActivity(intent);
    }

    private void openCartActivity() {
        Intent intent = new Intent(MainActivity.this, CartActivity.class);
        startActivity(intent);
    }
}
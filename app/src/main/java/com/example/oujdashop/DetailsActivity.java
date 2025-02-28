package com.example.oujdashop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    private TextView productName, productPrice, productDescription, productCategory;
    private ImageView productImage;
    private RadioGroup sizeRadioGroup;
    private CheckBox addToFavoritesCheckBox;
    private Switch addToCartSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);
        productDescription = findViewById(R.id.product_description);
        productCategory = findViewById(R.id.product_category);
        productImage = findViewById(R.id.product_image);
        sizeRadioGroup = findViewById(R.id.size_radio_group);
        addToFavoritesCheckBox = findViewById(R.id.add_to_favorites_checkbox);
        addToCartSwitch = findViewById(R.id.add_to_cart_switch);

        Intent intent = getIntent();
        String name = intent.getStringExtra("product_name");
        String price = intent.getStringExtra("product_price");
        String description = intent.getStringExtra("product_description");
        String category = intent.getStringExtra("product_category");
        int imageResId = intent.getIntExtra("product_image", R.drawable.ic_add);


        productName.setText(name);
        productPrice.setText(price);
        productDescription.setText(description);
        productCategory.setText(category);
        productImage.setImageResource(imageResId);


        sizeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedSize = findViewById(checkedId);
            if (selectedSize != null) {
                Toast.makeText(DetailsActivity.this, "Taille choisie: " + selectedSize.getText(), Toast.LENGTH_SHORT).show();
            }
        });


        addToFavoritesCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(DetailsActivity.this, "Ajouté aux favoris", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DetailsActivity.this, "Retiré des favoris", Toast.LENGTH_SHORT).show();
            }
        });


        addToCartSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(DetailsActivity.this, "Ajouté au panier", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DetailsActivity.this, "Retiré du panier", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.example.oujdashop;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ListView cartListView;
    private CartAdapter cartAdapter;
    private ArrayList<Product> cartProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialiser la barre d'outils
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Panier");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialiser la liste des produits du panier
        cartListView = findViewById(R.id.cart_list);
        cartProducts = new ArrayList<>();

        // Ajouter des produits fictifs au panier
        cartProducts.add(new Product(1, "Produit 1", "Description du produit 1", 100.0, R.drawable.produit1, "Catégorie 1"));
        cartProducts.add(new Product(2, "Produit 2", "Description du produit 2", 200.0, R.drawable.produit2, "Catégorie 2"));

        // Configurer l'adaptateur et l'associer à la ListView
        cartAdapter = new CartAdapter(this, cartProducts);
        cartListView.setAdapter(cartAdapter);

        // Afficher un message si le panier est vide
        if (cartProducts.isEmpty()) {
            Toast.makeText(this, "Votre panier est vide", Toast.LENGTH_SHORT).show();
        }
    }

    // Retour à l'activité précédente lorsqu'on clique sur le bouton retour
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

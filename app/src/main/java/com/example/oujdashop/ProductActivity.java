package com.example.oujdashop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<Product> productList;
    private ProductAdapter productAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        databaseHelper = new DatabaseHelper(this);
        gridView = findViewById(R.id.gridView);

        // Initialize product list
        productList = new ArrayList<>();
        loadProducts();

        // Set up adapter
        productAdapter = new ProductAdapter(this, productList);
        gridView.setAdapter(productAdapter);

        registerForContextMenu(gridView);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Product selectedProduct = productList.get(position);
            Intent intent = new Intent(ProductActivity.this, DetailsActivity.class);
            intent.putExtra("productId", selectedProduct.getId());
            startActivity(intent);
        });
    }

    private void loadProducts() {
        // Add dummy products (you should query these from your database)
        productList.add(new Product(1, "Produit 1", 25.99, "Description du produit 1", "Catégorie 1", R.drawable.produit1));
        productList.add(new Product(2, "Produit 2", 15.49, "Description du produit 2", "Catégorie 2", R.drawable.produit2));
        productList.add(new Product(3, "Produit 3", 19.99, "Description du produit 3", "Catégorie 3", R.drawable.produit3));
        productList.add(new Product(4, "Produit 4", 35.00, "Description du produit 4", "Catégorie 4", R.drawable.produit4));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "Modifier");
        menu.add(0, 2, 0, "Supprimer");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Product selectedProduct = productList.get(info.position);

        switch (item.getItemId()) {
            case 1: // Modifier
                editProduct(selectedProduct);
                return true;
            case 2: // Supprimer
                deleteProduct(selectedProduct);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void editProduct(Product product) {
        // Here you can implement logic to open an edit screen or show an AlertDialog to modify the product
        Toast.makeText(this, "Modifier le produit: " + product.getName(), Toast.LENGTH_SHORT).show();
    }


    private void deleteProduct(Product product) {
        new AlertDialog.Builder(this)
                .setTitle("Supprimer le produit")
                .setMessage("Êtes-vous sûr de vouloir supprimer ce produit?")
                .setPositiveButton("Oui", (dialog, which) -> {
                    productList.remove(product);
                    productAdapter.notifyDataSetChanged();
                    Toast.makeText(ProductActivity.this, "Produit supprimé", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Non", null)
                .show();
    }
}

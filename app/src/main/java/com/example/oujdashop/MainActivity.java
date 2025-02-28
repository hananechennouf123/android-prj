package com.example.oujdashop;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView categoryListView;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        categoryListView = findViewById(R.id.category_list);
        categories = new ArrayList<>();


        categories.add(new Category(1, "Catégorie 1", R.drawable.produit1));
        categories.add(new Category(2, "Catégorie 2", R.drawable.produit2));
        categories.add(new Category(3, "Catégorie 3", R.drawable.produit3));
        categories.add(new Category(4, "Catégorie 4", R.drawable.produit4));


        categoryAdapter = new CategoryAdapter(this, categories);
        categoryListView.setAdapter(categoryAdapter);


        categoryListView.setOnItemClickListener((parent, view, position, id) -> {
            Category category = categories.get(position);
            Intent intent = new Intent(MainActivity.this, ProductActivity.class);
            intent.putExtra("categoryId", category.getId());
            startActivity(intent);
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        categoryListView.setOnItemLongClickListener((parent, view, position, id) -> {
            Category category = categories.get(position);
            showEditDeleteDialog(category);
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_category:

                showAddCategoryDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAddCategoryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ajouter une catégorie");
        builder.setMessage("Entrez le nom de la nouvelle catégorie");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Ajouter", (dialog, which) -> {
            String categoryName = input.getText().toString().trim();
            if (!categoryName.isEmpty()) {
                Category newCategory = new Category(categories.size() + 1, categoryName, R.drawable.produit4);
                categories.add(newCategory);
                categoryAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Catégorie ajoutée", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Nom de catégorie invalide", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Annuler", null);
        builder.show();
    }

    private void showEditDeleteDialog(Category category) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modifier ou Supprimer");
        builder.setItems(new CharSequence[] {"Modifier", "Supprimer"}, (dialog, which) -> {
            if (which == 0) {
                showEditCategoryDialog(category);
            } else {
                categories.remove(category);
                categoryAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Catégorie supprimée", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    private void showEditCategoryDialog(Category category) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modifier la catégorie");

        final EditText input = new EditText(this);
        input.setText(category.getName());
        builder.setView(input);

        builder.setPositiveButton("Modifier", (dialog, which) -> {
            String newName = input.getText().toString().trim();
            if (!newName.isEmpty()) {
                category.setName(newName);
                categoryAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Catégorie modifiée", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Nom invalide", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Annuler", null);
        builder.show();
    }
}

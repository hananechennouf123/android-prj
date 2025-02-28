package com.example.oujdashop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextNom, editTextPrenom, editTextEmail, editTextPassword, editTextConfirmPassword;
    private Button buttonRegister;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Désactiver l'Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_register);

        // Initialisation des vues
        editTextNom = findViewById(R.id.editTextNom);
        editTextPrenom = findViewById(R.id.editTextPrenom);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Initialisation de la base de données
        databaseHelper = new DatabaseHelper(this);

        // Écouteur du bouton d'inscription
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String nom = editTextNom.getText().toString().trim();
        String prenom = editTextPrenom.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        // Vérifier si tous les champs sont remplis
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // Vérifier si les mots de passe correspondent
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
            return;
        }

        // Vérifier si l'utilisateur existe déjà (selon l'email)
        if (databaseHelper.checkUser(email)) {
            Toast.makeText(this, "Cet email est déjà utilisé", Toast.LENGTH_SHORT).show();
            return;
        }

        // Enregistrer l'utilisateur dans la base de données
        boolean inserted = databaseHelper.registerUser(nom, prenom, email, password);
        if (inserted) {
            // Feedback de réussite
            Toast.makeText(this, "Inscription réussie !", Toast.LENGTH_SHORT).show();
            // Rediriger vers LoginActivity
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish(); // Ferme cette activité après l'inscription
        } else {
            // Feedback d'erreur
            Toast.makeText(this, "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show();
        }
    }
}

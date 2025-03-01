package com.example.oujdashop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oujdashop.MainActivity;
import com.example.oujdashop.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialisation des vues
        emailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.buttonLogin);
        Button registerButton = findViewById(R.id.textViewRegister);

        // Action du bouton "Se connecter"
        loginButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Vérification des identifiants (test avec email et mot de passe donnés)
            if (email.equals("hanane.chen123@gmail.com") && password.equals("123456")) {
                // Connexion réussie, redirection vers MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();  // Fermer LoginActivity pour éviter que l'utilisateur retourne sur cet écran
            } else {
                // Affichage d'un message d'erreur
                Toast.makeText(LoginActivity.this, "Identifiants incorrects", Toast.LENGTH_SHORT).show();
            }
        });

        // Action du bouton "Créer un compte"
        registerButton.setOnClickListener(view -> {
            // Redirection vers RegisterActivity pour l'inscription
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}

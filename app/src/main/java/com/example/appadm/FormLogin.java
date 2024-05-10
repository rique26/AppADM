package com.example.appadm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.appadm.ViewModel.FormLoginViewModel;
import com.google.android.material.snackbar.Snackbar;

public class FormLogin extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private FormLoginViewModel formLoginViewModel;
    private Button button_criar_conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        // Habilita o uso da biblioteca EdgeToEdge para ajustar as margens conforme a barra de status e navegação
        EdgeToEdge.enable(this);

        Log.i("LOG", "onCreate() FormLogin");
        // Define o listener de insets para ajustar as margens conforme a barra de status e navegação
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.formlogin), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializa o botão de criar conta
        button_criar_conta = findViewById(R.id.button_criar_conta);

        editTextEmail = findViewById(R.id.login);
        editTextPassword = findViewById(R.id.senha);

        formLoginViewModel = new ViewModelProvider(this).get(FormLoginViewModel.class);



    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("LOG", "onStart() FormLogin");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("LOG", "onResume() FormLogin");


        button_criar_conta.setOnClickListener(v -> {
            // Cria a intent para abrir a tela de cadastro oficial
            Intent intent = new Intent(FormLogin.this, TelaCadastroOficial.class);
            startActivity(intent);
        });

        Button buttonEntrar = findViewById(R.id.button_entrar);
        buttonEntrar.setOnClickListener(v -> {
            String email = getEmailFromEditText();
            String password = getPasswordFromEditText();
            formLoginViewModel.loginUser(email, password);

        });


        formLoginViewModel.observeLoginSuccess().observe(this, loginSuccess -> {
            if (loginSuccess) {
                openTelaControle();
            } else {
                // Mostrar mensagem de erro de login
            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("LOG", "onPause() FormLogin");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i("LOG", "onStop() FormLogin");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("LOG", "onDestroy() FormLogin");
    }



    private void openTelaControle() {
        Intent intent = new Intent(this, TelaControle.class);
        startActivity(intent);
    }

    private String getEmailFromEditText() {
        return editTextEmail.getText().toString();
    }

    // Método para obter a senha do campo de texto
    private String getPasswordFromEditText() {
        return editTextPassword.getText().toString();
    }


}

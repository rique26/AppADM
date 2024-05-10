package com.example.appadm;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TelaCadastroTeste extends AppCompatActivity {
/*
    private EditText edit_nome;
    private Button bt_continuar1;

    String[] mensagens = {"Preencha todos os campos", "Cadastro realizado com sucesso"};
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.telacadastro), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        IniciarComponentes();

        bt_continuar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edit_nome.getText().toString();

                
                if (nome.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.BLUE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    CadastrarUsuario();

                }
            }
        });
    }

    private void CadastrarUsuario() {
        String nome = edit_nome.getText().toString();

        if (nome.isEmpty()) {
            Snackbar.make(bt_continuar1, "Preencha todos os campos", Snackbar.LENGTH_SHORT).show();
        } else {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword("email@exemplo.com", "senha123")
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String usuarioID = user.getUid();
                                SalvarDadosUsuarios(usuarioID, nome);



                            } else {
                                Snackbar.make(bt_continuar1, "Erro ao criar usuário", Snackbar.LENGTH_SHORT).show();
                            }
                        } else {
                            Snackbar.make(bt_continuar1, "Erro ao criar usuário: " + task.getException().getMessage(), Snackbar.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void SalvarDadosUsuarios(String usuarioID, String nome) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> usuarios = new HashMap<>();
        usuarios.put("nome", nome);

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.set(usuarios)
                .addOnSuccessListener(unused -> {
                    Log.d("db", "Sucesso ao salvar os dados");
                    Snackbar.make(bt_continuar1, "Cadastro realizado com sucesso", Snackbar.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Log.d("db_error", "Erro ao salvar os dados", e);
                    Snackbar.make(bt_continuar1, "Erro ao realizar o cadastro", Snackbar.LENGTH_SHORT).show();
                });
    }



    private void IniciarComponentes() {
        edit_nome = findViewById(R.id.edit_nome);
        bt_continuar1 = findViewById(R.id.bt_continuar1);

    }

*/
}

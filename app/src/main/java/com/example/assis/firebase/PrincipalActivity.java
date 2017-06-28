package com.example.assis.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity {
    private Button btnTeste;
    private Button btnLancamentos;
    private Button btnConsultas;
    private Button btnAgenda;
    private Button btnRelatorios;
    private Button btnAjustes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnTeste = (Button) findViewById(R.id.btnTeste);
        btnLancamentos = (Button) findViewById(R.id.btnLancamentos);
        btnConsultas = (Button) findViewById(R.id.btnConsultas);
        btnAgenda = (Button) findViewById(R.id.btnAgenda);
        btnRelatorios = (Button) findViewById(R.id.btnRelatorios);
        btnAjustes = (Button) findViewById(R.id.btnAjustes);


        btnConsultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirConsultas = new Intent(PrincipalActivity.this, SecundarioConsultas.class);
                startActivity(intentAbrirConsultas);
            }
        });
        btnLancamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirLancamentos = new Intent(PrincipalActivity.this, SecundarioLancamentos.class);
                startActivity(intentAbrirLancamentos);
            }
        });
        btnAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirCadastroAnimais = new Intent(PrincipalActivity.this, SecundarioAgenda.class);
                startActivity(intentAbrirCadastroAnimais);
            }
        });
        btnRelatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirRelatorios = new Intent(PrincipalActivity.this, SecundarioRelatorios.class);
                startActivity(intentAbrirRelatorios);
            }
        });
        btnAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirAjustes = new Intent(PrincipalActivity.this, SecundarioAjustes.class);
                startActivity(intentAbrirAjustes);
            }
        });
        btnTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirTeste = new Intent(PrincipalActivity.this, SecundarioCad.class);
                startActivity(intentAbrirTeste);
            }
        });

    }
}

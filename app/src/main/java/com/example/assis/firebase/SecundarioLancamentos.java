package com.example.assis.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecundarioLancamentos extends AppCompatActivity {
    private Button buttonLancLeiteiro;
    private Button buttonLancReproducao;
    private Button buttonLancFinanceiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundario_lancamentos);

        buttonLancLeiteiro = (Button) findViewById(R.id.buttonLancLeiteiro);
        buttonLancReproducao = (Button) findViewById(R.id.buttonLancReproducao);
        buttonLancFinanceiro = (Button) findViewById(R.id.buttonLancFinanceiro);


        buttonLancFinanceiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirLancFinan = new Intent(SecundarioLancamentos.this, LancContFinan.class);
                startActivity(intentAbrirLancFinan);
            }
        });
        buttonLancReproducao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirLancRep = new Intent(SecundarioLancamentos.this, LancContRep.class);
                startActivity(intentAbrirLancRep);
            }
        });
        buttonLancLeiteiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirContLeit = new Intent(SecundarioLancamentos.this, LancContLeite.class);
                startActivity(intentAbrirContLeit);
            }
        });


    }

}

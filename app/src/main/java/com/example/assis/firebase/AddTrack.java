package com.example.assis.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Transaction;

import Helper.Track;

public class AddTrack extends AppCompatActivity {

    TextView textViewNomeAni;
    ListView listViewTrackAnimais;
    EditText editTextTrackNome;
    SeekBar seekBarRating;

    DatabaseReference databaseTrack;

    Button btnAddTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track);
        textViewNomeAni = (TextView) findViewById(R.id.editTextTNomeAni);
        listViewTrackAnimais = (ListView) findViewById(R.id.listViewTrackAnimais);
        editTextTrackNome = (EditText) findViewById(R.id.edtTrackNomeAni);
        seekBarRating = (SeekBar) findViewById(R.id.seekBarRating);
        btnAddTrack = (Button) findViewById(R.id.btnTrackAni);

        Intent intent = getIntent();

        String id = intent.getStringExtra(SecundarioCad.ARTIST_ID);
        String nome = intent.getStringExtra(SecundarioCad.ARTIST_NAME);

        textViewNomeAni.setText(nome);
        //editTextTrackNome.setText(nome);

        databaseTrack = FirebaseDatabase.getInstance().getReference("track").child(id);
    }

        /* ANEXAR DOIS ARQUIVOS, VAI SER ÚTTIL PARA FAZER A LIGAÇÃO ENTRE O BANCO DE DADOS CADASTRO E PRODUÇÃO E REPRODUÇÃO
        ALTERAR A ENTIDADE TRACK PARA ACRESCENTAR AS OUTRAS VARIÁVEIS, SÓ FOI O NOME
        btnAddTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saveTrack ();
            }
        });

    }

    public void saveTrack (){
            String trackNome = editTextTrackNome.getText().toString().trim();
            int rating = seekBarRating.getProgress();

            if(!TextUtils.isEmpty(trackNome)){
                String codigo = databaseTrack.push().getKey();

                Track track = new Track(codigo, trackNome, rating);

                databaseTrack.child(codigo).setValue(track);
                Toast.makeText(this, "Sucesso", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Insira o nome do animal", Toast.LENGTH_LONG).show();
            }
    }*/
}

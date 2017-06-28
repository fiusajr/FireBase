package com.example.assis.firebase;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Entidades.Animal;
import Entidades.AnimalProducao;
import Helper.AnimalList;
import Helper.AnimalListProducao;
import Helper.DateDialogContLeite;

public class LancContLeite extends AppCompatActivity {

    private EditText editTextDataContLeite;

    ListView listViewAnimaisLan;

    List<Animal> animaisLan;

    DatabaseReference databaseAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanc_cont_leite);

        databaseAnimal = FirebaseDatabase.getInstance().getReference("fazenda");

        editTextDataContLeite = (EditText) findViewById(R.id.editTextDataContLeite);

        listViewAnimaisLan = (ListView) findViewById(R.id.listViewAnimaisLan);

        animaisLan = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseAnimal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                animaisLan.clear();
                for (DataSnapshot artistSnaphot : dataSnapshot.getChildren()) {
                    Animal animal = artistSnaphot.getValue(Animal.class);
                    animaisLan.add(animal);
                }

                AnimalList adapter = new AnimalList(LancContLeite.this, animaisLan);
                listViewAnimaisLan.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}



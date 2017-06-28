package com.example.assis.firebase;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.Service;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Entidades.Animal;
import Helper.AnimalList;
import Helper.DateDialog;

public class SecundarioCad extends AppCompatActivity {

    public static final String ARTIST_NAME =  "animalname";
    public static final String ARTIST_ID =  "animalid";


    private EditText editTextAnimalNome;
    private EditText editTextAnimalData;
    private EditText editTextAnimalNumero;
    private Spinner spinnerAnimalRaca;
    private Spinner spinnerAnimalSexo;
    private Button buttonAdd;
    ListView listViewAnimais;

    List<Animal> animais;

    DatabaseReference databaseAnimal;

    //private FirebaseAuth autenticacaoAni;

    InputMethodManager imm;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundario_cad);

        //CRIANDO O BANCO DE DADOS
        databaseAnimal = FirebaseDatabase.getInstance().getReference("fazenda");


        spinnerAnimalSexo = (Spinner) findViewById(R.id.spinnerAnimalSexo);
        spinnerAnimalRaca = (Spinner) findViewById(R.id.spinnerAnimalRaca);
        editTextAnimalNome = (EditText) findViewById(R.id.editTextAnimalNome);
        editTextAnimalNumero = (EditText) findViewById(R.id.editTextAnimalNumero);
        editTextAnimalData = (EditText) findViewById(R.id.editTextAnimalData);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        listViewAnimais = (ListView) findViewById(R.id.listViewAnimais);


        imm = (InputMethodManager) this.getSystemService(Service.INPUT_METHOD_SERVICE);

        animais = new ArrayList<>();


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnimal();
                limpaCampos();
                escondeTeclado();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseAnimal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                animais.clear();
                for (DataSnapshot artistSnaphot : dataSnapshot.getChildren()) {
                    Animal animal = artistSnaphot.getValue(Animal.class);
                    animais.add(animal);
                }

                AnimalList adapter = new AnimalList(SecundarioCad.this, animais);
                listViewAnimais.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listViewAnimais.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                Animal animal = animais.get(i);

                showUpdateDialog(animal.getAnimalId(), animal.getAnimalNome(), animal.getAnimalNumero(), animal.getAnimalDataNascimento(), animal.getAnimalRaca(),
                        animal.getAnimalSexo());

                return false;
            }
        });


        editTextAnimalData.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });


    }
    private void addAnimal(){
        String name = editTextAnimalNome.getText().toString().trim();
        String numero = editTextAnimalNumero.getText().toString();
        String datan = editTextAnimalData.getText().toString();
        String raca = spinnerAnimalRaca.getSelectedItem().toString();
        String sexo = spinnerAnimalSexo.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){
            String id = databaseAnimal.push().getKey();

            Animal animal = new Animal(id, name,  numero, datan, raca, sexo);

            databaseAnimal.child(id).setValue(animal);
            Toast.makeText(this,"Animal cadastrado com sucesso", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Digite um nome", Toast.LENGTH_LONG).show();
        }
    }




    void limpaCampos (){

        editTextAnimalNome.setText("");
        editTextAnimalNumero.setText("");
        editTextAnimalData.setText("");
        editTextAnimalNome.requestFocus();
    }

    void  escondeTeclado (){
        imm.hideSoftInputFromInputMethod(editTextAnimalNome.getWindowToken(),0);
    }



    private void showUpdateDialog (final String animalId, String animalNome, String animalNumero, String animalDataNascimento,String animalRaca, String animalSexo){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater =getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update,null);

        dialogBuilder.setView(dialogView);

        final EditText editTextNomeAniUp = (EditText) dialogView.findViewById(R.id.editTextNomeAniUp);
        final EditText editTextNumeroAniUp = (EditText) dialogView.findViewById(R.id.editTextNumeroAniUp);
        final EditText editTextDataAniUp = (EditText) dialogView.findViewById(R.id.editTextDataAniUp);
        final Spinner spinnerAnimalRacaUp = (Spinner) dialogView.findViewById(R.id.spinnerAnimalRacaUp);
        final Spinner spinnerAnimalSexoUp = (Spinner) dialogView.findViewById(R.id.spinnerAnimalSexoUp);

        final Button btnUpdate = (Button) dialogView.findViewById(R.id.btnUpdate);
        final Button btnDeletar = (Button) dialogView.findViewById(R.id.btnDeletar);

        dialogBuilder.setTitle("Editando cadastro");
        editTextNomeAniUp.setText(animalNome);
        editTextNumeroAniUp.setText(animalNumero);
        editTextDataAniUp.setText(animalDataNascimento);

        spinnerAnimalRacaUp.setSelection(getIndex(spinnerAnimalRacaUp, animalRaca));
        spinnerAnimalSexoUp.setSelection(getIndex(spinnerAnimalSexoUp, animalSexo));

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

       btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAnimal(animalId);
                alertDialog.dismiss();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editTextNomeAniUp.getText().toString().trim();
                String numero = editTextNumeroAniUp.getText().toString();
                String datan = editTextDataAniUp.getText().toString();
                String raca = spinnerAnimalRacaUp.getSelectedItem().toString();
                String sexo = spinnerAnimalSexoUp.getSelectedItem().toString();

                if(TextUtils.isEmpty(nome)){
                    editTextNomeAniUp.setError("Coloque um nome");
                    return;
                }
                updateAnimal(animalId, nome, numero, datan,raca, sexo);
                alertDialog.dismiss();
            }
        });
        editTextDataAniUp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });


    }

    private boolean updateAnimal(String id, String nome, String numero, String datan, String raca, String sexo){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("fazenda").child(id);
        Animal animal = new Animal(id, nome, numero, datan, raca, sexo);

        databaseReference.setValue(animal);
        Toast.makeText(this, "Editado com sucesso", Toast.LENGTH_LONG).show();
        return true;
    }

    private void deleteAnimal(String animalId){
        DatabaseReference drAnimal = FirebaseDatabase.getInstance().getReference("fazenda").child(animalId);
        //DatabaseReference drTracks = FirebaseDatabase.getInstance().getReference("tracks").child(animalId);

        drAnimal.removeValue();
        //drTracks.removeValue();

        Toast.makeText(this, "Animal apagado com sucesso", Toast.LENGTH_LONG).show();
    }

    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }

}



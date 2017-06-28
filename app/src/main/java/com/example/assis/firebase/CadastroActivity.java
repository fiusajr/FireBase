package com.example.assis.firebase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import DAO.ConfiguracaoFirebase;
import Entidades.Usuarios;
import Helper.Base64Custom;
import Helper.Preferencias;

public class CadastroActivity extends AppCompatActivity {

    private EditText editCadNome;
    private EditText editCadSobrenome;
    private EditText editCadEmail;
    private EditText editCadSenha;
    private EditText editCadAniversario;
    private EditText editCadConfirmarSenha;
    private RadioButton rbMasculino;
    private RadioButton rbFemenino;
    private Button btnGravar;
    private Usuarios usuarios;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editCadNome = (EditText)findViewById(R.id.editCadNome);
        editCadSobrenome = (EditText)findViewById(R.id.editCadSobrenome);
        editCadEmail = (EditText)findViewById(R.id.editCadEmail);
        editCadSenha = (EditText)findViewById(R.id.editCadSenha);
        editCadConfirmarSenha = (EditText)findViewById(R.id.editCadConfirmarSenha);
        editCadAniversario = (EditText)findViewById(R.id.editCadAniversario);
        rbMasculino =(RadioButton) findViewById(R.id.rbMasculino);
        rbFemenino =(RadioButton) findViewById(R.id.rbFemenino);
        btnGravar = (Button) findViewById(R.id.btnGravar);

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editCadSenha.getText().toString().equals(editCadConfirmarSenha.getText().toString())){
                    usuarios = new Usuarios();
                    usuarios.setNome(editCadNome.getText().toString());
                    usuarios.setSobrenome(editCadSobrenome.getText().toString());
                    usuarios.setEmail(editCadEmail.getText().toString());
                    usuarios.setAniversario(editCadAniversario.getText().toString());
                    usuarios.setSenha(editCadSenha.getText().toString());

                    if(rbFemenino.isChecked()){
                        usuarios.setSexo("Femenino");
                    } else{
                        usuarios.setSexo("Masculino");
                    }
                    cadastrarUsuario();

                }else{
                    Toast.makeText(CadastroActivity.this, "As sennhas não são correspondentes", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void cadastrarUsuario(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuarios.getEmail(),
                usuarios.getSenha()
        ).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(CadastroActivity.this, "Usuário cadastrado com sucesso", Toast.LENGTH_LONG).show();

                        String identificadorUsuario = Base64Custom.codificarBase64(usuarios.getEmail());
                        FirebaseUser usuarioFirebase = task.getResult().getUser();
                        usuarios.setId(identificadorUsuario);
                        usuarios.salvar();

                        Preferencias preferencias = new Preferencias(CadastroActivity.this);
                        preferencias.salvarUsuarioPreferencias(identificadorUsuario, usuarios.getNome());
                        abrirLoginUsuario();
                    } else {
                        String erroExcessao = "";
                        try {
                            throw task.getException();
                        }catch (FirebaseAuthWeakPasswordException e){
                            erroExcessao= "Digite uma senha mais forte, contendo no mínimo 8 caracteres";
                        }catch (FirebaseAuthInvalidCredentialsException e){
                            erroExcessao= "O email digitado é inválido, digite um novo email";

                        }catch (FirebaseAuthUserCollisionException e){
                            erroExcessao= "Esse email já está cadastrado no sistema";

                        }catch (Exception e){
                            erroExcessao= "Erro ao efetuar o cadastro";
                            e.printStackTrace();
                        }
                        Toast.makeText(CadastroActivity.this, "Erro: " + erroExcessao, Toast.LENGTH_LONG).show();
                    }
            }
        });
    }
    public void abrirLoginUsuario (){
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}

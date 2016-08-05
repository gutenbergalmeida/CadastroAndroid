package com.ufpb.rennanfelizardo.formulariocadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity {

    private EditText mNome;
    private EditText mSobrenome;
    private EditText mEmail;
    private EditText mSenha;
    private EditText mConfirmarSenha;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mNome = (EditText) findViewById(R.id.nome_campo);
        mSobrenome = (EditText) findViewById(R.id.sobrenome_campo);
        mEmail = (EditText) findViewById(R.id.email_campo);
        mSenha = (EditText) findViewById(R.id.senha_campo);
        mConfirmarSenha = (EditText) findViewById(R.id.confirmar_senha_campo);
        mButton = (Button) findViewById(R.id.cadastrar_botao);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verificarCampos()){
                    enviarDados();
                };
            }
        });
    }

    public boolean verificarCampos(){
        String email = mEmail.getText().toString();
        String senha = mSenha.getText().toString();
        String confirmarSenha = mConfirmarSenha.getText().toString();
        boolean enviar = true;

        View focus = null;

        if (!verificarNome(mNome)){
            focus = mNome;
            enviar = false;
        }

        if (!verificarNome(mSobrenome)){
            focus = mSobrenome;
            enviar = false;
        }

        if (!verificarEmail(email)){
            focus = mEmail;
            enviar = false;
        }

        if (!verificarSenha(senha, confirmarSenha)){
            focus = mSenha;
            enviar = false;
        }


        if(focus != null){
            focus.requestFocus();
        }

       return enviar;

    }

    public boolean verificarNome(EditText nome){

        if (TextUtils.isEmpty(nome.getText().toString())){
            nome.setError("Campo vazio");
            return false;
        }else{
            if (nome.getText().toString().matches(".*\\d.*")){
                mNome.setError("Contém números");
                return false;
            };
            return true;
        }
    }

    public boolean verificarEmail(String email){
        if (TextUtils.isEmpty(email)){
            mEmail.setError("Campo vazio");
        }
        else{
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                mEmail.setError("E-mail inválido");
                return false;
            };
        }
        return true;
    }

    public boolean verificarSenha(String senha, String confirmarSenha){
        if (TextUtils.isEmpty(senha)){
            mSenha.setError("Campo vazio");
            return false;
        }
        if (TextUtils.isEmpty(confirmarSenha)){
            mConfirmarSenha.setError("Campo vazio");
            return false;
        }

        if (!TextUtils.isEmpty(senha) && !TextUtils.isEmpty(confirmarSenha) && !TextUtils.equals(senha, confirmarSenha)){
            mSenha.setError("As senhas não correspondem");
            mConfirmarSenha.setError("As senhas não correspondem");
            return false;
        }
        return true;
    }

    public void enviarDados(){
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("nome", mNome.getText().toString());
        bundle.putString("sobrenome", mSobrenome.getText().toString());
        bundle.putString("email", mEmail.getText().toString());
        bundle.putString("senha", mSenha.getText().toString());
        intent.putExtras(bundle);

        startActivity(intent);
        finish();
    }
}

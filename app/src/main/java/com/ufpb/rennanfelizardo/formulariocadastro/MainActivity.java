package com.ufpb.rennanfelizardo.formulariocadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        String nome = bundle.getString("nome");
        mMensagem = (TextView) findViewById(R.id.mensagem);

        String mensagem = "Olá " +nome+ "!";

        mMensagem.setText(mensagem);





    }
}

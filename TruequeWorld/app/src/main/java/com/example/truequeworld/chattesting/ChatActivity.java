package com.example.truequeworld.chattesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.User;
import com.example.truequeworld.HiloCliente;
import com.example.truequeworld.R;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatActivity extends AppCompatActivity {
    HiloCliente hiloCliente;
    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        product = (Product) getIntent().getSerializableExtra("producto");
        User user = (User) getIntent().getSerializableExtra("user");
        Toast.makeText(this, product.getNombre(), Toast.LENGTH_SHORT).show();

        hiloCliente = new HiloCliente(user,this);
        hiloCliente.start();
    }

    public void enviarMensaje(View view){
        EditText editText = findViewById(R.id.texto);
        String texto = editText.getText().toString();

        try {
            DataOutputStream dos = hiloCliente.dos;
            dos.writeUTF(texto);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
package com.example.truequeworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.truequeworld.Class.Mensaje;
import com.example.truequeworld.retrofit.RetrofitConexion;

import java.net.URISyntaxException;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CHAT extends AppCompatActivity {
    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Call<List<Mensaje>> call3 = RetrofitConexion.getMensajeServiceApi().getMensajes();
        call3.enqueue(new Callback<List<Mensaje>>() {
            @Override
            public void onResponse(Call<List<Mensaje>> call, Response<List<Mensaje>> response) {
                Toast.makeText(CHAT.this, response.body().get(0).getTexto(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Mensaje>> call, Throwable t) {
                Toast.makeText(CHAT.this, "esto no funciona", Toast.LENGTH_SHORT).show();
            }
        });
        Call<Boolean> call1 = RetrofitConexion.getMensajeServiceApi().getConexion("2");
        call1.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Toast.makeText(CHAT.this, "si", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(CHAT.this, "no", Toast.LENGTH_SHORT).show();
            }
        });

        try {
            socket = IO.socket("http://192.168.128.235:8086");
            socket.connect();
            socket.on("latestNews", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Toast.makeText(CHAT.this, args.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Button button = findViewById(R.id.button99);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Boolean> call = RetrofitConexion.getMensajeServiceApi().enviarMensaje("hola","2");
                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        Toast.makeText(CHAT.this, "envie", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(CHAT.this, "No envie", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
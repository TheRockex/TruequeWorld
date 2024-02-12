package com.example.truequeworld;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.truequeworld.Class.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloCliente extends Thread{
    private User user;
    Context context;
    public DataOutputStream dos;

    public HiloCliente(User user,Context context){
        this.user = user;
        this.context = context;
    }

    public void run(){
        try {
            boolean continuar = true;
            Socket socket = new Socket("192.168.128.235", 6565);
            dos = new DataOutputStream(socket.getOutputStream());
            DataOutputStream dos2 = new DataOutputStream(socket.getOutputStream());
            dos2.writeUTF(user.getName());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            String mensaje;

            while (continuar) {
                // Recibimos la respuesta del server
                mensaje = dis.readUTF();
                Toast.makeText(context, "MENSAJE RECIBIDO", Toast.LENGTH_SHORT).show();

                continuar = !mensaje.equals("EXIT");
            }
            dis.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

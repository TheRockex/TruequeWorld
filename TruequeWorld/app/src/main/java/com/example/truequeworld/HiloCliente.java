package com.example.truequeworld;

import com.example.truequeworld.Class.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloCliente extends Thread{
    private User user;

    public HiloCliente(User user){
        this.user = user;
    }

    public void run(){
        try {
            boolean continuar = true;
            Socket socket = new Socket("192.168.128.235", 6565);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(user.getName());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            String mensaje;

            while (continuar) {
                // Recibimos la respuesta del server
                mensaje = dis.readUTF();

                continuar = !mensaje.equals("EXIT");
            }

            // Cierre de todas las conexiones o streams de datos
            dos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package com.dam.truequeworld.hiloCliente;

import com.dam.truequeworld.TruequeWorldApplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloCliente extends Thread {
    public Socket socket;
    public String name;

    public HiloCliente(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        String mensaje;

        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            do {
                mensaje = dis.readUTF();
                System.out.println("Mensaje de " + socket.getInetAddress().getHostName() + " recibido: " + mensaje);
                if(mensaje.startsWith("P")) {
                    String[] s = mensaje.split(" ",2);
                    DataOutputStream dos = new DataOutputStream(TruequeWorldApplication.getSoketDestino(s[0]).getOutputStream());
                    dos.writeUTF(socket.getInetAddress().getHostName() + ": " + s[1]);
                }else {
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeUTF(mensaje);
                }

            } while (!mensaje.equals("exit"));
            dis.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

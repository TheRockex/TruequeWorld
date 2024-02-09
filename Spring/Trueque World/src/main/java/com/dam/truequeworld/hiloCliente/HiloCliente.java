package com.dam.truequeworld.hiloCliente;

import com.dam.truequeworld.TruequeWorldApplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloCliente extends Thread {
    private Socket socket;
    private String name;

    public HiloCliente(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    public void run() {
        String mensaje;

        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            do {
                mensaje = dis.readUTF();
                System.out.println("Mensaje de " + name + " recibido: " + mensaje);
                if(!mensaje.startsWith("EXIT")) {
                    String[] s = mensaje.split(" ",2);
                    DataOutputStream dos = new DataOutputStream(TruequeWorldApplication.getSoketDestino(s[0]).getOutputStream());
                    if(dos != null){
                        dos.writeUTF(s[1]);
                    }
                }else {
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeUTF(mensaje);
                }

            } while (!mensaje.equals("EXIT"));
            dis.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Socket getSocketConnection(){
        return socket;
    }

    public String getConnectionName(){
        return name;
    }
}

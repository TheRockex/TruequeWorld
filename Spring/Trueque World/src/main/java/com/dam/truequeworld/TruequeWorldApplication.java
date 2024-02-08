package com.dam.truequeworld;

import com.dam.truequeworld.hiloCliente.HiloCliente;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

@SpringBootApplication
public class TruequeWorldApplication {
	public static ArrayList<HiloCliente> listaHilos = new ArrayList<HiloCliente>();
	public static void main(String[] args) {
		SpringApplication.run(TruequeWorldApplication.class, args);
		try (ServerSocket serverSocket = new ServerSocket(6565);) {
			while (true) {
				Socket socketCliente = serverSocket.accept();
				socketCliente.set
				listaHilos.add(new HiloCliente(socketCliente));
				listaHilos.get(listaHilos.size()-1).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Socket getSoketDestino(String namePC) {
		for(HiloCliente h : listaHilos) {
			if(h.socket.getInetAddress().getHostName().equals(namePC)) {
				return h.socket;
			}
		}
		return null;
	}

}

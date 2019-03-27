package com.example.carcontrol;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TCP_comm {

    public void TCP_send(final String command, final String ip,final int port, final int ACTION) {

        Thread  tcpThread = new Thread(new Runnable() {
            Socket clientSocket;
            private PrintWriter outToServer;
            BufferedReader inFromServer;
            String data;
            @Override
            public void run (){
                try {

                    InetAddress serverAddr = InetAddress.getByName(ip);
                    clientSocket = new Socket(serverAddr, port);

                    outToServer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
                    if (ACTION == 0)
                        outToServer.println(command);
                    else
                        outToServer.println("G\0");
                    outToServer.flush();
                    clientSocket.close();
                } catch (Exception e){}
            }

        });tcpThread.start();

    }

}

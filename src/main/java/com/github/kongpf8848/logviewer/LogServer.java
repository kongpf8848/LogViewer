package com.github.kongpf8848.logviewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class LogServer {

    public static int PORT = 5036;

    public static void startServer(LogPrinter callback) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            callback.log("listening,port:"+PORT+",waiting for device connect......");
            while (true) {
                try {
                    Socket client = server.accept();
                    callback.log("client:" + client.getInetAddress() + "/" + client.getPort() + " is connected");
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        String line = "";
                        while ((line = bufferedReader.readLine()) != null) {
                            callback.log(line);
                        }
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

package com.github.kongpf8848.logviewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class LogServer {

    public static int PORT = 5036;

    public static void startServer(LogCallback callback) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            callback.printLog("waiting for device connect ");
            while (true) {
                try {
                    Socket client = server.accept();
                    callback.printLog("client:" + client.getInetAddress() + ":" + client.getPort() + " is connected");
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        String line = "";
                        while ((line = bufferedReader.readLine()) != null) {
                            callback.printLog(line);
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

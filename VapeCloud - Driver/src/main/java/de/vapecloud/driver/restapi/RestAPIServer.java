package de.vapecloud.driver.restapi;

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.SettingsConfig;
import de.vapecloud.driver.console.logger.enums.MessageType;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RestAPIServer {

    public Integer port;
    public boolean shutdown = false;

    public RestAPIServer() {}


    public RestAPIServer bind(Integer port){
        this.port = port;
        return this;
    }

    @SneakyThrows
    public void create() {

        new Thread(() -> {
            ServerSocket serverSocket;
            try {
                serverSocket = new ServerSocket(port);
                initReader(serverSocket);
            } catch (IOException e) {

            }

        }).start();

    }



    public void shutdown(){
        this.shutdown = true;
    }



    private void initReader( ServerSocket serverSocket){
        while (shutdown != true) {
            try {
                Socket clientSocket = serverSocket.accept();
                OutputStream clientOutput = clientSocket.getOutputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String s = in.readLine();
                if (s != null){
                    if(!s.equalsIgnoreCase(" GET /favicon.ico HTTP/1.1") && s.contains("/") && s.contains("GET")){
                        String clearText = s.replace("GET /", "").replace(" HTTP/1.1", "");
                        if (clearText.contains("/")){
                            String token = clearText.split("/")[0];
                            String request = clearText.split("/")[1];

                            SettingsConfig settingsConfig = (SettingsConfig)new ConfigHandler("./settings.json").getConfig(SettingsConfig.class);

                            if (token.equals(settingsConfig.getInternalAuthKey())){
                                deployPage(clientOutput, "{\n" +
                                        "  \"connectionAccept\": true\n"+
                                        "}");
                            }else {
                                deployPage(clientOutput, "{\n" +
                                        "  \"connectionAccept\": false,\n" +
                                        "  \"reason\": \"false token was enter\"\n" +
                                        "}");
                            }
                        }else{
                            deployPage(clientOutput, "{\n" +
                                    "  \"connectionAccept\": false,\n" +
                                    "  \"reason\": \"false data was enter\"\n" +
                                    "}");
                        }
                    }
                }
                in.close();
                clientOutput.close();
            } catch (IOException e) {
                initReader(serverSocket);
            }


        }
    }

    @SneakyThrows
    private void deployPage(OutputStream clientOutput, String json){
        clientOutput.write("HTTP/1.1 200 OK Content-Type: application/json charset: utf-8\r\n".getBytes());
        clientOutput.write("\r\n".getBytes());
        clientOutput.write("<title> VapeCloud | RestAPI </title>".getBytes());
        clientOutput.write("<link rel=\"icon\" type=\"image/x-icon\" href=\"https://i.ibb.co/LSfzr1p/icon.png\">".getBytes());
        clientOutput.write("\r\n".getBytes());
        clientOutput.write(json.getBytes());
        clientOutput.write("\r\n\r\n".getBytes());
        clientOutput.flush();
    }


}

package de.vapecloud.driver.utils.download;


import java.io.*;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * Projectname: VapeCloud
 * Created AT: 30.12.2021/19:06
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.container.enums.ContainerVersion;

public class Downloader {

    String url = "";
    String fileName = "";
    String filePath = "";

    public Downloader(String fileName, String filePath, String url){
        this.url = url;
        this.fileName = fileName;
        this.filePath = filePath;
        startDownload();
    }

    public Downloader(String fileName, String filePath, ContainerVersion containerVersion){
        this.fileName = fileName;
        this.filePath = filePath;

        switch (containerVersion){
            case PAPERSPIGOT_1_8_8:
                this.url = "https://papermc.io/api/v2/projects/paper/versions/1.8.8/builds/443/downloads/paper-1.8.8-443.jar";
                break;
            case PAPERSPIGOT_1_9_4:
                this.url = "https://papermc.io/api/v2/projects/paper/versions/1.9.4/builds/775/downloads/paper-1.9.4-775.jar";
                break;
            case PAPERSPIGOT_1_10_2:
                this.url = "https://papermc.io/api/v2/projects/paper/versions/1.10.2/builds/918/downloads/paper-1.10.2-918.jar";
                break;
            case PAPERSPIGOT_1_11_2:
                this.url = "https://papermc.io/api/v2/projects/paper/versions/1.11.2/builds/1106/downloads/paper-1.11.2-1106.jar";
                break;
            case PAPERSPIGOT_1_12_2:
                this.url = "https://papermc.io/api/v2/projects/paper/versions/1.12.2/builds/1620/downloads/paper-1.12.2-1620.jar";
                break;
            case PAPERSPIGOT_1_13_2:
                this.url = "https://papermc.io/api/v2/projects/paper/versions/1.13.2/builds/657/downloads/paper-1.13.2-657.jar";
                break;
            case PAPERSPIGOT_1_14_4:
                this.url = "https://papermc.io/api/v2/projects/paper/versions/1.14.4/builds/245/downloads/paper-1.14.4-245.jar";
                break;
            case PAPERSPIGOT_1_15_2:
                this.url = "https://papermc.io/api/v2/projects/paper/versions/1.15.2/builds/393/downloads/paper-1.15.2-393.jar";
                break;
            case PAPERSPIGOT_1_16_5:
                this.url = "https://papermc.io/api/v2/projects/paper/versions/1.16.5/builds/794/downloads/paper-1.16.5-794.jar";
                break;
            case PAPERSPIGOT_1_17_1:
                this.url = "https://papermc.io/api/v2/projects/paper/versions/1.17.1/builds/404/downloads/paper-1.17.1-404.jar";
                break;
            case PAPERSPIGOT_1_18_1:
                this.url = "https://papermc.io/api/v2/projects/paper/versions/1.18.1/builds/112/downloads/paper-1.18.1-112.jar";
                break;
            case WATERFALL_LATEST:
                this.url = "https://papermc.io/api/v2/projects/waterfall/versions/1.18/builds/474/downloads/waterfall-1.18-474.jar";
                break;
            case BUNGEECORD_LATEST:
                this.url = "https://ci.md-5.net/job/BungeeCord/lastSuccessfulBuild/artifact/bootstrap/target/BungeeCord.jar";
                break;
        }
        startDownload();

    }

    private void startDownload() {

        a("try to download the file §e" + fileName);


        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(filePath + "/" + fileName)) {
            byte dataBuffer[] = new byte[1024];

            int bytesRead;


            byte anime = 0;
            for(int j = 0 ; j<= 10 ; j++)
            {
                if(j == 0){
                    System.out.print("\r" + j*10 + "% [>                                        ]");
                } if(j == 1){
                System.out.print("\r" + j*10 + "% [====>                                    ]");
            }

                if(j == 2){
                    System.out.print("\r" + j*10 + "% [========>                                ]");
                }
                if(j == 3){
                    System.out.print("\r" + j*10 + "% [============>                            ]");
                }
                if(j == 4){
                    System.out.print("\r" + j*10 + "% [================>                        ]");
                }
                if(j == 5){
                    System.out.print("\r" + j*10 + "% [====================>                    ]");
                }
                if(j == 6){
                    System.out.print("\r" + j*10 + "% [========================>                ]");
                }
                if(j == 7){
                    System.out.print("\r" + j*10 + "% [============================>            ]");
                }
                if(j == 8){
                    System.out.print("\r" + j*10 + "% [================================>        ]");
                }
                if(j == 9){
                    System.out.print("\r" + j*10 + "% [====================================>    ]");
                }

                if(j == 10){
                    System.out.print("\r" + j*10 + "% [========================================>]");
                }
                Thread.sleep(100);
            }
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }

            a("§eSuccesfully§7 downloaded §e" + fileName);

        } catch (IOException | InterruptedException error) {

            a("Error downloading §e" + fileName + " from " + url + "!");
        }
    }

    private void a(String message) {
        if (VapeDriver.getInstance().getVapeSettings().getSetupData().inSetup){
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, false, message);
        }else{
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, message);
        }
    }

}

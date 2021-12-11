package de.vapecloud.driver.configuration;
/*
 * Created AT: 11.12.2021
 * Created by Robin B. (UniqueByte)
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import de.vapecloud.driver.VapeDriver;
import lombok.SneakyThrows;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class IConfigHandler {

    protected static final Gson GSON = (new GsonBuilder()).serializeNulls().setPrettyPrinting().disableHtmlEscaping().create();
    protected static final JsonParser PARSER = new JsonParser();
    private String filelocation;
    private final Gson gson;



    public  IConfigHandler(String location){
        this.gson = new Gson();
        this.filelocation = location;
    }


    @SneakyThrows
    public IConfig getConfig(Class<? extends IConfig> tClass){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(this.filelocation), tClass);
    }



    public String convertToString(IConfig iconfig){
        return gson.toJson(iconfig);
    }

    public String convertToJson(IConfig iconfig) {
        return GSON.toJson(iconfig);
    }

    public boolean canFinde(){
        if(new File(this.filelocation).exists()){
            return true;
        }
        return false;
    }


    public void saveConfig(IConfig iconfig){
        if(iconfig != null && this.filelocation != null){

            if(!canFinde()){
                try {
                    new File(this.filelocation).createNewFile();
                } catch (IOException ignored) {}
            }

            try {
                try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(this.filelocation), StandardCharsets.UTF_8)) {
                    GSON.toJson(iconfig, writer);
                } catch (IOException e) {
                    VapeDriver.consolHandler.getiLogger().error(false, "" + e.getMessage(), null);
                }
            }catch (Exception ignored){}
        }else{
            VapeDriver.consolHandler.getiLogger().error(false, "The location or the config is null", null);
        }
    }


}

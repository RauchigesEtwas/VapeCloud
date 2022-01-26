package de.vapecloud.vapenet.protocol;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/17:37
 * Created by Robin B. (RauchigesEtwas)
 */

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class PacketBuffer implements IPacketBuffer {

    private final Gson gson = new Gson();
    private final JsonObject object;

    public PacketBuffer(byte[] bytes) {
        this.object = gson.fromJson(new String(bytes), JsonObject.class);
    }

    public PacketBuffer() {
        this.object = new JsonObject();
    }

    @Override
    public byte[] array() {
        return toString().getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public void write(String key, Object value) {
        object.add(key, gson.toJsonTree(value));
    }

    @Override
    public <T> T read(String key, Class<T> clazz) {
        return gson.fromJson(object.get(key), clazz);
    }

    @Override
    public <T> List<T> readList(String key, Class<T> clazz) {
        return gson.fromJson(object.get(key), TypeToken.getParameterized(List.class, clazz).getType());
    }

    @Override
    public <T1, T2> Map<T1, T2> readMap(String key, Class<T1> clazz1, Class<T2> clazz2) {
        return gson.fromJson(object.get(key), TypeToken.getParameterized(Map.class, clazz1, clazz2).getType());
    }

    @Override
    public String toString() {
        return gson.toJson(object);
    }
}

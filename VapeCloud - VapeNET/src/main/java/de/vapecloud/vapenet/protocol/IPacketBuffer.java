package de.vapecloud.vapenet.protocol;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/17:37
 * Created by Robin B. (RauchigesEtwas)
 */

import java.util.List;
import java.util.Map;

public interface IPacketBuffer {


    byte[] array();

    void write(String key, Object value);

    <T> T read(String key, Class<T> clazz);

    <T> List<T> readList(String key, Class<T> clazz);

    <T1, T2> Map<T1, T2> readMap(String key, Class<T1> clazz1, Class<T2> clazz2);
}

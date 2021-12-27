package de.vapecloud.vapenet;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/17:36
 * Created by Robin B. (RauchigesEtwas)
 */

public class VapeNETUtil {

    public static byte[] nullChar(byte[] bytes) {
        byte[] result = new byte[bytes.length + 1];
        byte[] nullByte = new byte[1];
        nullByte[0] = -1;
        System.arraycopy(bytes, 0, result, 0, bytes.length);
        System.arraycopy(nullByte, 0, result, bytes.length, 1);
        return result;
    }
}

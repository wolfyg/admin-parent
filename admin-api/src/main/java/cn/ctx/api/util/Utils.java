package cn.ctx.api.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;

/**
 * 工具类
 * @author yg
 *
 */
public class Utils {

    public static Logger logger = LoggerFactory.getLogger(Utils.class);

    
    public static Logger getClass(Class clazz) {
    	return logger = LoggerFactory.getLogger(clazz);
    }
    
    /**
     * base64转字节数组，通过字节数组获取文件流
     * @param str
     * @return
     */
    public static byte[] transformBase64(String str) {
        BASE64Decoder decode = new BASE64Decoder();
        byte[] b = null;
        try {
            b = decode.decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    /** 对象序列化与反序列化 */
    public static byte[] serialize(Object object) {

        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e1) {
            return null;
        }
    }

    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}

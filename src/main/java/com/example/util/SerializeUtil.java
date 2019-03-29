package com.example.util;

import java.io.*;

public class SerializeUtil {

    /**
     * 对象序列化
     * @param obj 实现了Serializable接口的对象
     * @return 序列化字节数组
     */
    public static <T extends Serializable> byte[] seria(T obj) {
        ObjectOutputStream oos = null;
        try {
            // 序列化
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (oos!=null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 字节数组反序列化为指定类型的对象
     * @param bytes 字节数组
     * @param cls 实现了Serializable的类型
     * @return 指定类型的对象
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deseria(byte[] bytes, Class<T> cls) {
        return (T) deseria(bytes);
    }

    /**
     * 字节数组反序列化为Object
     * @param bytes 字节数组
     * @return Object对象
     */
    public static Object deseria(byte[] bytes) {
        ObjectInputStream ois = null;
        try {
            // 反序列化
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (ois!=null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}

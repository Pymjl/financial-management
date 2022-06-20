package cuit.pymjl.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/20 19:46
 **/
public class SerializeUtil {
    /**
     * string2相等字节
     * 统一编码，字符串获取byte数组
     * 编码格式：UTF-8
     *
     * @param s 字符串
     * @return {@code byte[]}
     */
    public static byte[] String2Byte(String s) {
        if (s == null) {
            return null;
        }
        return s.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 统一编码，byte数组转字符串
     * 编码格式：UTF-8
     *
     * @param bytes 字节
     * @return {@code String}
     */
    public static String ByteToString(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 序列化
     *
     * @param object
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static byte[] serializeObj(Object object) {
        if (object == null) {
            return null;
        }
        ByteArrayOutputStream byteOutStream = null;
        ObjectOutputStream outputStream = null;

        try {
            byteOutStream = new ByteArrayOutputStream();
            try {
                outputStream = new ObjectOutputStream(byteOutStream);
                outputStream.writeObject(object);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] bytes = byteOutStream.toByteArray();
            return bytes;
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            if (null != byteOutStream) {
                try {
                    byteOutStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Object unserializeObj(byte[] bytes) {
        ByteArrayInputStream byteInputStream = null;

        ObjectInputStream inputStream = null;

        try {
            byteInputStream = new ByteArrayInputStream(bytes);

            inputStream = new ObjectInputStream(byteInputStream);

            return inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (null != byteInputStream) {
                try {
                    byteInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    /**
     * key链表转byte二维数组
     */
    public static byte[][] serializeListString(List<String> list) {
        if (list == null) {
            return null;
        }

        byte[][] byteArray = new byte[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            byte[] bytefield = SerializeUtil.String2Byte(list.get(i));

            byteArray[i] = bytefield;
        }

        return byteArray;
    }

    /**
     * key链表转byte二维数组
     */
    public static byte[][] serializeList(List<?> list) {
        if (list == null) {
            return null;
        }

        byte[][] byteArray = new byte[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            byte[] bytefield = SerializeUtil.serializeObj(list.get(i));

            byteArray[i] = bytefield;
        }

        return byteArray;
    }

    /**
     * 将List<byte[]>反序列化为list<T> 集合
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> unserializeList(List<byte[]> bytes) {
        if (bytes == null) {
            return null;
        }

        List<T> list = new ArrayList<T>();

        for (byte[] ele : bytes) {
            T t = (T) SerializeUtil.unserializeObj(ele);

            list.add(t);
        }

        return list;
    }

    /**
     * Map<String,T>转 Map<byte[], byte[]> </br>
     * 其中第一个byte[]为String的字节码，第二个byte[]为T的序列化字节码
     */
    public static <T> Map<byte[], byte[]> serializeMap(Map<String, T> map) {
        if (map == null) {
            return null;
        }

        Map<byte[], byte[]> jedisMap = new HashMap<byte[], byte[]>();

        for (Map.Entry<String, T> entry : map.entrySet()) {
            jedisMap.put(SerializeUtil.String2Byte(entry.getKey()), SerializeUtil.serializeObj(entry.getValue()));
        }

        return jedisMap;
    }

    /**
     * Map<String,T>转 Map<byte[], byte[]> </br>
     * 其中第一个byte[]为String的字节码，第二个byte[]为T的序列化字节码
     */
    public static Map<byte[], Double> serializeDoubleMap(Map<?, Double> map) {
        if (map == null) {
            return null;
        }

        Map<byte[], Double> jedisMap = new HashMap<byte[], Double>();

        for (Map.Entry<?, Double> entry : map.entrySet()) {
            jedisMap.put(SerializeUtil.serializeObj(entry.getKey()), entry.getValue());
        }

        return jedisMap;
    }

    /**
     * 将字节map反序列化为Map
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> unserializeMap(Map<byte[], byte[]> map) {
        if (map == null) {
            return null;
        }

        Map<String, T> result = new HashMap<String, T>();

        for (Map.Entry<byte[], byte[]> entry : map.entrySet()) {
            result.put(SerializeUtil.ByteToString(entry.getKey()), (T) SerializeUtil.unserializeObj(entry.getValue()));
        }

        return result;
    }

    /**
     * 将List<byte[]>反序列化为Set<T> 集合
     */
    public static Set<String> byteSet2String(Set<byte[]> bytes) {
        if (bytes == null) {
            return null;
        }

        Set<String> set = new HashSet<String>();

        for (byte[] ele : bytes) {
            String t = SerializeUtil.ByteToString(ele);

            set.add(t);
        }

        return set;
    }

    /**
     * 将List<byte[]>反序列化为Set<T> 集合
     */
    @SuppressWarnings("unchecked")
    public static <T> Set<T> unserializeSet(Set<byte[]> bytes) {
        if (bytes == null) {
            return null;
        }

        Set<T> set = new HashSet<T>();

        for (byte[] ele : bytes) {
            T t = (T) SerializeUtil.unserializeObj(ele);

            set.add(t);
        }

        return set;
    }

    /**
     * 将List<byte[]>反序列化为Set<T> 集合
     */
    @SuppressWarnings("unchecked")
    public static <T> LinkedHashSet<T> unserializeSet2LinkedHashSet(Set<byte[]> bytes) {
        if (bytes == null) {
            return null;
        }

        LinkedHashSet<T> set = new LinkedHashSet<T>();

        for (byte[] ele : bytes) {
            T t = (T) SerializeUtil.unserializeObj(ele);

            set.add(t);
        }

        return set;
    }

    /**
     * 关闭io流对象
     *
     * @param closeable
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

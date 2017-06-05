package cn.edu.nju.p.utils;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * 序列化对象的工具类
 * 不使用java自带的序列化机制，使用ProtoStuff提高性能
 */
public class ProtoStuffSerializerUtil {

    /**
     * 序列化对象
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> byte[] serialize(T obj) {

        if (obj == null) {
            throw new RuntimeException("序列化对象为空！");
        }

        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(obj.getClass());
        LinkedBuffer linkedBuffer = LinkedBuffer.allocate(1204 * 1024);
        byte[] protoStuff = ProtostuffIOUtil.toByteArray(obj, schema, linkedBuffer);
        linkedBuffer.clear();
        return protoStuff;
    }

    /**
     * 反序列化对象
     * @param bytes
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T deSerialize(byte[] bytes, Class<T> targetClass) {

        if (bytes == null || bytes.length == 0) {
            throw new RuntimeException("反序列化时传递的byte数组为空！");
        }
        T instance;
        try {
            instance = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("反序列化时根据类型创建对象失败！", e);
        }

        Schema<T> schema = RuntimeSchema.getSchema(targetClass);
        ProtostuffIOUtil.mergeFrom(bytes, instance, schema);
        return instance;
    }
}

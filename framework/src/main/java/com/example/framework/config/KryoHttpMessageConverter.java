package com.example.framework.config;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;

import java.io.IOException;

/**
 * Description: 模块功能描述
 * <p>
 * User: luo0412
 * Date: 2020-08-28 14:35
 */
public class KryoHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    public static final MediaType KRYO = new MediaType("application", "x-kryo");

    private static final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        return kryo;
    });

    public KryoHttpMessageConverter() {
        super(KRYO);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return Object.class.isAssignableFrom(clazz);
    }

    @Override
    protected Object readInternal(
            Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException {
        Input input = new Input(inputMessage.getBody());
        return kryoThreadLocal.get().readClassAndObject(input);
    }

    @Override
    protected void writeInternal(
            Object object, HttpOutputMessage outputMessage) throws IOException {
        Output output = new Output(outputMessage.getBody());
        kryoThreadLocal.get().writeClassAndObject(output, object);
        output.flush();
    }

    @Override
    protected MediaType getDefaultContentType(Object object) {
        return KRYO;
    }
}

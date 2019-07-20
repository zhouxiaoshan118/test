package com.zte.zudp.modules.sys.utils.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import com.zte.zudp.common.enums.IntValueEnum;

/**
 * 通用的枚举转换类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-07-14.
 */
public class EnumConverterFactory implements ConverterFactory<String, IntValueEnum> {

    @Override
    public <T extends IntValueEnum> Converter<String, T> getConverter(Class<T> targetType) {
        if (!targetType.isEnum()) {
            throw new UnsupportedOperationException("only support convert for enum!");
        }
        return new StringToEnum<>(targetType);
    }

    private class StringToEnum<T extends IntValueEnum> implements Converter<String, T> {

        private final Class<T> enumType;

        public StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        public T convert(String source) {
            if (source.length() == 0) {
                // It's an empty enum identifier: reset the enum value to null.
                return null;
            }
            try {
                Integer v = Integer.valueOf(source);
                T[] all = enumType.getEnumConstants();
                for (T t : all) {
                    if (t.getValue() == v) {
                        return t;
                    }
                }
                return null;
            } catch (NumberFormatException e) {
                T[] all = enumType.getEnumConstants();
                for (T t : all) {
                    if (source.equals(t.getName())) return t;
                }
                return null;
            }
        }
    }
}

package org.apache.ibatis.type.custom;

import org.apache.ibatis.type.custom.ByteEnumTypeHandler;

/**
 * 实现此接口的枚举类，使用自定义的TypeHandler{@link ByteEnumTypeHandler}
 *
 * @author pujian
 * @date 2023/1/6 18:02
 */
public interface ByteEnum {

    /**
     * 获取枚举内部值
     *
     * @return Byte
     * @author pujian
     * @date 2023/1/6 18:14
     */
    Byte getCode();

}

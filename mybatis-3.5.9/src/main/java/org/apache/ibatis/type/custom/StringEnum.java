package org.apache.ibatis.type.custom;

/**
 * 映射String类型的枚举接口
 * @see org.apache.ibatis.type.custom.StringEnumTypeHandler
 *
 * @author pujian
 * @date 2022/7/5 14:06
 */
public interface StringEnum {

    /**
     * 获取枚举要映射的值
     *
     * @author pujian
     * @date 2022/7/5 16:20
     * @return java.lang.String
     */
    String getValue();

}

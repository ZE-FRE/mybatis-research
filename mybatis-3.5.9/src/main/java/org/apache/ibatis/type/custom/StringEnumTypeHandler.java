package org.apache.ibatis.type.custom;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 处理String类型枚举的TypeHandler
 *
 * mybatis默认提供的用于处理枚举类型的TypeHandler有两个，分别是：
 * 1、默认枚举类型处理器{@link org.apache.ibatis.type.EnumTypeHandler}，如果一个枚举类型未指定TypeHandler，则默认为它，它映射的是枚举的{@link Enum#name()}
 * 2、映射枚举下标索引的{@link org.apache.ibatis.type.EnumOrdinalTypeHandler}，即映射的是{@link Enum#ordinal()}
 *
 * 但是，有时候有这样的需求：实体中枚举类型的字段在数据库却希望存类似1、2、3、4等等这种数据，所以需要自定义TypeHandler来实现
 * 此时，如果一个枚举实现了{@link StringEnum}接口，则它的TypeHandler就是{@link StringEnumTypeHandler}，
 * 而不再是默认的{@link org.apache.ibatis.type.EnumTypeHandler}
 *
 * @author pujian
 * @param <E> 枚举类型
 * @date 2022/7/5 14:09
 */
@MappedTypes(StringEnum.class)
@MappedJdbcTypes({JdbcType.CHAR, JdbcType.VARCHAR})
public class StringEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    /**
     * 枚举的Class，{@link StringEnum}及其子类
     */
    private final Class<E> enumType;

    /**
     * 构造方法，由mybatis在扫描注册TypeHandler时反射调用
     *
     * @param enumType javaType
     * @author pujian
     * @date 2022/7/5 14:42
     */
    public StringEnumTypeHandler(Class<E> enumType) {
        if (!StringEnum.class.isAssignableFrom(enumType)) {
            throw new IllegalArgumentException("映射String类型的枚举请实现" + StringEnum.class.getName() + "接口");
        }
        this.enumType = enumType;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E e, JdbcType jdbcType) throws SQLException {
        StringEnum stringEnum = (StringEnum) e;
        ps.setString(i, stringEnum.getValue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return toEnum(value);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return toEnum(value);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return toEnum(value);
    }

    /**
     * 得到枚举值
     *
     * @param value 枚举内部字段的值
     * @author pujian
     * @date 2022/7/5 16:28
     * @return E
     */
    private E toEnum(String value) {
        if (value == null) {
            return null;
        }
        // 取得该枚举类中定义的所有实例
        E[] enums = enumType.getEnumConstants();
        for (E enumeration : enums) {
            StringEnum stringEnum = (StringEnum) enumeration;
            if (value.equals(stringEnum.getValue())) {
                return enumeration;
            }
        }
        return null;
    }

}

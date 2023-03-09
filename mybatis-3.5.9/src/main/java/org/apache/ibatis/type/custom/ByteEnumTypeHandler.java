package org.apache.ibatis.type.custom;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义枚举类TypeHandler
 * @see org.apache.ibatis.type.TypeHandler
 *
 * @author pujian
 * @date 2023/1/6 17:48
 */
@MappedTypes(ByteEnum.class)
public class ByteEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    /**
     * 枚举的Class，{@link ByteEnum}及其子类
     */
    private final Class<E> enumType;

    public ByteEnumTypeHandler(Class<E> enumType) {
        this.enumType = enumType;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
        ByteEnum ByteEnum = (ByteEnum) e;
        preparedStatement.setByte(i, ByteEnum.getCode());
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        byte code = resultSet.getByte(columnName);
        return code == 0 && resultSet.wasNull() ? null : valueOf(code);
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        byte code = resultSet.getByte(columnIndex);
        return code == 0 && resultSet.wasNull() ? null : valueOf(code);
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        byte code = callableStatement.getByte(columnIndex);
        return code == 0 && callableStatement.wasNull() ? null : valueOf(code);
    }


    /**
     * 得到枚举值
     *
     * @param code 枚举内部字段的值
     * @return E
     * @author pujian
     * @date 2023/1/7 10:08
     */
    private E valueOf(byte code) {
        // 取得该枚举类中定义的所有实例
        E[] enums = enumType.getEnumConstants();
        for (E enumInstance : enums) {
            ByteEnum ByteEnum = (ByteEnum) enumInstance;
            if (code == ByteEnum.getCode()) {
                return enumInstance;
            }
        }
        return null;
    }

}

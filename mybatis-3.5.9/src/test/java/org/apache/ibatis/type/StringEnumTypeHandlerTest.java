package org.apache.ibatis.type;

import org.apache.ibatis.type.custom.StringEnum;
import org.apache.ibatis.type.custom.StringEnumTypeHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author pujian
 * @date 2022/7/6 15:58
 */
public class StringEnumTypeHandlerTest extends BaseTypeHandlerTest {

  TypeHandler<Weekend> stringEnumTypeHandler;

  {
    TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();
    // 注册TypeHandler
    typeHandlerRegistry.register(StringEnumTypeHandler.class);
    // 获取枚举类Weekend对应的TypeHandler，即StringEnumTypeHandler
    stringEnumTypeHandler = typeHandlerRegistry.getTypeHandler(Weekend.class);
  }

  enum Weekend implements StringEnum {
    SATURDAY("周六"),
    SUNDAY("周天");

    private final String description;

    Weekend(String description) {
      this.description = description;
    }

    @Override
    public String getValue() {
      return description;
    }
  }

  @Test
  @Override
  public void shouldSetParameter() throws Exception {
    stringEnumTypeHandler.setParameter(ps, 1, Weekend.SATURDAY, JdbcType.CHAR);
    verify(ps).setString(1, "周六");
  }

  @Test
  public void shouldSetNullParameter() throws Exception {
    stringEnumTypeHandler.setParameter(ps, 1, null, JdbcType.CHAR);
    verify(ps).setNull(1, JdbcType.CHAR.TYPE_CODE);
  }

  @Test
  @Override
  public void shouldGetResultFromResultSetByName() throws Exception {
    when(rs.getString("column")).thenReturn("周天");
    assertEquals(Weekend.SUNDAY, stringEnumTypeHandler.getResult(rs, "column"));
  }

  @Test
  @Override
  public void shouldGetResultNullFromResultSetByName() throws Exception {
    when(rs.getString("column")).thenReturn(null);
    assertNull(stringEnumTypeHandler.getResult(rs, "column"));
  }

  @Test
  public void shouldGetResultNullFromResultSetByNameWhenUnknownEnum() throws Exception {
    when(rs.getString("column")).thenReturn("周一");
    assertNull(stringEnumTypeHandler.getResult(rs, "column"));
  }

  @Test
  @Override
  public void shouldGetResultFromResultSetByPosition() throws Exception {
    when(rs.getString(1)).thenReturn("周天");
    assertEquals(Weekend.SUNDAY, stringEnumTypeHandler.getResult(rs, 1));
  }

  @Test
  @Override
  public void shouldGetResultNullFromResultSetByPosition() throws Exception {
    when(rs.getString(1)).thenReturn(null);
    assertNull(stringEnumTypeHandler.getResult(rs, 1));
  }

  @Test
  @Override
  public void shouldGetResultFromCallableStatement() throws Exception {
    when(cs.getString(1)).thenReturn("周天");
    assertEquals(Weekend.SUNDAY, stringEnumTypeHandler.getResult(cs, 1));
  }

  @Test
  @Override
  public void shouldGetResultNullFromCallableStatement() throws Exception {
    when(cs.getString(1)).thenReturn(null);
    assertNull(stringEnumTypeHandler.getResult(cs, 1));
  }
}

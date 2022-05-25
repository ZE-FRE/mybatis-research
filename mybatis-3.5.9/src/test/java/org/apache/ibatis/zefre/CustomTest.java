package org.apache.ibatis.zefre;

import org.apache.ibatis.BaseDataTest;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.zefre.entity.User;
import org.apache.ibatis.zefre.mapper.UserMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * @author pujian
 * @date 2022/5/25 10:32
 */
public class CustomTest {

  @Test
  public void testDemo() throws Exception {
    String resource = "org/apache/ibatis/zefre/resources/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

//    BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
//      "org/apache/ibatis/zefre/resources/init.sql");

    SqlSession sqlSession = sqlSessionFactory.openSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    User user = userMapper.getById(1);
    System.out.println(user);
    user = userMapper.getByUsername("张三");
    System.out.println(user);
  }

}

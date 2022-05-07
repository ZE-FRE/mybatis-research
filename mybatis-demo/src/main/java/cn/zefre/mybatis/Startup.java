
package cn.zefre.mybatis;

import cn.zefre.mybatis.entity.User;
import cn.zefre.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author pujian
 * @date 2022/4/26 13:19
 */
public class Startup {

  public static void main(String[] args) throws IOException {

    InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = sqlSessionFactory.openSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    User user = userMapper.getById(1);
    System.out.println(user);
    user = userMapper.getByUsername("张三");
    System.out.println(user);

  }
}

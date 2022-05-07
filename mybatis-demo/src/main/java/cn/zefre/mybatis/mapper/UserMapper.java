
package cn.zefre.mybatis.mapper;

import cn.zefre.mybatis.entity.User;
import org.apache.ibatis.annotations.Select;


/**
 * @author pujian
 * @date 2022/4/26 15:31
 */
public interface UserMapper {

    @Select("select * from t_user where id = #{id}")
    User getById(Integer id);

    User getByUsername(String username);

}

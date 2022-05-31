
package org.apache.ibatis.zefre.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.zefre.entity.User;


/**
 * @author zefre
 * @date 2022/4/26 15:31
 */
public interface UserMapper {

    @Select("select * from t_user where id = #{id}")
    User getById(Integer id);

    User getByUsername(String username);

}

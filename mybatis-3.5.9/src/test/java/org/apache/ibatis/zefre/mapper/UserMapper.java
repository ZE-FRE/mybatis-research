/*
 *    Copyright 2009-2022 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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

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
package org.apache.ibatis.plugin;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class PluginTest {

  @Test
  void mapPluginShouldInterceptGet() {
    Map map = new HashMap();
    map = (Map) new AlwaysMapPlugin().plugin(map);
    assertEquals("Always", map.get("Anything"));
  }

  @Test
  void shouldNotInterceptToString() {
    Map map = new HashMap();
    map = (Map) new AlwaysMapPlugin().plugin(map);
    assertNotEquals("Always", map.toString());
  }

  @Intercepts({
      @Signature(type = Map.class, method = "get", args = {Object.class})})
  public static class AlwaysMapPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) {
      return "Always";
    }

  }




  /**
   * 插件嵌套
   *
   * @author zefre
   * @date 2022/5/11 13:43
   */
  @Intercepts({@Signature(type = Map.class, method = "get", args = {Object.class})})
  public static class AlwaysMapPlusPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
      return invocation.proceed() + "-plus";
    }

  }
  /**
   * 两个插件都拦截执行
   *
   * @author zefre
   * @date 2022/5/11 13:44
   */
  @Test
  void mapPluginShouldInterceptGetTwice() {
    Map map = new HashMap();
    map = (Map) new AlwaysMapPlugin().plugin(map);
    map = (Map) new AlwaysMapPlusPlugin().plugin(map);
    assertEquals("Always-plus", map.get("Anything"));
  }


}

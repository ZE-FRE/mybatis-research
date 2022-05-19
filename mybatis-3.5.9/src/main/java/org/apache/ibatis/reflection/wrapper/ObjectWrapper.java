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
package org.apache.ibatis.reflection.wrapper;

import java.util.List;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;

/**
 * 包装对象的Wrapper
 *
 * @author Clinton Begin
 */
public interface ObjectWrapper {

  /**
   * 获取对象某个属性的值
   *
   * @param prop 属性描述
   * @date 2022/5/18 16:52
   */
  Object get(PropertyTokenizer prop);

  /**
   * 设置对象某个属性的值
   *
   * @param prop 属性描述
   * @param value 设置的值
   * @author pujian
   * @date 2022/5/18 16:53
   */
  void set(PropertyTokenizer prop, Object value);

  String findProperty(String name, boolean useCamelCaseMapping);

  String[] getGetterNames();

  String[] getSetterNames();

  Class<?> getSetterType(String name);

  Class<?> getGetterType(String name);

  boolean hasSetter(String name);

  boolean hasGetter(String name);

  MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory);

  /**
   * 被包装的对象是否是Collection集合
   *
   * @date 2022/5/18 16:54
   * @return boolean
   */
  boolean isCollection();

  void add(Object element);

  <E> void addAll(List<E> element);

}

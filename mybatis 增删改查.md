# mybatis增删改查

\#输出日志到控制台，配置文件application.properties
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl

## 删除

* 在接口定义删除方法，使用注解运行sql语句，方法返回值为int，表示删除的数据数量，以下方法test运行会预编译 : delete from worker_info where id = ?。

![image-20230612163352632](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612163352632.png)

* 再到项目test测试目录下的springboottest编写测试方法

  ![image-20230612163659360](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612163659360.png)

## 新增

* 编写接口方法，传入参数是User类的实例对象

  ![image-20230612170958353](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612170958353.png)

* 编写测试方法

![image-20230612171025694](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612171025694.png)

![image-20230612170939611](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612170939611.png)

* 实例化对象中不需要设置id值，id是自增的，所以也不能直接用get方法获得id。但是可以用注解Options获得。

  useGeneratedKeys = true 表示要获取返回回来的主键值，keyProperty = "id"表示返回的值要传给对象user的id属性

  ```
      @Options(useGeneratedKeys = true, keyProperty = "id")
      @Insert("insert into worker_info(name, age, address, gender, id_card, entry_date)" +
              "values (#{name}, #{age}, #{address}, #{gender} , #{idCard}, #{entryDate})")
      public void insert(User user);
  ```

  

  ![image-20230612180536810](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612180536810.png)

## 更新

* 编写接口方法

  ```
      //根据id更新员工数据
      @Update("update worker_info set name = #{name} , age = #{age} , address = #{address}, " +
              "gender = #{gender}, id_card = #{idCard}, entry_date = #{entryDate} where id = #{id};")
      public void update(User user, Integer id);
  ```

  

  ![image-20230612182123002](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612182123002.png)

* 编写测试方法

  ```
      @Test
      public void testUpdate(){
          User user = new User();
          user.setName("基尼态梅");
          user.setAge((short) 22);
          user.setAddress("开心农场");
          user.setGender((short) 1);
          user.setIdCard("123456789087654321");
          user.setEntryDate(LocalDateTime.now());
  
          userMapper.update(user, 13);
      }
  ```

  

  ![image-20230612182148625](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612182148625.png)

## 查询

* 编写接口方法

  ```
      @Select("select * from worker_info where id = #{id};")
      public User selectById(Integer id);
  ```

  

  ![image-20230612182749398](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612182749398.png)

* 测试方法

  ```
      @Test
      public void testSelectById(){
          User user = userMapper.selectById(13);
          System.out.println(user);
      }
  ```

  

  ![image-20230612182806220](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612182806220.png)

但是运行结果

```
User(id=13, name=基尼态梅, age=22, address=开心农场, gender=1, idCard=null, entryDate=null)
```

发现idCard、entryDate 都为null。这是因为User类的属性名与数据库表的列名不一致。解决这类问题有以下几种方法：

* 方法一：在Select注解中的sql语句起别名，让别名与实体类属性名一致

  ```
  @Select("select id, name, age, address, gender, id_card idCard, entry_date entryDate from worker_info where id = #{id};")
  public User selectById(Integer id);
  ```

  ![image-20230612183530061](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612183530061.png)

* 方法二：通过@Results 注解 手动映射封装

  @Result中的参数 column 值是数据库表列名，property 值是实体类属性名

  ```
      @Results({
          @Result(column = "id_card", property = "idCard"),
          @Result(column = "entry_date", property = "entryDate"),
      })
      @Select("select * from worker_info where id = #{id};")
      public User selectById(Integer id);
  ```

  

  ![image-20230612183930789](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612183930789.png)

* 方法三：开启mybatis的驼峰命名自动映射开关---a_cloumn  ---->  aCloumn

  在application.properties配置文件中配置开启

  ```
  #驼峰命名的开关
  mybatis.configuration.map-underscore-to-camel-case=true
  ```

  

  ![image-20230612184313994](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612184313994.png)

## 条件查询

* 编写接口方法

  名字模糊匹配，为"%`name`%"不能直接用#{}，但是可以用${}进行拼接，但是存在sql注入问题

  ```
      // 根据条件查询
      @Select("select * from worker_info where name like '%${name}%' and" +
              " gender = #{gender} and age >= #{minAge};")
      public List<User> listIf(String name, short gender, short minAge);
  ```

![image-20230612190449539](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612190449539.png)

* 编写测试方法

  ```
      @Test
      public void testListIf(){
          List<User> userList = userMapper.listIf("罗", (short) 1, (short) 20);
          System.out.println(userList);
      }
  ```

  ![image-20230612190505833](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612190505833.png)

* 可以通过mysql中的concat函数解决这种情况下不能使用#的问题`concat('%',#{name},'%')`。

  ```
      @Select("select * from worker_info where name like concat('%', #{name}, '%') and" +
              " gender = #{gender} and age >= #{minAge};")
      public List<User> listIf(String name, short gender, short minAge);
  ```

  ![image-20230612191259999](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612191259999.png)
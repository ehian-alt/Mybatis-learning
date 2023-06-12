# mybatis 准备

* 创建springboot工程、数据库表、以及其实体类

  创建springboot工程

![image-20230612145913860](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612145913860.png)

​		引入mybatis framework 和 msql / Oracle driver

![image-20230612150400773](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612150400773.png)

​		创建一个数据库表，并根据该表字段创建一个实体类

![image-20230612150646142](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612150646142.png)

* 引入mybatis相关依赖、配置mybatis（数据库连接信息）

![image-20230612151020832](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612151020832.png)

* 编写一个接口测试

  调用时运行的是注解的sql语句，并把查询结果封装到泛型list内

  ![image-20230612151353558](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612151353558.png)

![image-20230612151542720](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612151542720.png)



* 可以通过以下方法切换数据库连接池![image-20230612152144293](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612152144293.png)

![image-20230612152305911](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612152305911.png)

## lombok工具

Lombok是一个实用的Java类库，能通过注解的形式自动生成构造器、getter/setter、equals、hashcode、toString等方法，并可以自动化生成日志变量，简化java开发、提高效率。

* Lombok常用的注解

![image-20230612152414885](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612152414885.png)

* pom.xml  配置依赖

  ![image-20230612152602211](C:\Users\L\AppData\Roaming\Typora\typora-user-images\image-20230612152602211.png)


package com.mybatis.mapper;

import com.mybatis.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    //查询所有数据信息
    @Select("select * from worker_info")
    public List<User> list();

    //根据id删除员工
    @Delete("delete from worker_info where id = #{id}")
    public int delete(Integer id);

    //新增员工
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into worker_info(name, age, address, gender, id_card, entry_date)" +
            "values (#{name}, #{age}, #{address}, #{gender} , #{idCard}, #{entryDate})")
    public void insert(User user);

    //根据id更新员工数据
    @Update("update worker_info set name = #{name} , age = #{age} , address = #{address}, " +
            "gender = #{gender}, id_card = #{idCard}, entry_date = #{entryDate} where id = #{id};")
    public void update(User user, Integer id);

    //根据id查询员工信息
//    @Select("select * from worker_info where id = #{id};")
//    public User selectById(Integer id);

    // 方法一：在Select注解中的sql语句起别名，让别名与实体类属性名一致
//    @Select("select id, name, age, address, gender, id_card idCard, " +
//            "entry_date entryDate from worker_info where id = #{id};")
//    public User selectById(Integer id);

    // 方法二：通过@Results 注解 手动映射封装
//    @Results({
//            @Result(column = "id_card", property = "idCard"),
//            @Result(column = "entry_date", property = "entryDate"),
//    })
//    @Select("select * from worker_info where id = #{id};")
//    public User selectById(Integer id);
    //方法三：开启mybatis的驼峰命名
    @Select("select * from worker_info where id = #{id};")
    public User selectById(Integer id);

    // 根据条件查询
//    @Select("select * from worker_info where name like '%${name}%' and" +
//            " gender = #{gender} and age >= #{minAge};")
//    public List<User> listIf(String name, short gender, short minAge);
    // 使用concat函数
    @Select("select * from worker_info where name like concat('%', #{name}, '%') and" +
            " gender = #{gender} and age >= #{minAge};")
    public List<User> listIf(String name, short gender, short minAge);

    // 测试xml映射
    public List<User> listXml(String name, short gender, short minAge);

    // 测试动态sql--if
    public List<User> listActive(String name, Short gender, Short age);

    // 动态删除数据
    public void deleteById(List<Integer> ids);
}

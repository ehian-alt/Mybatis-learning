package com.mybatis;

import com.mybatis.mapper.UserMapper;
import com.mybatis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
class MvnMybatisApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //查询表所有数据
    @Test
    public void testListUser(){
        List<User> userList = userMapper.list();
        userList.stream().forEach(user -> {
            System.out.println(user);
        });
    }

    // 根据id删除数据
    @Test
    public void testDelete(){
        int deleted = userMapper.delete(11);
        System.out.println(deleted);
    }

    // 新增数据
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("小菲菲");
        user.setAge((short) 12);
        user.setAddress("猪圈");
        user.setGender((short) 0);
        user.setIdCard("123456789087654321");
        user.setEntryDate(LocalDateTime.now());

        userMapper.insert(user);
    }

    //根据id更新数据
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

    //根据id查询数据
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(13);
        System.out.println(user);
    }

    // 条件查询
    @Test
    public void testListIf(){
        List<User> userList = userMapper.listIf("罗", (short) 1, (short) 20);
        System.out.println(userList);
    }

    // XML映射测试方法，条件查询
    @Test
    public void testListXml(){
        // listXml(String name, short gender, short minAge)
        List<User> userList = userMapper.listXml("罗", (short) 1, (short) 20);
        System.out.println(userList);
    }

    // 动态sql--where if 查询测试方法，条件查询 listXml(String name, short gender, short minAge)
    @Test
    public void testActiveXml() {
        List<User> userList = userMapper.listActive(null, (short) 1, null);
        System.out.println(userList);
    }

    // 动态删除
    @Test
    public void testDeleteActive(){
        List<Integer> ids = Arrays.asList(1,3,5);
        userMapper.deleteById(ids);
    }
}

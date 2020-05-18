package com.szh.test;

import com.szh.dao.RoleDao;
import com.szh.dao.UserDao;
import com.szh.domain.Role;
import com.szh.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToMany {

    @Autowired
    RoleDao roleDao;

    @Autowired
    UserDao userDao;

    @Test//测试保存
    @Transactional  //开启事务
    @Rollback(false)//设置为不回滚
    public void save(){
        User user=new User();
        user.setUserName("张三");
        user.setUserPassword("123456");

        Role role=new Role();
        role.setRoleName("程序员");

        user.getRoles().add(role);
        role.getUsers().add(user);

        userDao.save(user);
        roleDao.save(role);
    }

    @Test//测试级联保存
    @Transactional  //开启事务
    @Rollback(false)//设置为不回滚
    public void save2(){
        User user=new User();
        user.setUserName("张三");
        user.setUserPassword("123456");

        Role role=new Role();
        role.setRoleName("程序员");

        user.getRoles().add(role);

        userDao.save(user);
    }
}

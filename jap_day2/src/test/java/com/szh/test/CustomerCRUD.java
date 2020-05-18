package com.szh.test;

import com.szh.dao.CustomerDao;
import com.szh.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerCRUD {

    @Autowired
    private CustomerDao customerDao;

    @Test//查询(立即查询)
    public void testFindOne(){
        Customer customer = customerDao.findOne(1);
        System.out.println(customer);
    }

    @Test//保存
    public void testSave(){
        Customer customer=new Customer();
        customer.setCustId(7);
        customer.setCustName("王五2");
        customer.setCustIndustry("学生");
        Customer customer1 = customerDao.save(customer);
        System.out.println(customer1);
    }

    @Test//修改
    public void testUpdate(){
        Customer customer=new Customer();
        customer.setCustId(7);
        customer.setCustName("王五2");
        customer.setCustIndustry("教育");
        Customer customer1 = customerDao.save(customer);
        System.out.println(customer1);
    }

    @Test//删除
    public void testDelete(){
        customerDao.delete(6);
    }

    @Test//查询所有
    public void testFindAll(){
        List<Customer> list = customerDao.findAll();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    @Test//统计
    public void testCount(){
        long count = customerDao.count();
        System.out.println(count);
    }

    @Test//判断id是否存在
    public void testExists(){
        boolean exists = customerDao.exists(1);
        System.out.println(exists);
    }

    @Test//判断id是否存在
    @Transactional//保证getOne正常执行(延迟加载)
    public void testGetOne(){
        Customer one = customerDao.getOne(1);
        System.out.println(one);
    }
}

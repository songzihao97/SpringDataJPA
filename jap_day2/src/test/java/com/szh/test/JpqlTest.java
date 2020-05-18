package com.szh.test;

import com.szh.dao.CustomerDao;
import com.szh.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {

    @Autowired
    private CustomerDao customerDao;

    @Test//根据用户名查询
    public void testFindByName(){
        Customer customer = customerDao.findByName("张三1");
        System.out.println(customer);
    }

    @Test//根据用户名和id查询
    public void testFindByIdAndName(){
        Customer customer = customerDao.findByIdAndName(1, "张三1");
        System.out.println(customer);
    }

    @Test//更新
    @Transactional//修改和删除方法使用需要增加事务支持（默认执行后自动回滚）
    @Rollback(value = false)//关闭自动回滚
    public void testUpdate(){
        customerDao.updateCustomer(4,"张三");
    }

    @Test//测试sql查询所有
    public void testFindBySql(){
        List<Customer> all = customerDao.findAllBySql();
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }

    @Test//测试sql模糊查询
    public void testFindByNameSql(){
        List<Customer> all = customerDao.findByNameSql("张三%");
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }

    @Test//根据命名规则方法利用用户名查询
    public void testFindByCustName(){
        Customer customer= customerDao.findByCustName("张三");
        System.out.println(customer);
    }
    
    @Test//根据命名规则方法利用用户名模糊查询
    public void testFindByCustNameLike(){
        List<Customer> list = customerDao.findByCustNameLike("张三%");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }


    @Test//根据命名规则方法多条件查询
    public void testFindByCustNameLikeAnd(){
        List<Customer> list = customerDao.findByCustNameLikeAndAndCustIndustry("张三%","it");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
}

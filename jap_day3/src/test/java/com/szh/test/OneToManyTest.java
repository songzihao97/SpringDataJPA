package com.szh.test;

import com.szh.dao.CustomerDao;
import com.szh.dao.LinkManDao;
import com.szh.domain.Customer;
import com.szh.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    LinkManDao linkManDao;

    @Test//测试保存操作
    @Transactional
    @Rollback(false)
    public void testSave(){
        Customer customer=new Customer();
        customer.setCustName("One");
        customer.setCustLevel("LV1");
        customer.setCustIndustry("教育");

        LinkMan linkMan=new LinkMan();
        linkMan.setLkmName("Many");
        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    @Test//测试级联保存
    @Transactional
    @Rollback(false)
    public void testCascadeSave(){
        Customer customer=new Customer();
        customer.setCustName("小王");
        customer.setCustLevel("LV1");
        customer.setCustIndustry("教育");

        LinkMan linkMan=new LinkMan();
        linkMan.setLkmName("小李");
        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
    }

    @Test//测试级联删除操作
    @Transactional
    @Rollback(false)
    public void testDel(){
        customerDao.delete(7);
    }
}

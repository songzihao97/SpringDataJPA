package com.szh.test;

import com.szh.dao.CustomerDao;
import com.szh.dao.LinkManDao;
import com.szh.domain.Customer;
import com.szh.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQueryTest {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    LinkManDao linkManDao;

    @Test//测试导航查询（多表查询）
    @Transactional
    public void testQuery1(){
        Customer customer= customerDao.getOne(1);
        System.out.println(customer);
        System.out.println("----------------------");
        Set<LinkMan> linkMans = customer.getLinkMans();
        for (LinkMan linkMan : linkMans) {
            System.out.println(linkMan);
        }
    }

    @Test//测试导航查询（多表查询）
    @Transactional
    public void testQuery2(){
        LinkMan linkMan = linkManDao.getOne(1);
        Customer customer = linkMan.getCustomer();
        System.out.println(customer);
    }
}

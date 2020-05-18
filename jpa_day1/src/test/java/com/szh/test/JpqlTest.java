package com.szh.test;

import com.szh.domain.Customer;
import com.szh.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class JpqlTest {

    //利用jpql查询所有
    @Test
    public void testFindAll(){
        //利用工具类获取EntityManager
        EntityManager em = JpaUtils.getEntityManager();
        //获取事务对象开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //获取query完成数据库操作
        String JPQL="from Customer";
        Query query = em.createQuery(JPQL);
        List cList = query.getResultList();
        for (Object o : cList) {
            System.out.println(o);
        }
        //提交事务
        tx.commit();
        //释放资源
        em.close();
        //factory.close();
    }

    //分页查询
    @Test
    public void testFindPaged(){
        //利用工具类获取EntityManager
        EntityManager em = JpaUtils.getEntityManager();
        //获取事务对象开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //获取query完成数据库操作
        String JPQL="from Customer";
        Query query = em.createQuery(JPQL);
        query.setFirstResult(1);
        query.setMaxResults(2);
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        //提交事务
        tx.commit();
        //释放资源
        em.close();
        //factory.close();
    }

    //条件查询
    @Test
    public void testFindCondition(){
        //利用工具类获取EntityManager
        EntityManager em = JpaUtils.getEntityManager();
        //获取事务对象开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //获取query完成数据库操作
        String JPQL="from Customer where custName like ?";
        Query query = em.createQuery(JPQL);
       query.setParameter(1,"张%");
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        //提交事务
        tx.commit();
        //释放资源
        em.close();
        //factory.close();
    }

    //统计查询
    @Test
    public void testFindCount(){
        //利用工具类获取EntityManager
        EntityManager em = JpaUtils.getEntityManager();
        //获取事务对象开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //获取query完成数据库操作
        String JPQL="select count(custId) from Customer";
        Query query = em.createQuery(JPQL);
        Object count = query.getSingleResult();
        System.out.println(count);
        //提交事务
        tx.commit();
        //释放资源
        em.close();
        //factory.close();
    }
}

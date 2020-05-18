package com.szh.test;

import com.szh.domain.Customer;
import com.szh.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {
    /**
     * 测试jpa保存
     *      案例：保存一个客户到数据库中
     * Jpa操作步骤
     *      创建实体管理类工厂，借助Persistence的静态方法获取
     * 		其中传递的参数为持久化单元名称，需要jpa配置文件中指定
     */
    @Test
    public void testSave(){
        /*//加载配置文件创建工厂
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //通过实体类管理工厂获取实体管理器
        EntityManager em = factory.createEntityManager();*/
        EntityManager em = JpaUtils.getEntityManager();
        //获取事务对象开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //完成数据库操作
        Customer customer=new Customer();
        customer.setCustName("张三");
        customer.setCustIndustry("it");
        em.persist(customer);//保存
        //提交事务
        tx.commit();
        //释放资源
        em.close();
        //factory.close();
    }

    //根据id查询用户
    //find执行就查询
    @Test
    public void testFind(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        Customer customer = manager.find(Customer.class, 1);
        System.out.println(customer);
        tx.commit();
        manager.close();
    }

    //Reference(参考)什么时候用到对象什么时候查询
    //懒加载
    @Test
    public void testReference(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        Customer customer = manager.getReference(Customer.class, 1);
        System.out.println(customer);
        tx.commit();
        manager.close();
    }

    //根据id删除用户信息
    @Test
    public void testRemove(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        Customer customer = manager.getReference(Customer.class, 3);
        manager.remove(customer);
        tx.commit();
        manager.close();
    }

    //修改用户信息
    @Test
    public void testUpdate(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        Customer customer = manager.getReference(Customer.class, 2);
        customer.setCustLevel("1");
        manager.merge(customer);
        tx.commit();
        manager.close();
    }

}

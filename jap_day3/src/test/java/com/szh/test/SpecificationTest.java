package com.szh.test;

import com.szh.dao.CustomerDao;
import com.szh.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecificationTest {

    @Autowired
    CustomerDao customerDao;

    @Test//利用客户名查询
    public void testSpecifications(){
        //创建实现Specification的匿名内部类
        Specification<Customer> spec=new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                return criteriaBuilder.equal(custName,"张三");
            }
        };
        Customer cu = customerDao.findOne(spec);
        System.out.println(cu);
    }

    @Test//根据用户名模糊和行业精准进行查询
    public void testSpec1(){
        //创建实现Specification的匿名内部类
        Specification<Customer> spec=new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                Predicate p1 = criteriaBuilder.like(custName.as(String.class), "张三%");
                Predicate p2 = criteriaBuilder.equal(custIndustry, "it");
                Predicate and = criteriaBuilder.and(p1, p2);
                return and;
            }
        };
        List<Customer> list = customerDao.findAll(spec);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    @Test//排序查询
    public void testSpec2(){
        //创建实现Specification的匿名内部类
        Specification<Customer> spec=new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                Predicate p1 = criteriaBuilder.like(custName.as(String.class), "张三%");
                Predicate p2 = criteriaBuilder.equal(custIndustry, "it");
                Predicate and = criteriaBuilder.and(p1, p2);
                return and;
            }
        };
        //创建排序对象
        Sort sort=new Sort(Sort.Direction.DESC,"custId");
        List<Customer> customers = customerDao.findAll(spec, sort);
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Test//分页查询
    public void testSpec3(){
        Pageable pageable=new PageRequest(2,2);
        Page<Customer> customers= customerDao.findAll(pageable);
        System.out.println(customers.getTotalElements());//得到总条数
        System.out.println(customers.getTotalPages());//得到总页数
        System.out.println(customers.getContent());//得到数据集合列表
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}

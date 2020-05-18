package com.szh.dao;

import com.szh.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Query nativeQuery:false(使用jpql查询)|true(使用sql语句查询)
 */
public interface CustomerDao extends JpaRepository<Customer,Integer>, JpaSpecificationExecutor<Customer> {

    //根据客户名称查询（使用jpql）
    @Query(value = "from Customer where custName= ?1")
    public Customer findByName(String custName);

    //根据客户名和id查询
    //默认参数位置对应占位符位置
    //也可以利用索引指定
    @Query(value = "from Customer where custId=?1 and custName= ?2")
    public Customer findByIdAndName(Integer custId,String custName);

    /**
     * 修改用户名
     * @param id
     * @param name
     * @Modifying 指定方法为更新方法
     */
    @Query(value = "update Customer set custName=?2 where custId=?1")
    @Modifying
    public void updateCustomer(Integer id,String name);

    //利用sql语句查询所有
    @Query(nativeQuery = true,value = "select * from cst_customer")
    public List<Customer> findAllBySql();

    //利用sql语句模糊查询
    @Query(nativeQuery = true,value = "select * from cst_customer where cust_name like ?1")
    public List<Customer> findByNameSql(String name);

    //利用命名规则方法根据用户名查询
    public Customer findByCustName(String custName);
    //利用命名规则方法模糊查询
    public List<Customer> findByCustNameLike(String custName);
    //利用命名规则多条件查询
    public List<Customer> findByCustNameLikeAndAndCustIndustry(String name,String industry);
}

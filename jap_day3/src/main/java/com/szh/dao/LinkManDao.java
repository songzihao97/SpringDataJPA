package com.szh.dao;

import com.szh.domain.LinkMan;
import org.springframework.data.jpa.mapping.JpaPersistentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LinkManDao extends JpaRepository<LinkMan,Integer>, JpaSpecificationExecutor<CustomerDao> {
}

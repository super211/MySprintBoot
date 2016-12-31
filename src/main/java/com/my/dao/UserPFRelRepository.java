/*
 * Copyright (c) 2016, Deutsche Bank APAC Wealth Management All Rights Reserved.
 *
 */

package com.my.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.my.model.UserPf;

@Repository
public interface UserPFRelRepository extends PagingAndSortingRepository<UserPf, String> {

	@Query(value="SELECT * FROM USER_PF A WHERE A.USER_ID = :USER_ID", nativeQuery=true)
	public List<UserPf> findUserPortId(@Param("USER_ID") String USER_ID); 
	
	@Transactional
	@Query(value="SELECT * FROM USER_PF", nativeQuery=true)
	public List<UserPf> findUserList(); 
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM USER_PF A WHERE A.USER_ID = :USER_ID", nativeQuery=true)
	public void deleteByUserId(@Param("USER_ID") String USER_ID);
}


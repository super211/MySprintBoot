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
import com.my.model.UserPfRel;

@Repository
public interface UserPFRelRepository extends PagingAndSortingRepository<UserPf, String> {

	@Query(value = "SELECT * FROM USER_PF A WHERE A.USER_ID = :userId", nativeQuery = true)
	public List<UserPf> findUserPortId(@Param("userId") String userId);
	
	//We can use LIMIT for mysql
	@Query(value = "SELECT * FROM (SELECT * FROM USER_PF A WHERE A.USER_ID = :userId ORDER BY PF_ID) WHERE  ROWNUM <= :lastNo", nativeQuery = true)
	public List<UserPf> findUserPortIdLatest(@Param("userId") String userId, @Param("lastNo") Integer lastNo);

	@Transactional
	@Query(value = "SELECT * FROM USER_PF", nativeQuery = true)
	public List<UserPf> findUserList();

	// Modifying is necessary for deletion and update, otherwise data cannot be
	// changed in db
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM USER_PF A WHERE A.USER_ID = :userId", nativeQuery = true)
	public void deleteByUserId(@Param("userId") String userId);

	// It means that it will look for "findUserPortRelIds" in the UserPf
	@Query(name = "UserPf.findUserPortRelIds", nativeQuery = true)
	public List<UserPfRel> findUserPortRelIds(@Param("userId") String userId);

	@Query(name = "UserPf.findUserPortIds", nativeQuery = true)
	public List<UserPfRel> findUserPortIds(@Param("userId") String userId);
}

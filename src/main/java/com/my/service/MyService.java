/*
 * 	* This business class have logic of portfolio overview part, assetmarket value overall market value & portfolio movement.
 *  * In this we have called external service currency conversion to convert base currency into desired currency by user.
 *  * Base Url will be in application properties of project.
 *  
 *    * @author parwlok
 *    * @date 25/11/2016
 *     
 */

package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.UserPFRelRepository;
import com.my.model.UserPf;

@Service
//@Transactional
public class MyService {

	@Autowired(required = true)
	UserPFRelRepository userPFRelRepository;


	/**
	 * This method is give list of asste market value of particular portfolio. 
	 * @param userId This is a repository for asset market value.
	 * @param repository5 This is a repository for get user portfolio
	 * @return List  This returns List of  data of given portfolio or relation
	 */
	public List<UserPf> processUserById(String userId){
		List<UserPf> listUserPortRel = null;
		listUserPortRel = userPFRelRepository.findUserPortId(userId);
		return listUserPortRel;
	}
	
	public Iterable<UserPf> processUserList(){
		Iterable<UserPf> listUserPortRel = null;
		listUserPortRel = userPFRelRepository.findAll();
		return listUserPortRel;
	}
	

	public void processDeleteByUserId(String userId){
		userPFRelRepository.deleteByUserId(userId);
	}
	
	public void processAddByUserId(String userId){
		
		UserPf userPf  = new UserPf();
		userPf.setUserId(userId);
		userPf.setPfId("1234567.89");
		userPf.setBranchCode(1);
		
		userPFRelRepository.save(userPf);
	}
} 
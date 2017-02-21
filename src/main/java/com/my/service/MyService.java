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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.my.dao.UserPFRelRepository;
import com.my.model.UserIdRequest;
import com.my.model.MyResp;
import com.my.model.UserPf;
import com.my.model.UserPfRel;

@Service
// @Transactional
public class MyService {

	@Autowired(required = true)
	UserPFRelRepository userPFRelRepository;
	
	@Autowired
	RestTemplate _RestTemplate;

	@Value("${exchangerate.service.url}")
	private String exhcangeServiceUrl;

	/**
	 * This method is give list of asste market value of particular portfolio.
	 * 
	 * @param userId
	 *            This is a repository for asset market value.
	 * @param repository5
	 *            This is a repository for get user portfolio
	 * @return List This returns List of data of given portfolio or relation
	 */
	public List<UserPf> processUserById(String userId) {
		List<UserPf> listUserPortRel = null;
		listUserPortRel = userPFRelRepository.findUserPortId(userId);
		return listUserPortRel;
	}
	
	public List<UserPf> processUserByIdLatest(String userId, Integer lastNo) {
		List<UserPf> listUserPortRel = null;
		listUserPortRel = userPFRelRepository.findUserPortIdLatest(userId, lastNo);
		return listUserPortRel;
	}

	public Iterable<UserPf> processUserList() {
		Iterable<UserPf> listUserPortRel = null;
		listUserPortRel = userPFRelRepository.findAll();
		return listUserPortRel;
	}

	public boolean processDeleteByUserId(String userId) {
		boolean ret = true;

		try {
			userPFRelRepository.deleteByUserId(userId);
		} catch (Exception e) {
			ret = false;
		}

		return ret;
	}

	public boolean processAddByUserId(String userId) {

		boolean ret = true;

		UserPf userPf = new UserPf();
		userPf.setUserId(userId);
		userPf.setPfId("1234567.89");
		userPf.setBranchCode(1);

		try {
			userPFRelRepository.save(userPf);
		} catch (Exception e) {
			ret = false;
		}

		return ret;
	}

	public List<UserPfRel> processUserPortfolioRel(String userId) {
		List<UserPfRel> listUserPortRel = null;
		listUserPortRel = userPFRelRepository.findUserPortRelIds(userId);
		return listUserPortRel;
	}

	public List<UserPfRel> processUserPortfolio(String userId) {
		List<UserPfRel> listUserPortRel = null;
		listUserPortRel = userPFRelRepository.findUserPortIds(userId);
		return listUserPortRel;
	}

	/**
	 * This method is to deal with the my specified response
	 * 
	 * @return MyResp
	 */
	public MyResp processMyResp(String statusDesc, boolean status) {
		MyResp myResp = new MyResp();

		if (status) {
			myResp.setStatusCd("SUCCESS");
		} else {
			myResp.setStatusCd("FAIL");
		}

		myResp.setStatusDesc(statusDesc);
		return myResp;
	}

	/**
	 * This method is used to generate random number for the userId
	 * 
	 * @return random number
	 */
	public int generateRandom() {
		Random generator = null;
		try {
			generator = new Random(System.currentTimeMillis());
			return generator.nextInt(999999 - 100000) + 100000;
		} finally {
			generator = null;
		}

	}
	
	public String createUserId(String userId, String password, String newPassword) throws NotFoundException {

		try {
			UserIdRequest createEbankUserId = new UserIdRequest();
			createEbankUserId.setUserId(userId);
			createEbankUserId.setPassword(password);
			createEbankUserId.setPassword(newPassword);

			HttpHeaders headerObject = new HttpHeaders();
			headerObject.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<UserIdRequest> httpEntity = new HttpEntity<UserIdRequest>(createEbankUserId,
					headerObject);

			String url = exhcangeServiceUrl.replaceAll(" ", "");
			URI uri = new URI(url);
			ResponseEntity<String> str = _RestTemplate.postForEntity(uri, httpEntity, String.class);

			return str.getBody().replaceAll("\"", "").replaceAll("}", "").split(":")[1];

		} catch (URISyntaxException ex) {
			throw new RuntimeException();
		}

	}
}

/*
 * 	* This is controller class having request mapping for different mapping Url.
 *  * In this we are getting excange service URL from application properties and pass it on different methods.
 *    * @author parwlok
 *    * @date 25/11/2016
 *     
 */
package com.my.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.model.UserPf;
import com.my.service.MyService;

@Validated
@RestController
public class Controller {

	@Autowired(required = true)
	MyService myService;

	@RequestMapping(method = RequestMethod.GET, path = "/user-lis-by-id")
	public @ResponseBody List<UserPf> UserPortRel(
			@RequestParam(value = "userId") @NotNull(message = "validation.name.notnull") @Size(min = 1, max = 15, message = "validation.param.size") String userId,
			HttpServletRequest request) {
		List<UserPf> listUserPortfolio = null;

		listUserPortfolio = myService.processUserById(userId);
		return listUserPortfolio;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/user-list")
	public @ResponseBody Iterable<UserPf> UserList(HttpServletRequest request) {
		Iterable<UserPf> listUserPortfolio = null;
		listUserPortfolio = myService.processUserList();
		return listUserPortfolio;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/delete-user-by-id")
	public void DelByUserId(
			@RequestParam(value = "userId") @NotNull(message = "validation.name.notnull") @Size(min = 1, max = 15, message = "validation.param.size") String userId,
			HttpServletRequest request) {
		myService.processDeleteByUserId(userId);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/add-user-by-id")
	public void AddByUserId(
			@RequestParam(value = "userId") @NotNull(message = "validation.name.notnull") @Size(min = 1, max = 15, message = "validation.param.size") String userId,
			HttpServletRequest request) {
		myService.processAddByUserId(userId);
	}

}

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
import com.my.model.UserPfRel;
import com.my.service.MyService;

@Validated
@RestController
public class Controller {

	@Autowired(required = true)
	MyService myService;

	/*
	 * http://localhost:18888/user-list-by-id?userId=US001
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/user-list-by-id")
	public @ResponseBody List<UserPf> UserPortRel(
			@RequestParam(value = "userId") @NotNull(message = "validation.name.notnull") @Size(min = 1, max = 15, message = "validation.param.size") String userId,
			HttpServletRequest request) {
		List<UserPf> listUserPortfolio = null;

		listUserPortfolio = myService.processUserById(userId);
		return listUserPortfolio;
	}

	/*
	 * http://localhost:8888/user-list?userId=US001
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/user-list")
	public @ResponseBody Iterable<UserPf> UserList(HttpServletRequest request) {
		Iterable<UserPf> listUserPortfolio = null;
		listUserPortfolio = myService.processUserList();
		return listUserPortfolio;
	}

	/*
	 * http://localhost:18888/delete-user-by-id?userId=US088
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/delete-user-by-id")
	public void DelByUserId(
			@RequestParam(value = "userId") @NotNull(message = "validation.name.notnull") @Size(min = 1, max = 15, message = "validation.param.size") String userId,
			HttpServletRequest request) {
		myService.processDeleteByUserId(userId);
	}

	/*
	 * http://localhost:18888/add-user-by-id?userId=US088
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/add-user-by-id")
	public void AddByUserId(
			@RequestParam(value = "userId") @NotNull(message = "validation.name.notnull") @Size(min = 1, max = 15, message = "validation.param.size") String userId,
			HttpServletRequest request) {
		myService.processAddByUserId(userId);
	}

	/*
	 * http://localhost:18888/user-portfolio-relations?userId=US001
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/user-portfolio-relations")
	public @ResponseBody List<UserPfRel> UserPortRels(
			@RequestParam(value = "userId") @NotNull(message = "validation.name.notnull") @Size(min = 1, max = 15, message = "validation.param.size") String userId,
			HttpServletRequest request) {

		List<UserPfRel> listUserPortfolio = myService.processUserPortfolioRel(userId);

		if (listUserPortfolio == null || listUserPortfolio.isEmpty()) {
			listUserPortfolio = myService.processUserPortfolio(userId);
		}
		return listUserPortfolio;
	}
}
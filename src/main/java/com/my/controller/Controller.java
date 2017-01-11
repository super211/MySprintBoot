
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.model.Car;
import com.my.model.MyResp;
import com.my.model.RequestWrapper;
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
	public ResponseEntity<MyResp> DelByUserId(
			@RequestParam(value = "userId") @NotNull(message = "validation.name.notnull") @Size(min = 1, max = 15, message = "validation.param.size") String userId,
			HttpServletRequest request) {
		boolean status = true;

		status = myService.processDeleteByUserId(userId);

		MyResp myResp = myService.processMyResp("DELETE_USER", status);

		return new ResponseEntity<MyResp>(myResp, HttpStatus.OK);
	}

	/*
	 * http://localhost:18888/add-user-by-id?userId=US088
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/add-user-by-id")
	public ResponseEntity<MyResp> AddByUserId(
			@RequestParam(value = "userId") @NotNull(message = "validation.name.notnull") @Size(min = 1, max = 15, message = "validation.param.size") String userId,
			HttpServletRequest request) {
		boolean status = true;

		status = myService.processAddByUserId(userId);

		MyResp myResp = myService.processMyResp("ADD_USER", status);

		return new ResponseEntity<MyResp>(myResp, HttpStatus.OK);
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
	
	/*
	http://localhost:18888/vehicle/cars
	[
	  {
	    "color":"Blue",
	    "miles":200,
	    "vin":"1234"
	  },
	  {
	    "color":"Red",
	    "miles":500,
	    "vin":"1235"
	  }
	]
	 */
	@RequestMapping(value = "/vehicle/cars", method = RequestMethod.POST)
	public ResponseEntity<List<Car>> update(@RequestBody List<Car> cars) {

		cars.stream().forEach(c -> {
			c.setMiles(c.getMiles() + 100);
			c.setVIN("VIN" + c.getVIN());
		});

		return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
	}
	
	/*
	http://localhost:18888/carsandtrucks
	{
		"cars": [{
			"color": "Blue",
			"miles": 100,
			"vin": "1234"
		}, {
			"color": "Red",
			"miles": 400,
			"vin": "1235"
		}],
		"truck": {
			"color": "Red",
			"miles": 400,
			"vin": "1235"
		}
	}
	 */
	@RequestMapping(value = "/carsandtrucks", method = RequestMethod.POST)
	public ResponseEntity<RequestWrapper> updateWithMultipleObjects(
	        @RequestBody RequestWrapper requestWrapper) {

	    requestWrapper.getCars().stream()
	            .forEach(c -> c.setMiles(c.getMiles() + 100));

	    // TODO: call persistence layer to update

	    return new ResponseEntity<RequestWrapper>(requestWrapper, HttpStatus.OK);
	}
}
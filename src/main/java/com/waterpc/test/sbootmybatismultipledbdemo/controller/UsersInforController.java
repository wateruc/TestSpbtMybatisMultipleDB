package com.waterpc.test.sbootmybatismultipledbdemo.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.waterpc.test.sbootmybatismultipledbdemo.service.UsersInforEntityService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/userinfor")
public class UsersInforController {

	private static Logger logger = LoggerFactory.getLogger(UsersInforController.class);
	
	@Autowired
	private UsersInforEntityService usersInforService;
	
	@ApiOperation(value="获取一个用户的详细信息", notes="根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long", paramType = "path")
	@RequestMapping(value = "/getone/{id}", method = RequestMethod.GET)
	public Map<String, Object> getOneUserInfor(@PathVariable(name="id") long id){
		Map<String, Object> returnReslt = new HashMap<>();
		try {
			returnReslt.put("result", usersInforService.getOneUserInfor(id));
			returnReslt.put("status", "success");
		} catch (Exception e) {
			returnReslt.put("msg", e.getMessage());
			returnReslt.put("status", "failed");
			logger.error(e.getMessage());
		}
		return returnReslt;
	}
	
}

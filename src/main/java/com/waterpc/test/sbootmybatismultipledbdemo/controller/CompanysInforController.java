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

import com.waterpc.test.sbootmybatismultipledbdemo.service.CompanysInforEntityService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/companysinfor")
public class CompanysInforController {

	private static Logger logger = LoggerFactory.getLogger(CompanysInforController.class);
	
	@Autowired
	private CompanysInforEntityService companyInfoService;
	
	@ApiOperation(value="获取一个公司的详细信息", notes="根据url的id来获取公司详细信息")
	@ApiImplicitParam(name = "id", value = "公司ID", required = true, dataType = "long", paramType = "path")
	@RequestMapping(value = "/getone/{id}", method = RequestMethod.GET)
	public Map<String, Object> getOneUserInfor(@PathVariable("id") long id) {
		Map<String, Object> returnReslt = new HashMap<>();
		try {
			returnReslt.put("result", companyInfoService.getOneCompanyInfor(id));
			returnReslt.put("status", "success");
		} catch (Exception e) {
			returnReslt.put("msg", e.getMessage());
			returnReslt.put("status", "failed");
		}
		return returnReslt;
	}
	
}

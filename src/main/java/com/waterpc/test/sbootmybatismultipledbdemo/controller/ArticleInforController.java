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

import com.waterpc.test.sbootmybatismultipledbdemo.service.ArticleInforEntityService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/articlesinfor")
public class ArticleInforController {

	private static Logger logger = LoggerFactory.getLogger(ArticleInforController.class);
	
	@Autowired
	private ArticleInforEntityService articleInfoService;
	
	@ApiOperation(value="获取一篇文章的详细信息", notes="根据url的id来获取文章详细信息")
	@ApiImplicitParam(name = "id", value = "公司ID", required = true, dataType = "int", paramType = "path")
	@RequestMapping(value = "/getone/{id}", method = RequestMethod.GET)
	public Map<String, Object> getOneArticleInfo(@PathVariable("id") int id){
		Map<String, Object> returnReslt = new HashMap<>();
		try {
			returnReslt.put("result", articleInfoService.getOneArticleInfo(id));
			returnReslt.put("status", "success");
		} catch (Exception e) {
			returnReslt.put("msg", e.getMessage());
			returnReslt.put("status", "failed");
		}
		return returnReslt;
	}
	
}

package com.oracle.intelagr.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.intelagr.common.JsonResult;
import com.oracle.intelagr.common.PageModel;
import com.oracle.intelagr.entity.AirMoni;
import com.oracle.intelagr.entity.YearCode;
import com.oracle.intelagr.service.IYearCodeService;

@Controller
@RequestMapping("/year")
public class YearController {
	@Autowired
	private IYearCodeService yearCodeService;
	
	@RequestMapping("/list")
	public String list(String yearName,@RequestParam(defaultValue="1")int page,Map m) {
		PageModel pageModel = new PageModel();
		pageModel.setPage(page);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("yearCode", yearName);
		map.put("index", (page-1)*pageModel.getPageSize());
		map.put("pageSize", pageModel.getPageSize());
		pageModel.setData(map);
		yearCodeService.query(pageModel);
		m.put("pageModel", pageModel);
		return "yearcode/yearcodeList";
	}
	@RequestMapping("/add")
	public String add() {
		return "yearcode/yearcodeAdd";
	}
	@RequestMapping("/save")
	@ResponseBody
	public JsonResult save(@RequestBody YearCode yearCode,HttpServletRequest request) {
		JsonResult jr = null;
		int result = yearCodeService.save(yearCode, request);
		if (result>0) {
			jr = new JsonResult(true,"保存成功");
		}else {
			jr = new JsonResult(false,"保存失败");
		}
		return jr ;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public JsonResult delete(@RequestBody int[] ids,HttpServletRequest request) {
		int count=0;
		JsonResult jr = null;
		for(int i:ids) {
			YearCode yearCode = yearCodeService.queryOne(i);
			int result = yearCodeService.delete(yearCode, request);
			count+=result;
		}
		if (count==ids.length) {
			jr = new JsonResult(true,"保存成功");
		}else {
			jr = new JsonResult(false,"保存失败");
		}
		return jr;
	}
}

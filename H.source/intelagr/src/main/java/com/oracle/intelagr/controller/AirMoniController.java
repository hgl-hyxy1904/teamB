package com.oracle.intelagr.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.intelagr.common.CommonUtil;
import com.oracle.intelagr.common.JsonResult;
import com.oracle.intelagr.common.PageModel;
import com.oracle.intelagr.entity.AirMoni;
import com.oracle.intelagr.entity.CommonData;
import com.oracle.intelagr.service.IAirMoniService;
import com.oracle.intelagr.service.ICommonDataService;

@Controller
@RequestMapping("/airMoni")
public class AirMoniController {
	@Autowired
	private ICommonDataService commonDataService;
	@Autowired
	private IAirMoniService airMoniService;
	
	@RequestMapping("/list")
	public String list(String beginDate,String endDate,@RequestParam(defaultValue="1")int page,Map m) {
		PageModel pageModel = new PageModel();
		pageModel.setPage(page);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("s_monitorDate", beginDate);
		map.put("e_monitorDate", endDate);
		map.put("index", (page-1)*pageModel.getPageSize());
		map.put("pageSize", pageModel.getPageSize());
		pageModel.setData(map);
		airMoniService.query(pageModel);
		m.put("pageModel", pageModel);
		return "/airmoni/airMoniList";
	}
	@RequestMapping("/add")
	public String add(Map m) {
		List<CommonData> list = commonDataService.getCommonDataListByCodeKey("MonitorPointType");
		m.put("list", list);
		return "/airmoni/airMoniAdd";
	}
	@RequestMapping("/save")
	@ResponseBody
	public JsonResult save(@RequestBody AirMoni airMoni,HttpServletRequest request) {
		int result = airMoniService.save(airMoni, request);
		JsonResult jr = null;
		if (result>0) {
			jr = new JsonResult(true,"保存成功");
		}else {
			jr = new JsonResult(false,"保存失败");
		}
		return jr;
	}
	@RequestMapping("/edit")
	public String edit(String id,Map m) {
		AirMoni airMoni = airMoniService.queryOne(Integer.parseInt(id));
		m.put("airMoni", airMoni);
		m.put("date", new SimpleDateFormat("yyyy-MM-dd").format(airMoni.getMonitorDate()));
		return "/airmoni/airMoniEdit";
	}
	@RequestMapping("/view")
	public String view(String id,Map m) {
		AirMoni airMoni = airMoniService.queryOne(Integer.parseInt(id));
		m.put("airMoni", airMoni);
		m.put("date", new SimpleDateFormat("yyyy-MM-dd").format(airMoni.getMonitorDate()));
		return "/airmoni/airMoniView";
	}
	@RequestMapping("/update")
	@ResponseBody
	public JsonResult update(@RequestBody AirMoni airMoni,HttpServletRequest request) {
		JsonResult jr = null;
		int result = airMoniService.edit(airMoni, request);
		if (result>0) {
			jr = new JsonResult(true,"保存成功");
		}else {
			jr = new JsonResult(false,"保存失败");
		}
		return jr;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public JsonResult delete(@RequestBody int[] ids,HttpServletRequest request) {
		int count=0;
		JsonResult jr = null;
		for(int i:ids) {
			AirMoni airMoni = airMoniService.queryOne(i);
			int result = airMoniService.delete(airMoni, request);
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

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

import com.oracle.intelagr.common.JsonResult;
import com.oracle.intelagr.common.PageModel;
import com.oracle.intelagr.entity.AirMoni;
import com.oracle.intelagr.entity.CommonData;
import com.oracle.intelagr.entity.WaterMoni;
import com.oracle.intelagr.service.ICommonDataService;
import com.oracle.intelagr.service.IWaterMoniService;

@Controller
@RequestMapping("/waterMoni")
public class WaterMoniController {
	@Autowired
	private ICommonDataService commonDataService;
	@Autowired
	private IWaterMoniService waterMoniService;
	
	
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
		waterMoniService.query(pageModel);
		m.put("pageModel", pageModel);
		return "/watermoni/waterMoniList";
	}
	@RequestMapping("/add")
	public String add(Map m) {
		List<CommonData> list = commonDataService.getCommonDataListByCodeKey("MonitorPointType");
		m.put("list", list);
		return "/watermoni/waterMoniAdd";
	}
	@RequestMapping("/save")
	@ResponseBody
	public JsonResult save(@RequestBody WaterMoni waterMoni,HttpServletRequest request) {
		int result = waterMoniService.save(waterMoni, request);
		JsonResult jr = null;
		if (result>0) {
			jr = new JsonResult(true,"保存成功");
		}else {
			jr = new JsonResult(false,"保存失败");
		}
		return jr;
	}
	@RequestMapping("/view")
	public String view(String id,Map m) {
		WaterMoni waterMoni = waterMoniService.queryOne(Integer.parseInt(id));
		m.put("waterMoni", waterMoni);
		m.put("date", new SimpleDateFormat("yyyy-MM-dd").format(waterMoni.getMonitorDate()));
		return "/watermoni/waterMoniView";
	}
	@RequestMapping("/edit")
	public String edit(String id,Map m) {
		WaterMoni waterMoni = waterMoniService.queryOne(Integer.parseInt(id));
		m.put("waterMoni", waterMoni);
		m.put("date", new SimpleDateFormat("yyyy-MM-dd").format(waterMoni.getMonitorDate()));
		return "/watermoni/waterMoniEdit";
	}
	@RequestMapping("/update")
	@ResponseBody
	public JsonResult update(@RequestBody WaterMoni waterMoni,HttpServletRequest request) {
		JsonResult jr = null;
		int result = waterMoniService.edit(waterMoni, request);
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
			WaterMoni waterMoni = waterMoniService.queryOne(i);
			int result = waterMoniService.delete(waterMoni, request);
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

package com.oracle.intelagr.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.intelagr.common.BaseModel;
import com.oracle.intelagr.common.CommonUtil;
import com.oracle.intelagr.common.Constants;
import com.oracle.intelagr.common.GetSession;
import com.oracle.intelagr.common.JsonResult;
import com.oracle.intelagr.common.PageModel;
import com.oracle.intelagr.entity.InputReg;
import com.oracle.intelagr.entity.User;
import com.oracle.intelagr.service.ICompanyService;
import com.oracle.intelagr.service.IFileManagerService;
import com.oracle.intelagr.service.IInputRegService;
import com.oracle.intelagr.service.IServialNumService;
import com.oracle.intelagr.service.IYearCodeService;


@Controller
@RequestMapping("/inputReg")
public class InputRegController {
	@Autowired
	private IInputRegService inputRegService;
	@Autowired
	private IFileManagerService fileManagerService;
	@Autowired
	private IServialNumService servialNumService;
	@Autowired
	private IYearCodeService yearCodeService;
	@Autowired
	private ICompanyService companyService;
	@RequestMapping("/editInput")
	public String editInput(Map map,HttpServletRequest request){
		User user = GetSession.getSessionEntity(request);
		String applyBatchNo = servialNumService.getServialNum(Constants.BIZ_TYPE_TR);
		map.put("applyBatchNo",applyBatchNo );
		map.put("companyCode",user.getCompanyCode() );
		return "/inputreg/inputRegEdit";
	}
	@RequestMapping("/save")
	@ResponseBody
	public JsonResult save(@RequestBody InputReg inputReg,HttpServletRequest request) {		
		HttpSession session = request.getSession();
		inputReg.setCreateDate(new Date());
		inputReg.setCreateUserId(((User)session.getAttribute("user")).getUserID());
		inputReg.setUpdateDate(new Date());
		inputReg.setUpdateUserId(((User)session.getAttribute("user")).getUserID());
		inputRegService.save(inputReg);
		CommonUtil.saveMfile(fileManagerService, "02", String.valueOf(inputReg.getId()), request);
		return new JsonResult(true);
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="1")int pageSize,Map m) {
		String year = request.getParameter("year");
		String companyCode = request.getParameter("companyCode");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		PageModel pageModel = new PageModel() ;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("year", year);
		map.put("companyCode", companyCode);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		map.put("index", (page-1)*pageModel.getPageSize());
		map.put("pageSize", pageModel.getPageSize());
		List<InputReg> list =inputRegService.query(map);
		m.put("inputRegList", list);
		return "/inputreg/inputRegList";
	}
	
	@RequestMapping("/add")
	public String add() {
		
		return "forward:/inputReg/editInput";
	}
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request,Map m) {
	    String id = request.getParameter("id");
	    String reFlag = request.getParameter("reFlag");
	    InputReg inputReg = inputRegService.selectById(Integer.parseInt(id));
	    m.put("inputReg", inputReg);
	    //把接到的reFlag发送到界面因为界面需要接一个reFlag才能往list跳转
	    m.put("reFlag", reFlag);
	    return "/inputreg/inputRegEdit2";
	}
	@RequestMapping("/delete")
	@ResponseBody
	public JsonResult delete(@RequestBody List<String> ids ) {
		int sum=0;
		for(String id:ids) {
			int result =inputRegService.delete(id);
			sum+=result;
		}
		JsonResult jr = null;
		if (sum==ids.size()) {
			jr =new JsonResult(true);
		}else {
			jr =new JsonResult(false,"删除失败");
		}
		//inputRegService.delete(ids);
		return jr;
	}
	@RequestMapping("/update")
	@ResponseBody
	public JsonResult update(@RequestBody InputReg inputReg,HttpServletRequest request) {		
		BaseModel baseModel = CommonUtil.getBaseModel(request);
		inputReg.setUpdateDate(baseModel.getUpdateDate());
		inputReg.setUpdateUserId(baseModel.getUpdateUserId());
		inputRegService.update(inputReg);
		return new JsonResult(true);
	}
	@RequestMapping("/view")
	public String view(String id,Map m,String retFlag) {
	    InputReg inputReg = inputRegService.selectById(Integer.parseInt(id));
	    m.put("inputReg", inputReg);
	    m.put("retFlag", retFlag);
		return "/inputreg/inputRegView";
	}
	
}

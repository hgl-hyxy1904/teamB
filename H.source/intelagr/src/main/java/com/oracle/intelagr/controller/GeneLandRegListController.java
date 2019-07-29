package com.oracle.intelagr.controller;

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

import com.oracle.intelagr.common.Constants;
import com.oracle.intelagr.common.GetSession;
import com.oracle.intelagr.common.JsonResult;
import com.oracle.intelagr.common.PageModel;
import com.oracle.intelagr.entity.AreaDevision;
import com.oracle.intelagr.entity.CommonData;
import com.oracle.intelagr.entity.GeneLandReg;
import com.oracle.intelagr.entity.GeneLandRegD;
import com.oracle.intelagr.entity.User;
import com.oracle.intelagr.service.IAreaDevisionService;
import com.oracle.intelagr.service.ICommonDataService;
import com.oracle.intelagr.service.IGeneLandRegDService;
import com.oracle.intelagr.service.IGeneLandRegService;
import com.oracle.intelagr.service.IServialNumService;

import net.sf.json.JSONArray;
import net.sf.json.JSONString;

@Controller
@RequestMapping("/geneLandReg")
public class GeneLandRegListController {
	@Autowired
	private IGeneLandRegService geneLandRegService;
	@Autowired
	private IGeneLandRegDService geneLandRegDService;
	@Autowired
	private IAreaDevisionService areaDevisionService;
	@Autowired
	private ICommonDataService commonDataService;
	@Autowired
	private IServialNumService servialNumService;
	
	
	@RequestMapping("/list")
	public String list(@RequestParam(defaultValue="1")int page,String year,String companyCode,String beginDate,String endDate,HttpServletRequest request,Map<String, Object> m) {
		User user = GetSession.getSessionEntity(request);
		m.put("companyCode", user.getCompanyCode());
		//查询出数据库中的数据
		//条件查询的map
		PageModel pageModel = new PageModel();
		Map<String,Object> geneLandRegListMap = new HashMap<String,Object>();
		geneLandRegListMap.put("year", year);
		geneLandRegListMap.put("companyCode", companyCode);
		geneLandRegListMap.put("s_operatorDate", beginDate);
		geneLandRegListMap.put("e_operatorDate", endDate);
		geneLandRegListMap.put("index", (page-1)*pageModel.getPageSize());
		geneLandRegListMap.put("pageSize", pageModel.getPageSize());
		pageModel.setData(geneLandRegListMap);
		pageModel.setPage(page);
		geneLandRegService.query(pageModel);
		List<GeneLandReg> list = (List<GeneLandReg>) pageModel.getResult();
		int sum=0;
		for(GeneLandReg g:list) {
			for(GeneLandRegD gd:g.getList()) {
				sum+=gd.getArchiveAcreage();
			}
		}
		//将组装好的pageModel发回页面
		m.put("pageModel", pageModel);
		m.put("sum", sum);
		return "/genelandreg/geneLandRegList";
	}
	@RequestMapping("/delete")
	@ResponseBody
	public JsonResult delete(@RequestBody int[] ids ,HttpServletRequest request) {
		int resultsum=0;
		for(int id:ids) {
			GeneLandReg geneLandReg = new GeneLandReg();
			geneLandReg.setId(id);
			geneLandReg.setDeleteFlag("Y");
			int result = geneLandRegService.delete(geneLandReg,request);
			resultsum+=result;
		}
		JsonResult jr = null;
		if (resultsum>=ids.length) {
			jr=new JsonResult(true,"删除成功");
		}else {
			jr=new JsonResult(false,"删除失败");
		}
		return jr;
	}
	@RequestMapping("/listadd")
	public String add(Map m,HttpServletRequest request) {
		User user = GetSession.getSessionEntity(request);
		String ApplyBatchNo = servialNumService.getServialNum(Constants.BIZ_TYPE_PT);
		m.put("ApplyBatchNo", ApplyBatchNo);
		m.put("companyCode", user.getCompanyCode());
		return "/genelandreg/geneLandRegEdit";
	}
	@RequestMapping("/listedit")
	public String edit(String retFlag,String id,String year,String companyCode,String status,String beginDate,String endDate,Map m) {
		//通过查询数据库得到该行的数据
		GeneLandRegD geneLandRegD = new GeneLandRegD();
		geneLandRegD.setHid(Integer.parseInt(id));
		List<GeneLandRegD> geneLandRegDList=geneLandRegDService.query(geneLandRegD);
		for(GeneLandRegD g:geneLandRegDList) {
			//查出已备案面积
			g.setZmj(g.getContractTotalYield());
			g.setYba(g.getRegisteredTotalYield()+g.getArchiveAcreage());
			g.setKba(g.getContractTotalYield()-g.getRegisteredTotalYield()-g.getArchiveAcreage());
			//查出地址名称
			AreaDevision areaDevision = new AreaDevision();
			areaDevision.setTownCode(g.getTownCode());
			areaDevision.setCountryCode(g.getCountryCode());
			List<AreaDevision> areaDevisionList = areaDevisionService.query(areaDevision);
			g.setTownName(areaDevisionList.get(0).getTownName());
			g.setCountryName(areaDevisionList.get(0).getCountryName());
			//查出承包商类型名字和证件类型名字
			CommonData commondataIdType = commonDataService.getCommonData("IDType", g.getIdType());
			CommonData commondataContractorType = commonDataService.getCommonData("ContractorType", g.getContractorType());
			g.setIdName(commondataIdType.getCodeValue());
			g.setContractorTypeName(commondataContractorType.getCodeValue());
		}
		GeneLandReg geneLandReg = geneLandRegService.selectById(Integer.parseInt(id));
		m.put("geneyear", geneLandReg.getYear());
		m.put("companyCode", geneLandReg.getCompanyCode());
		m.put("ApplyBatchNo", geneLandReg.getApplyBatchNo());
		m.put("retFlag", retFlag);
		m.put("id", id);
		m.put("tyear", year);
		m.put("tcompanyCode", companyCode);
		m.put("tstatus", status);
		m.put("tbeginDate", beginDate);
		m.put("tendDate", endDate);
		m.put("geneLandRegDList", geneLandRegDList);
		return "/genelandreg/geneLandRegEdit";
	}
	@RequestMapping("/view")
	public String view(String id,String year,String companyCode,String status,String beginDate,String endDate,Map m) {
		//通过查询数据库得到该行的数据
				GeneLandRegD geneLandRegD = new GeneLandRegD();
				geneLandRegD.setHid(Integer.parseInt(id));
				List<GeneLandRegD> geneLandRegDList=geneLandRegDService.query(geneLandRegD);
				for(GeneLandRegD g:geneLandRegDList) {
					//查出已备案面积
					g.setZmj(g.getContractTotalYield());
					g.setYba(g.getRegisteredTotalYield()+g.getArchiveAcreage());
					g.setKba(g.getContractTotalYield()-g.getRegisteredTotalYield()-g.getArchiveAcreage());
					//查出地址名称
					AreaDevision areaDevision = new AreaDevision();
					areaDevision.setTownCode(g.getTownCode());
					areaDevision.setCountryCode(g.getCountryCode());
					List<AreaDevision> areaDevisionList = areaDevisionService.query(areaDevision);
					g.setTownName(areaDevisionList.get(0).getTownName());
					g.setCountryName(areaDevisionList.get(0).getCountryName());
					//查出承包商类型名字和证件类型名字
					CommonData commondataIdType = commonDataService.getCommonData("IDType", g.getIdType());
					CommonData commondataContractorType = commonDataService.getCommonData("ContractorType", g.getContractorType());
					g.setIdName(commondataIdType.getCodeValue());
					g.setContractorTypeName(commondataContractorType.getCodeValue());
				}
				GeneLandReg geneLandReg = geneLandRegService.selectById(Integer.parseInt(id));
				m.put("geneyear", geneLandReg.getYear());
				m.put("companyCode", geneLandReg.getCompanyCode());
				m.put("ApplyBatchNo", geneLandReg.getApplyBatchNo());
				m.put("id", id);
				m.put("tyear", year);
				m.put("tcompanyCode", companyCode);
				m.put("tstatus", status);
				m.put("tbeginDate", beginDate);
				m.put("tendDate", endDate);
				m.put("geneLandRegDList", geneLandRegDList);		
		
		return "/genelandreg/geneLandRegView";
	}
}

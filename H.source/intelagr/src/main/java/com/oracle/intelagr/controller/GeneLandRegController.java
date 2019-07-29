package com.oracle.intelagr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.intelagr.common.Constants;
import com.oracle.intelagr.common.GetSession;
import com.oracle.intelagr.common.JsonResult;
import com.oracle.intelagr.common.PageModel;
import com.oracle.intelagr.entity.AreaDevision;
import com.oracle.intelagr.entity.CommonData;
import com.oracle.intelagr.entity.Contract;
import com.oracle.intelagr.entity.GeneLandReg;
import com.oracle.intelagr.entity.GeneLandRegD;
import com.oracle.intelagr.entity.Peasant;
import com.oracle.intelagr.entity.User;
import com.oracle.intelagr.service.IAreaDevisionService;
import com.oracle.intelagr.service.ICommonDataService;
import com.oracle.intelagr.service.IContractService;
import com.oracle.intelagr.service.IGeneLandRegDService;
import com.oracle.intelagr.service.IGeneLandRegService;
import com.oracle.intelagr.service.IPeasantService;
import com.oracle.intelagr.service.IServialNumService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/geneLandReg")
public class GeneLandRegController {
	@Autowired
	private IServialNumService servialNumService;
	@Autowired
	private IPeasantService peasantService;
	@Autowired
	private IAreaDevisionService areaDevisionService;
	@Autowired
	private IContractService contractService;
	@Autowired
	private ICommonDataService commonDataService;
	@Autowired
	private IGeneLandRegService geneLandRegService;
	@Autowired
	private IGeneLandRegDService geneLandRegDService;
	
	@RequestMapping("/editInput")
	public String editInput(Map m,HttpServletRequest request) {
		User user = GetSession.getSessionEntity(request);
		String ApplyBatchNo = servialNumService.getServialNum(Constants.BIZ_TYPE_PT);
		m.put("ApplyBatchNo", ApplyBatchNo);
		m.put("companyCode", user.getCompanyCode());
		return "/genelandreg/geneLandRegEdit";
	}
	@RequestMapping("/add")
	public String add(String id,Map m) {
		return "/genelandreg/geneLandRegDEdit";
	}
	@RequestMapping("/expandInfo")
	@ResponseBody
	public JsonResult expandInfo(@RequestBody Peasant peasant) {
		List<Peasant> peasantList = peasantService.query(peasant);
		JsonResult jr = null;
		if (peasantList.size()>0) {
			jr = new JsonResult(true,"存在");
			JSONObject obj = new JSONObject();
			obj.put("peasant", peasantList.get(0));
			//合同表查询
			Contract contract = new Contract();
			contract.setContractorCode(peasantList.get(0).getContractorCode());
			List<Contract> contractList = contractService.query(contract);
			double zmj =0;
			for(Contract contractzmj:contractList) {
				zmj += contractzmj.getMeasurementMu();
			}
			//新增页面 已备案面积为通过条件查数据库
			GeneLandRegD geneLandRegD = new GeneLandRegD();
			geneLandRegD.setContractorID(peasantList.get(0).getContractorID());
			List<GeneLandRegD> geneLandRegDList = geneLandRegDService.query(geneLandRegD);
			//循环这个子表数据聚合，得到已备案面积
			double yba = 0;
			for(GeneLandRegD g: geneLandRegDList) {
				yba+=g.getArchiveAcreage();
			}
			double kba = zmj-yba;
			obj.put("zmj", String.valueOf(zmj));
			obj.put("yba", String.valueOf(yba));
			obj.put("kba", String.valueOf(kba));
			//将合同表的数据组装为JSONArray发回jsp页面
			Contract[] contractarray = new Contract[contractList.size()];
			for (int i = 0; i < contractList.size(); i++) {
				 Contract c = contractList.get(i);
				 CommonData commonData1 =commonDataService.getCommonData("PlowlandType", c.getLandType());
				 CommonData commonData2 =commonDataService.getCommonData("PlowlandClass", c.getLandClass());
				 c.setLandTypeName(commonData1.getCodeValue());
				 c.setLandClassName(commonData2.getCodeValue());
				contractarray[i] = c;
			}
			obj.put("contract", contractarray);
			jr.setData(obj.toString());
		}else {
			jr = new JsonResult(false,"身份信息不存在");
		}
		return jr;
	}
	
	@RequestMapping("/getAreaDevisions")
	@ResponseBody
	public JsonResult getAreaDevisions(@RequestBody Peasant peasant) {
		JSONArray array = new JSONArray();
		AreaDevision areaDevision = new AreaDevision();
		areaDevision.setCountryCode(peasant.getCountryCode());
		areaDevision.setTownCode(peasant.getTownCode());
		List<AreaDevision> list = areaDevisionService.query(areaDevision);
		JsonResult jr = null;
		if (list.size()>0) {
			JSONObject obj1 = new JSONObject();
			obj1.put("id", list.get(0).getTownCode());
			obj1.put("text", list.get(0).getTownName());
			array.add(obj1);
			JSONObject obj2 = new JSONObject();
			obj2.put("id", list.get(0).getCountryCode());
			obj2.put("text", list.get(0).getCountryName());
			array.add(obj2);
			jr = new JsonResult(true,"成功");
			jr.setData(array);
		}else {
			jr = new JsonResult(false,"查询住址失败");
		}
		return jr;
		
	}
	@RequestMapping("/save")
	@ResponseBody
	public JsonResult save(@RequestBody GeneLandReg geneLandReg,HttpServletRequest request) {
		JsonResult jr = null;
		//在这里判断订单的流水号。如果数据库存在则更新，不存在则添加,多余则更新为Y
		Map<String,Object> selectgeneLandRegMap = new HashMap<String,Object>();
		selectgeneLandRegMap.put("applyBatchNo", geneLandReg.getApplyBatchNo());
		PageModel pageModel = new PageModel();
		pageModel.setData(selectgeneLandRegMap);
		PageModel resultpageModel = geneLandRegService.query(pageModel);
		if (resultpageModel.getResult().size()>0) {
			//执行更新操作
			//更新主表
			GeneLandReg tablegene = (GeneLandReg) resultpageModel.getResult().get(0);
			geneLandReg.setId(tablegene.getId());
			int updateresult = geneLandRegService.update(geneLandReg,request);
			if (updateresult>0) {
				jr = new JsonResult(true,"更新成功");
			}else {
				jr = new JsonResult(false,"更新失败");
			}
		}else {
			int saveresult = geneLandRegService.save(geneLandReg,request);
			
			if (saveresult>0) {
				jr = new JsonResult(true,"保存成功");
			}else {
				jr = new JsonResult(false,"保存失败");
			}
		}
		return jr;
	}
	@RequestMapping("/edit")
	public String edit(String rowIndex,String conInfo,String t,String year,Map m) {
		m.put("rowIndex", rowIndex);
		String[] conInfos = conInfo.split(",");
		m.put("contractorType", conInfos[0]);
		m.put("idType", conInfos[1]);
		m.put("contractorID", conInfos[2]);
		m.put("tmp_archiveAcreage", conInfos[3]);
		m.put("tmp_operatorName", conInfos[4]);
		m.put("tmp_operatorDateDate", conInfos[5]);
		m.put("tmp_contractorName", conInfos[6]);
		m.put("tmp_contractorTel", conInfos[7]);
		m.put("tmp_townCode", conInfos[8]);
		m.put("tmp_countryCode", conInfos[9]);
		m.put("tmp_groupName", conInfos[10]);
		//查询出地址信息
		AreaDevision a = new AreaDevision();
		a.setTownCode(conInfos[8]);
		a.setCountryCode(conInfos[9]);
		List<AreaDevision> areaDevisionList =areaDevisionService.query(a);
		m.put("tmp_townCodeView", areaDevisionList.get(0).getTownName());
		m.put("tmp_countryCodeView", areaDevisionList.get(0).getCountryName());
		Peasant peasant = new Peasant();
		peasant.setContractorID(conInfos[2]);
		List<Peasant> peasantList = peasantService.query(peasant);
		if (peasantList.size()>0) {
			//合同表查询
			Contract contract = new Contract();
			contract.setContractorCode(peasantList.get(0).getContractorCode());
			List<Contract> contractList = contractService.query(contract);
			double zmj =0;
			for(Contract contractzmj:contractList) {
				zmj += contractzmj.getMeasurementMu();
			}
			//新增页面 已备案面积为通过条件查数据库
			GeneLandRegD geneLandRegD = new GeneLandRegD();
			geneLandRegD.setContractorID(peasantList.get(0).getContractorID());
			List<GeneLandRegD> geneLandRegDList = geneLandRegDService.query(geneLandRegD);
			//循环这个子表数据聚合，得到已备案面积
			double yba = 0;
			for(GeneLandRegD g: geneLandRegDList) {
				yba+=g.getArchiveAcreage();
			}
			double kba = zmj-yba;
			m.put("zmj", String.valueOf(zmj));
			m.put("yba", String.valueOf(yba));
			m.put("kba", String.valueOf(kba));
			//拼出合同table表的数据
			List<Contract> editcontractList = new ArrayList<Contract>();
			for (int i = 0; i < contractList.size(); i++) {
				 Contract c = contractList.get(i);
				 CommonData commonData1 =commonDataService.getCommonData("PlowlandType", c.getLandType());
				 CommonData commonData2 =commonDataService.getCommonData("PlowlandClass", c.getLandClass());
				 c.setLandTypeName(commonData1.getCodeValue());
				 c.setLandClassName(commonData2.getCodeValue());
				 editcontractList.add(c);
			}
			m.put("editcontractList", editcontractList);
			System.out.println(conInfo);
		}
		return "/genelandreg/geneLandRegNEdit";
	}
}

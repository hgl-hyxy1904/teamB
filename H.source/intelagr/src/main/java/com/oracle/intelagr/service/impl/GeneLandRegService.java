package com.oracle.intelagr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.intelagr.common.BaseModel;
import com.oracle.intelagr.common.CommonUtil;
import com.oracle.intelagr.common.PageModel;
import com.oracle.intelagr.entity.Contract;
import com.oracle.intelagr.entity.GeneLandDetail;
import com.oracle.intelagr.entity.GeneLandReg;
import com.oracle.intelagr.entity.GeneLandRegD;
import com.oracle.intelagr.entity.Peasant;
import com.oracle.intelagr.mapper.ContractMapper;
import com.oracle.intelagr.mapper.GeneLandDetailMapper;
import com.oracle.intelagr.mapper.GeneLandRegDMapper;
import com.oracle.intelagr.mapper.GeneLandRegMapper;
import com.oracle.intelagr.mapper.PeasantMapper;
import com.oracle.intelagr.service.IGeneLandRegService;
@Service
public class GeneLandRegService implements IGeneLandRegService{
	
	@Autowired
	private GeneLandRegMapper geneLandRegMapper;
	@Autowired
	private GeneLandRegDMapper geneLandRegDMapper;
	@Autowired 
	private GeneLandDetailMapper geneLandDetailMapper;
	@Autowired
	private PeasantMapper peasantMapper;
	@Autowired
	private ContractMapper contractMapper;
	@Override
	public PageModel query(PageModel pageModel) {
		Map<String,Object> map = (Map<String, Object>) pageModel.getData();
		List<GeneLandReg> geneLandRegList = geneLandRegMapper.select(map);
		int sum = geneLandRegMapper.count(map);
		pageModel.setResult(geneLandRegList);
		pageModel.setTotalCount(sum);
		return pageModel;
	}

	@Override
	@Transactional
	public int save(GeneLandReg geneLandReg,HttpServletRequest request) {
		BaseModel baseModel = CommonUtil.getBaseModel(request);
		geneLandReg.setCreateUserId(baseModel.getCreateUserId());
		geneLandReg.setCreateDate(baseModel.getCreateDate());
		geneLandReg.setUpdateUserId(baseModel.getUpdateUserId());
		geneLandReg.setUpdateDate(baseModel.getUpdateDate());
		int  geneLandRegresult = geneLandRegMapper.insert(geneLandReg);
		//根据申请批次号查询出主表数据
		for(GeneLandRegD geneLandRegD :geneLandReg.getList()) {
			//调用geneLandRegD子表的添加方法
			geneLandRegD.setCreateUserId(baseModel.getCreateUserId());
			geneLandRegD.setCreateDate(baseModel.getCreateDate());
			geneLandRegD.setUpdateUserId(baseModel.getUpdateUserId());
			geneLandRegD.setUpdateDate(baseModel.getUpdateDate());
			geneLandRegD.setHid(geneLandReg.getId());
			geneLandRegD.setCityCode("230184");
			int  geneLandRegDresult =  geneLandRegDMapper.insert(geneLandRegD);
			//向细节表添加数据
			//通过身份证号查询出ContractorCode,在通过查询合同表查出细节数据
			Map<String,Object> peasantMap = new HashMap<String,Object>();
			peasantMap.put("contractorID", geneLandRegD.getContractorID());
			List<Peasant> peasantList = peasantMapper.select(peasantMap);
			Map<String,Object> contractMap = new HashMap<String,Object>();
			contractMap.put("contractorCode", peasantList.get(0).getContractorCode());
			List<Contract> contractList = contractMapper.select(contractMap);
			//contractList为该承包商所有的合同信息
			//组装为GeneLandDetail对象存入数据库
			for(Contract contract:contractList) {
				GeneLandDetail geneLandDetail = new GeneLandDetail();
				geneLandDetail.setHid(geneLandRegD.getId());
				geneLandDetail.setLandType(contract.getLandType());
				geneLandDetail.setActualMu(contract.getMeasurementMu());
				geneLandDetail.setMeasurementMu(contract.getMeasurementMu());
				if (contract.getLandClass()==null || contract.getLandClass()=="") {
					geneLandDetail.setLandClass("05");
				}else {
					geneLandDetail.setLandClass(contract.getLandClass());
				}
				geneLandDetail.setLandName(contract.getLandName());
				geneLandDetail.setCityCode(geneLandRegD.getCityCode());
				geneLandDetail.setTownCode(geneLandRegD.getTownCode());
				geneLandDetail.setCountryCode(geneLandRegD.getCountryCode());
				geneLandDetail.setGroupName(geneLandRegD.getGroupName());
				geneLandDetail.setCreateUserId(baseModel.getCreateUserId());
				geneLandDetail.setCreateDate(baseModel.getCreateDate());
				geneLandDetail.setUpdateUserId(baseModel.getUpdateUserId());
				geneLandDetail.setUpdateDate(baseModel.getUpdateDate());
				int geneLandDetailResult = geneLandDetailMapper.insert(geneLandDetail);
			}
			
		}
		
		
		
		return  geneLandRegresult;
	}

	@Override
	@Transactional
	public int delete(GeneLandReg geneLandReg,HttpServletRequest request) {
		BaseModel baseModel = CommonUtil.getBaseModel(request);
		geneLandReg.setUpdateUserId(baseModel.getUpdateUserId());
		geneLandReg.setUpdateDate(baseModel.getUpdateDate());
		int result = geneLandRegMapper.update(geneLandReg);
		//更改子表中的deleteflag
		Map<String,Object> geneLandRegDMap = new HashMap<String,Object>();
		geneLandRegDMap.put("HID", geneLandReg.getId());
		List<GeneLandRegD> geneLandRegDList = geneLandRegDMapper.select(geneLandRegDMap);
		for(GeneLandRegD geneLandRegD:geneLandRegDList) {
			geneLandRegD.setDeleteFlag("Y");
			geneLandRegD.setUpdateUserId(baseModel.getUpdateUserId());
			geneLandRegD.setUpdateDate(baseModel.getUpdateDate());
			geneLandRegDMapper.update(geneLandRegD);
			Map<String,Object> geneLandDetailMap = new HashMap<String,Object>();
			geneLandDetailMap.put("HID", geneLandRegD.getId());
			List<GeneLandDetail> geneLandDetailList = geneLandDetailMapper.select(geneLandDetailMap);
			for(GeneLandDetail geneLandDetail:geneLandDetailList) {
				geneLandDetail.setDeleteFlag("Y");
				geneLandDetail.setUpdateUserId(baseModel.getUpdateUserId());
				geneLandDetail.setUpdateDate(baseModel.getUpdateDate());
				geneLandDetailMapper.update(geneLandDetail);
			}
		}
		return result;
	}

	@Override
	public GeneLandReg selectById(int id) {
		GeneLandReg geneLandReg = geneLandRegMapper.selectById(id);
		return geneLandReg;
	}

	@Override
	@Transactional
	public int update(GeneLandReg geneLandReg,HttpServletRequest request) {
		BaseModel baseModel = CommonUtil.getBaseModel(request);
		//更新子表数据
		//通过身份证号码查询子表 存在则更新 不存在则添加 多余则更新deleteflag
		//查出对应的子表数据
		Map<String,Object> geneLandRegDMap = new HashMap<String,Object>();
		geneLandRegDMap.put("HID", geneLandReg.getId());
		List<GeneLandRegD> geneLandRegDList = geneLandRegDMapper.select(geneLandRegDMap);
		//页面表中有的数据 和数据库做比较 做更新 增加操作
		for(GeneLandRegD tableg:geneLandReg.getList()) {
			for(GeneLandRegD g:geneLandRegDList) {
				if (g.getContractorID().equals(tableg.getContractorID())) {
					tableg.setId(g.getId());
					geneLandRegDMapper.update(tableg);
				}else {
					tableg.setCreateUserId(baseModel.getCreateUserId());
					tableg.setCreateDate(baseModel.getCreateDate());
					tableg.setUpdateUserId(baseModel.getUpdateUserId());
					tableg.setUpdateDate(baseModel.getUpdateDate());
					tableg.setHid(geneLandReg.getId());
					tableg.setCityCode("230184");
					geneLandRegDMapper.insert(tableg);
				}
			}
		}
		//将数据库中的数据与页面作比较 做删除操作
		for(GeneLandRegD g:geneLandRegDList) {
			for(GeneLandRegD tableg:geneLandReg.getList()) {
					if (!tableg.getContractorID().equals(g.getContractorID())) {
						g.setDeleteFlag("Y");
						g.setUpdateUserId(baseModel.getUpdateUserId());
						g.setUpdateDate(baseModel.getUpdateDate());
						geneLandRegDMapper.update(g);
						Map<String,Object> geneLandDetailMap = new HashMap<String,Object>();
						geneLandDetailMap.put("HID", g.getId());
						List<GeneLandDetail> geneLandDetailList = geneLandDetailMapper.select(geneLandDetailMap);
						for(GeneLandDetail geneLandDetail:geneLandDetailList) {
							geneLandDetail.setDeleteFlag("Y");
							geneLandDetail.setUpdateUserId(baseModel.getUpdateUserId());
							geneLandDetail.setUpdateDate(baseModel.getUpdateDate());
							geneLandDetailMapper.update(geneLandDetail);
						}
					}
			}
		}
		//更新主表数据
				geneLandReg.setUpdateUserId(baseModel.getUpdateUserId());
				geneLandReg.setUpdateDate(baseModel.getUpdateDate());
				int result = geneLandRegMapper.update(geneLandReg);
		
		
		return result;
	}

}

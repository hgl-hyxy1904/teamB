package com.oracle.intelagr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.intelagr.common.BaseModel;
import com.oracle.intelagr.common.CommonUtil;
import com.oracle.intelagr.common.PageModel;
import com.oracle.intelagr.entity.AirMoni;
import com.oracle.intelagr.entity.CommonData;
import com.oracle.intelagr.entity.WaterMoni;
import com.oracle.intelagr.mapper.CommonDataMapper;
import com.oracle.intelagr.mapper.WaterMoniMapper;
import com.oracle.intelagr.service.IWaterMoniService;
@Service
public class WaterMoniService implements IWaterMoniService{
	@Autowired
	private CommonDataMapper commonDataMapper;
	@Autowired
	private WaterMoniMapper waterMoniMapper;
	
	@Override
	public PageModel query(PageModel pageModel) {
		Map<String,Object> map = (Map<String,Object>)pageModel.getData();
		List<WaterMoni> waterMoniList = waterMoniMapper.select(map);
		for(WaterMoni w:waterMoniList) {
			Map<String,Object> commonDataMap = new HashMap<String,Object>();
			commonDataMap.put("codeKey", "MonitorPointType");
			commonDataMap.put("codeCode", w.getMonitorPointCode());
			List<CommonData> cdList = commonDataMapper.select(commonDataMap);
			w.setMonitorPointName(cdList.get(0).getCodeValue());
		}
		int sum = waterMoniMapper.count(map);
		pageModel.setResult(waterMoniList);
		pageModel.setTotalCount(sum);
		return pageModel;
	}

	@Override
	public int edit(WaterMoni waterMoni,HttpServletRequest request) {
		BaseModel baseModel = CommonUtil.getBaseModel(request);
		waterMoni.setUpdateUserId(baseModel.getUpdateUserId());
		waterMoni.setUpdateDate(baseModel.getUpdateDate());
		int result = waterMoniMapper.update(waterMoni);
		return result;
	}

	@Override
	public int save(WaterMoni waterMoni,HttpServletRequest request) {
		BaseModel baseModel = CommonUtil.getBaseModel(request);
		waterMoni.setCreateUserId(baseModel.getCreateUserId());
		waterMoni.setCreateDate(baseModel.getCreateDate());
		waterMoni.setUpdateUserId(baseModel.getUpdateUserId());
		waterMoni.setUpdateDate(baseModel.getUpdateDate());
		int result = waterMoniMapper.insert(waterMoni);
		return result;
	}

	@Override
	public int delete(WaterMoni waterMoni, HttpServletRequest request) {
		BaseModel baseModel = CommonUtil.getBaseModel(request);
		waterMoni.setUpdateUserId(baseModel.getUpdateUserId());
		waterMoni.setUpdateDate(baseModel.getUpdateDate());
		waterMoni.setDeleteFlag("Y");
		int result = waterMoniMapper.update(waterMoni);
		return result;
	}

	@Override
	public WaterMoni queryOne(int id) {
		WaterMoni w = waterMoniMapper.selectById(id);
		return w;
	}

}

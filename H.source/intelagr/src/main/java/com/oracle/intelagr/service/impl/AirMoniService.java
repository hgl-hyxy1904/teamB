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
import com.oracle.intelagr.entity.MoniPoint;
import com.oracle.intelagr.mapper.AirMoniMapper;
import com.oracle.intelagr.mapper.CommonDataMapper;
import com.oracle.intelagr.mapper.MoniPointMapper;
import com.oracle.intelagr.service.IAirMoniService;
@Service
public class AirMoniService implements IAirMoniService{
	@Autowired
	private AirMoniMapper airMoniMapper;
	@Autowired
	private CommonDataMapper commonDataMapper;
	@Autowired
	private MoniPointMapper moniPointMapper;
	
	@Override
	public PageModel query(PageModel pageModel) {
		Map<String,Object> map = (Map<String,Object>)pageModel.getData();
		List<AirMoni> airMoniList = airMoniMapper.select(map);
		for(AirMoni a:airMoniList) {
			Map<String,Object> moniPointMap = new HashMap<String,Object>();
			moniPointMap.put("monitorPointCode", a.getMonitorPointCode());
			List<MoniPoint> cdList = moniPointMapper.select(moniPointMap);
			a.setMonitorPointName(cdList.get(0).getMonitorPointName());
		}
		int sum = airMoniMapper.count(map);
		pageModel.setResult(airMoniList);
		pageModel.setTotalCount(sum);
		return pageModel;
	}

	@Override
	public int edit(AirMoni airMoni,HttpServletRequest request) {
		BaseModel baseModel = CommonUtil.getBaseModel(request);
		airMoni.setUpdateUserId(baseModel.getUpdateUserId());
		airMoni.setUpdateDate(baseModel.getUpdateDate());
		int result = airMoniMapper.update(airMoni);
		return result;
	}

	@Override
	public int save(AirMoni airMoni,HttpServletRequest request) {
		BaseModel baseModel = CommonUtil.getBaseModel(request);
		airMoni.setCreateUserId(baseModel.getCreateUserId());
		airMoni.setCreateDate(baseModel.getCreateDate());
		airMoni.setUpdateUserId(baseModel.getUpdateUserId());
		airMoni.setUpdateDate(baseModel.getUpdateDate());
		int result = airMoniMapper.insert(airMoni);
		return result;
	}

	@Override
	public int delete(AirMoni airMoni, HttpServletRequest request) {
		BaseModel baseModel = CommonUtil.getBaseModel(request);
		airMoni.setUpdateUserId(baseModel.getUpdateUserId());
		airMoni.setUpdateDate(baseModel.getUpdateDate());
		airMoni.setDeleteFlag("Y");
		int result = airMoniMapper.update(airMoni);
		return result;
	}

	@Override
	public AirMoni queryOne(int id) {
		AirMoni a = airMoniMapper.selectById(id);
		return a;
	}

}

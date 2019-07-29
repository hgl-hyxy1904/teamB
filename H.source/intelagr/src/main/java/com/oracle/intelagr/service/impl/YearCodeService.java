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
import com.oracle.intelagr.entity.CommonData;
import com.oracle.intelagr.entity.WaterMoni;
import com.oracle.intelagr.entity.YearCode;
import com.oracle.intelagr.mapper.YearCodeMapper;
import com.oracle.intelagr.service.IYearCodeService;
@Service
public class YearCodeService implements IYearCodeService{
	@Autowired
	private YearCodeMapper yearCodeMapper;
	@Override
	public YearCode getYearCode(String yearCode) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("yearCode", yearCode);
		List<YearCode> list = yearCodeMapper.select(params);
		if(list.size()>0) {
			return list.get(0);
		}
		return new YearCode();
	}
	@Override
	public List<YearCode> getYearCodeList() {
		Map<String,Object> params = new HashMap<String,Object>();
		return yearCodeMapper.select(params);
	}
	@Override
	public int save(YearCode yearCode, HttpServletRequest request) {
		BaseModel baseModel = CommonUtil.getBaseModel(request);
		yearCode.setCreateUserId(baseModel.getCreateUserId());
		yearCode.setCreateDate(baseModel.getCreateDate());
		yearCode.setUpdateUserId(baseModel.getUpdateUserId());
		yearCode.setUpdateDate(baseModel.getUpdateDate());
		int result = yearCodeMapper.insert(yearCode);
		return result;
	}
	@Override
	public int delete(YearCode yearCode, HttpServletRequest request) {
		BaseModel baseModel = CommonUtil.getBaseModel(request);
		yearCode.setUpdateUserId(baseModel.getUpdateUserId());
		yearCode.setUpdateDate(baseModel.getUpdateDate());
		yearCode.setDeleteFlag("Y");
		int result = yearCodeMapper.update(yearCode);
		return result;
	}
	@Override
	public PageModel query(PageModel pageModel) {
		Map<String,Object> map = (Map<String,Object>)pageModel.getData();
		List<YearCode> yearCodeList = yearCodeMapper.select(map);
		int sum = yearCodeMapper.count(map);
		pageModel.setResult(yearCodeList);
		pageModel.setTotalCount(sum);
		return pageModel;
	}
	@Override
	public YearCode queryOne(int id) {
		YearCode y = yearCodeMapper.selectById(id);
		return y;
	}

}

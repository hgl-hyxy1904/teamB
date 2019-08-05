package com.oracle.intelagr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.intelagr.entity.MoniPoint;
import com.oracle.intelagr.entity.YearCode;
import com.oracle.intelagr.mapper.MoniPointMapper;
import com.oracle.intelagr.service.IMoniPointService;
import com.oracle.intelagr.service.IYearCodeService;
@Service
public class MoniPointService implements IMoniPointService{
	@Autowired
	private MoniPointMapper moniPointMapper;
	@Override
	public MoniPoint getMoniPoint(String monitorPointCode) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("monitorPointCode", monitorPointCode);
		List<MoniPoint> list = moniPointMapper.select(params);
		if(list.size()>0) {
			return list.get(0);
		}
		return new MoniPoint();
	}
	@Override
	public List<MoniPoint> getMoniPointList() {
		Map<String,Object> params = new HashMap<String,Object>();
		return moniPointMapper.select(params);
	}

}

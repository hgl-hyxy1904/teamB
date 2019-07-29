package com.oracle.intelagr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.intelagr.entity.AreaDevision;
import com.oracle.intelagr.entity.Peasant;
import com.oracle.intelagr.mapper.AreaDevisionMapper;
import com.oracle.intelagr.mapper.PeasantMapper;
import com.oracle.intelagr.service.IAreaDevisionService;
import com.oracle.intelagr.service.IPeasantService;
@Service
public class AreaDevisionService implements IAreaDevisionService{
	@Autowired
	private AreaDevisionMapper areaDevisionMapper;
	@Override
	public List<AreaDevision> query(AreaDevision areaDevision) {
		String townCode = areaDevision.getTownCode();
		String countryCode = areaDevision.getCountryCode();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("townCode", townCode);
		map.put("countryCode", countryCode);
		List<AreaDevision> list = areaDevisionMapper.select(map);
		return list;
	}

}

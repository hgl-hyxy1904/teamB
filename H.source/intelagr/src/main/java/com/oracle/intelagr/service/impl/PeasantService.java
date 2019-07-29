package com.oracle.intelagr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.intelagr.entity.Peasant;
import com.oracle.intelagr.mapper.PeasantMapper;
import com.oracle.intelagr.service.IPeasantService;
@Service
public class PeasantService implements IPeasantService{
	@Autowired
	private PeasantMapper peasantMapper;
	@Override
	public List<Peasant> query(Peasant peasant) {
		String contractorId = peasant.getContractorID();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("contractorID", contractorId);
		List<Peasant> list = peasantMapper.select(map);
		return list;
	}

}

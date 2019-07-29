package com.oracle.intelagr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.intelagr.entity.GeneLandRegD;
import com.oracle.intelagr.mapper.GeneLandRegDMapper;
import com.oracle.intelagr.service.IGeneLandRegDService;
@Service
public class GeneLandRegDService implements IGeneLandRegDService{

	@Autowired
	private GeneLandRegDMapper geneLandRegDMapper;
	
	@Override
	public List<GeneLandRegD> query(GeneLandRegD geneLandRegD) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("contractorID", geneLandRegD.getContractorID());
		map.put("HID", geneLandRegD.getHid());
		List<GeneLandRegD> geneLandRegDList = geneLandRegDMapper.select(map);
		return geneLandRegDList;
	}

	@Override
	public int insert(GeneLandRegD geneLandRegD) {
		int result =  geneLandRegDMapper.insert(geneLandRegD);
		return result;
	}

	@Override
	public int update(GeneLandRegD geneLandRegD) {
		// TODO Auto-generated method stub
		return 0;
	}

}

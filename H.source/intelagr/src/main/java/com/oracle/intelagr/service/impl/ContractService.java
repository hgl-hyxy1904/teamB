package com.oracle.intelagr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.intelagr.entity.AreaDevision;
import com.oracle.intelagr.entity.Contract;
import com.oracle.intelagr.entity.Peasant;
import com.oracle.intelagr.mapper.AreaDevisionMapper;
import com.oracle.intelagr.mapper.ContractMapper;
import com.oracle.intelagr.mapper.PeasantMapper;
import com.oracle.intelagr.service.IAreaDevisionService;
import com.oracle.intelagr.service.IContractService;
import com.oracle.intelagr.service.IPeasantService;
@Service
public class ContractService implements IContractService{
	@Autowired
	private ContractMapper contractMapper;
	@Override
	public List<Contract> query(Contract contract) {
		String contractorCode = contract.getContractorCode();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("contractorCode", contractorCode);
		List<Contract> list = contractMapper.select(map);
		return list;
	}
	@Override
	public int save(Contract contract) {
		int result = contractMapper.insert(contract);
		return result;
	}

}

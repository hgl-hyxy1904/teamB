package com.oracle.intelagr.mapper;

import java.util.List;
import java.util.Map;

import com.oracle.intelagr.entity.AreaDevision;
import com.oracle.intelagr.entity.Contract;
import com.oracle.intelagr.entity.Peasant;

public interface ContractMapper {
	public List<Contract> select(Map<String,Object> map);
	public int insert(Contract contract);
}

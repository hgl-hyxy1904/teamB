package com.oracle.intelagr.service;

import java.util.List;

import com.oracle.intelagr.entity.AreaDevision;
import com.oracle.intelagr.entity.Contract;

public interface IContractService {
	public List<Contract> query(Contract contract);
	public int save(Contract contract);
}

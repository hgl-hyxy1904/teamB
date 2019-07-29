package com.oracle.intelagr.service;

import java.util.List;

import com.oracle.intelagr.entity.AreaDevision;
import com.oracle.intelagr.entity.Peasant;

public interface IAreaDevisionService {
	public List<AreaDevision> query(AreaDevision areaDevision);
}

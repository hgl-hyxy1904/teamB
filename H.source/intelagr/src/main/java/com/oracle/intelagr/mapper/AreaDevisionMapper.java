package com.oracle.intelagr.mapper;

import java.util.List;
import java.util.Map;

import com.oracle.intelagr.entity.AreaDevision;
import com.oracle.intelagr.entity.Peasant;

public interface AreaDevisionMapper {
	public List<AreaDevision> select(Map<String,Object> map);
}

package com.oracle.intelagr.mapper;

import java.util.List;
import java.util.Map;

import com.oracle.intelagr.entity.Peasant;

public interface PeasantMapper {
	public List<Peasant> select(Map<String,Object> map);
}

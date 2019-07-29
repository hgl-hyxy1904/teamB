package com.oracle.intelagr.mapper;

import java.util.List;
import java.util.Map;

import com.oracle.intelagr.entity.AirMoni;

public interface AirMoniMapper {
	public List<AirMoni> select(Map<String,Object> map);
	public int update(AirMoni airMoni);
	public int insert(AirMoni airMoni);
	public int count(Map<String, Object> map);
	public AirMoni selectById(int id);
}

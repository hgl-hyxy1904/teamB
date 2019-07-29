package com.oracle.intelagr.mapper;

import java.util.List;
import java.util.Map;

import com.oracle.intelagr.entity.WaterMoni;

public interface WaterMoniMapper {
	public List<WaterMoni> select(Map<String,Object> map);
	public int update(WaterMoni waterMoni);
	public int insert(WaterMoni waterMoni);
	public WaterMoni selectById(int id);
	public int count(Map<String, Object> map);
}

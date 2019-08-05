package com.oracle.intelagr.mapper;

import java.util.List;
import java.util.Map;

import com.oracle.intelagr.entity.MoniPoint;


public interface MoniPointMapper {
	public List<MoniPoint> select(Map<String,Object> map);
}

package com.oracle.intelagr.mapper;

import java.util.List;
import java.util.Map;

import com.oracle.intelagr.entity.GeneLandRegD;

public interface GeneLandRegDMapper {
	public int insert(GeneLandRegD geneLandRegD);
	public List<GeneLandRegD> select(Map<String,Object> map);
	public int update(GeneLandRegD geneLandRegD);
}

package com.oracle.intelagr.mapper;

import java.util.List;
import java.util.Map;

import com.oracle.intelagr.entity.GeneLandReg;

public interface GeneLandRegMapper {
	public List<GeneLandReg> select(Map<String,Object> map);
	public int insert(GeneLandReg geneLandReg);
	public int update(GeneLandReg geneLandReg);
	public GeneLandReg selectById(int id);
	public int count(Map<String,Object> map);
}

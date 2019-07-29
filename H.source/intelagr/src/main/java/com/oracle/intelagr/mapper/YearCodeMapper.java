package com.oracle.intelagr.mapper;

import java.util.List;
import java.util.Map;

import com.oracle.intelagr.entity.YearCode;

public interface YearCodeMapper {
	public List<YearCode> select(Map<String,Object> map);
	public int insert(YearCode yearCode);
	public int update(YearCode yearCode);
	public int count(Map<String, Object> map);
	public YearCode selectById(int id);
}

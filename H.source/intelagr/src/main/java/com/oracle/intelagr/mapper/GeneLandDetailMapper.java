package com.oracle.intelagr.mapper;

import java.util.List;
import java.util.Map;

import com.oracle.intelagr.entity.GeneLandDetail;

public interface GeneLandDetailMapper {
	public List<GeneLandDetail> select(Map<String,Object> map);
	public int insert(GeneLandDetail geneLandDetail);
	public int update(GeneLandDetail geneLandDetail);
}

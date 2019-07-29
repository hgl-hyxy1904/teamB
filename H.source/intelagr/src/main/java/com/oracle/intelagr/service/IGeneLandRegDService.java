package com.oracle.intelagr.service;

import java.util.List;

import com.oracle.intelagr.entity.GeneLandRegD;

public interface IGeneLandRegDService {
	public List<GeneLandRegD> query(GeneLandRegD geneLandRegD);
	public int insert(GeneLandRegD geneLandRegD);
	public int update(GeneLandRegD geneLandRegD);
}

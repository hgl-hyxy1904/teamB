package com.oracle.intelagr.service;

import java.util.List;

import com.oracle.intelagr.entity.MoniPoint;


public interface IMoniPointService {
	public MoniPoint getMoniPoint(String monitorPointCode) ;
	public List<MoniPoint> getMoniPointList();
}

package com.oracle.intelagr.service;

import javax.servlet.http.HttpServletRequest;

import com.oracle.intelagr.common.PageModel;
import com.oracle.intelagr.entity.AirMoni;
import com.oracle.intelagr.entity.WaterMoni;

public interface IWaterMoniService {
	public PageModel query(PageModel pageModel);
	public int edit(WaterMoni waterMoni,HttpServletRequest request);
	public int save(WaterMoni waterMoni,HttpServletRequest request);
	public int delete(WaterMoni waterMoni,HttpServletRequest request);
	public WaterMoni queryOne(int id);
}

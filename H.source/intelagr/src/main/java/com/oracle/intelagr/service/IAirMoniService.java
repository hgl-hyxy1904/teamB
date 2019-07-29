package com.oracle.intelagr.service;

import javax.servlet.http.HttpServletRequest;

import com.oracle.intelagr.common.PageModel;
import com.oracle.intelagr.entity.AirMoni;

public interface IAirMoniService {
	public PageModel query(PageModel pageModel);
	public int edit(AirMoni airMoni,HttpServletRequest request);
	public int save(AirMoni airMoni,HttpServletRequest request);
	public int delete(AirMoni airMoni,HttpServletRequest request);
	public AirMoni queryOne(int id);
}

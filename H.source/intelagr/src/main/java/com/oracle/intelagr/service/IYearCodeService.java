package com.oracle.intelagr.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.oracle.intelagr.common.PageModel;
import com.oracle.intelagr.entity.YearCode;

public interface IYearCodeService {
	public YearCode getYearCode(String yearCode) ;
	public List<YearCode> getYearCodeList();
	public int save(YearCode yearCode,HttpServletRequest request);
	public int delete(YearCode yearCode,HttpServletRequest request);
	public PageModel query(PageModel pageModel);
	public YearCode queryOne(int id);
}

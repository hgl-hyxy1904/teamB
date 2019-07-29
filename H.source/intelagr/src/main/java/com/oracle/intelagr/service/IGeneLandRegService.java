package com.oracle.intelagr.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.oracle.intelagr.common.PageModel;
import com.oracle.intelagr.entity.GeneLandReg;

public interface IGeneLandRegService {
	public PageModel query(PageModel pageModel);
	public int save(GeneLandReg geneLandReg,HttpServletRequest request);
	public int update(GeneLandReg geneLandReg,HttpServletRequest request);
	public GeneLandReg selectById(int id);
	public int delete(GeneLandReg geneLandReg,HttpServletRequest request);
}

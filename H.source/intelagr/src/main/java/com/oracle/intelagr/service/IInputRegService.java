package com.oracle.intelagr.service;

import java.util.List;
import java.util.Map;

import com.oracle.intelagr.entity.InputReg;

public interface IInputRegService {
	public void save(InputReg inputReg);
	public List<InputReg> query(Map map);
	/*	public void query(PageModel pageModel);*/
	public List<InputReg> select(Map map);
	public InputReg selectById (int id);
	public int delete(String id);	
	public int update(InputReg inputReg);
}

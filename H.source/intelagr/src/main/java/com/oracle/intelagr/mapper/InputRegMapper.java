package com.oracle.intelagr.mapper;


import java.util.List;
import java.util.Map;


import com.oracle.intelagr.entity.InputReg;

public interface InputRegMapper {
	public void insert(InputReg inputReg);
	public List<InputReg> select(Map<String,Object> map);
	public InputReg selectById (int id);
	public int update(InputReg inputReg);
}

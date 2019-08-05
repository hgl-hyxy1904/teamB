package com.oracle.intelagr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.intelagr.common.PageModel;
import com.oracle.intelagr.entity.InputReg;
import com.oracle.intelagr.mapper.InputRegMapper;
import com.oracle.intelagr.service.IInputRegService;

@Service
public class InputRegService implements IInputRegService {
	@Autowired
	private InputRegMapper inputRegMapper;
	@Override
	public void save(InputReg inputReg) {
		inputRegMapper.insert(inputReg);
	}
	@Override
	public List<InputReg> query(Map map) {
		//也可以对象放PageModel 然后可以调用pageMmodel.getData方法得到数据然后把数据放到map集合中   然后到查询方法 最后用pageModle.result方法把list放进去
		List<InputReg> list = inputRegMapper.select(map);
	
		return list;
	}
	
	@Override
	public InputReg selectById(int id) {
		
		return inputRegMapper.selectById(id);
	}
	@Override
	@Transactional
	public int delete(String id) {
		InputReg inputReg = new InputReg();
		inputReg.setId(Integer.parseInt(id));
		inputReg.setDeleteFlag("Y");
	return	inputRegMapper.update(inputReg);
	}
	
	@Override
	public int update(InputReg inputReg) {
		return inputRegMapper.update(inputReg);
		
	}
	@Override
	public List<InputReg> select(Map map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/*@Override
	public void query(PageModel pageModel) {
		Map<String,Object> map = (Map)pageModel.getData();
		List<InputReg> list = inputRegMapper.select(map);
		pageModel.setResult(list);
	}
*/
}

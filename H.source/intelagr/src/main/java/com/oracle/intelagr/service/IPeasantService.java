package com.oracle.intelagr.service;

import java.util.List;

import com.oracle.intelagr.entity.Peasant;

public interface IPeasantService {
	public List<Peasant> query(Peasant peasant);
}

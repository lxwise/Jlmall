package com.jl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jl.mapper.TestMapper;
import com.jl.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestMapper mapper;
	@Override
	public String queryNow() {
		
		return mapper.queryNow();
	}


}

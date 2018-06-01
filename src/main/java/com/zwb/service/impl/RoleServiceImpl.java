package com.zwb.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zwb.mapper.RoleMapper;
import com.zwb.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Resource
	private RoleMapper roleMapper;
}

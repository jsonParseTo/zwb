package com.zwb.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.zwb.service.RoleService;

@Controller
public class RoleAction {
	@Resource
	private RoleService roleService;
}

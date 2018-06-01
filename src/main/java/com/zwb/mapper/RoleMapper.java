package com.zwb.mapper;

import java.util.List;

import com.zwb.entity.Role;

public interface RoleMapper {

	List<Role> findRoleByUserId(Integer userId);

}

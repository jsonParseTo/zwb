package com.zwb.test.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.zwb.entity.User;
import com.zwb.mapper.UserMapper;

public class UserTest {
	private SqlSessionFactory sqlSessionFactory;
	@Before
	public void init(){
		try {
			InputStream in = Resources.getResourceAsStream("config/sqlMapConfig.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLogin(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User u = new User("zwb", "888888");
		User user = userMapper.login(u);
		System.out.println("Username : "+user.getUserName()+" , Password : "+user.getPassWord());
		System.out.println(user.getUserRoles().size());
	}
	
	@Test
	public void testFindUserByUsername(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserByUsername("zwb");
		System.out.println("Username : "+user.getUserName()+" , Password : "+user.getPassWord());
		System.out.println(user.getUserRoles().size());
	}
	
	
	
}

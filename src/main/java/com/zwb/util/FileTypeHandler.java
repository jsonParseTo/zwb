package com.zwb.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

public class FileTypeHandler implements TypeHandler<MultipartFile>{

	@Override
	public void setParameter(PreparedStatement ps, int i, MultipartFile parameter, JdbcType jdbcType)
			throws SQLException {
		 ps.setString(i, parameter.getOriginalFilename());
	}

	@Override
	public MultipartFile getResult(ResultSet rs, String columnName) throws SQLException {
		//FileItem fi = new 
		return null;
	}

	@Override
	public MultipartFile getResult(ResultSet rs, int columnIndex) throws SQLException {
		String name = rs.getString(columnIndex);
		File file = new File(ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/images") + File.separator + name);  
		try {
			FileInputStream input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
//		MultipartFile
		return null;
	}

	@Override
	public MultipartFile getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return null;
	}

}

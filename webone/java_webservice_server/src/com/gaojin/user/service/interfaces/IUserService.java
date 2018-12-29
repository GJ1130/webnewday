package com.gaojin.user.service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="http://bj1622.com/wsd1")
public interface IUserService
{
	//查询角色的数据
	@WebMethod 
	public String queryRoleData();
	
	//找出学生的职务和数量
	
	@WebMethod
	public String  queryRoleGroupCount();
	
	//学生所学课程的数量
	@WebMethod 
	public String queryStuAndKmCount(String name);
	

	//学生登录信息
	@WebMethod
	public String queryStu(String username,String pwd);
	//数据库表单
	@WebMethod
	public String queryMenuData();
}

package com.gaojin.user.service.interfaces;

import java.util.List;

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
	
	//性别比例
	public String queryClassToSex();
	//班级比例
	public String queryClassToPc();
	
	//年龄比例
	public String queryAgeCount();
	
	//喜欢吃什么食物
	public String queryFoodCount();
	
	//籍贯比例
	public String queryNaplaceCount();
	
	//省份比例
	public String queryProvCount();
	
    public String queryClaAgeCount();
	
	public String queryCityCount();
	
	public String queryClaProCount();
}

package com.gaojin.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gaojin.model.Menu;
import com.gaojin.model.Role;
import com.gaojin.model.StuAndRole;
import com.gaojin.user.dao.DB;
import com.gaojin.user.service.interfaces.IUserService;

@WebService(portName="userservice",
serviceName="UserServiceImpl",
targetNamespace="http://bj1622.com/wsd1",
endpointInterface="com.gaojin.user.service.interfaces.IUserService")
public class UserServiceImpl implements IUserService
{

	@Override
	public String queryRoleData()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl is queryRoleData start....");
		
		
		DB db =new DB();
		
		List<Role> lists =db.queryRoleData();
		
		System.out.println("--->"+ lists.size());
		//WebService 发布的数据应该是各个平台和语言统一能够解析的数据格式：
		//json [{},{}]
		
		JSONArray array =new JSONArray();
		for (Role role : lists) {

			JSONObject obj = new JSONObject();
			obj.put("rid", role.getRid());
			obj.put("rname", role.getRname());
			array.add(obj);
		}
		System.out.println("JSON-->" + array.toString());
		
		return array.toString();
	}
	
	@Override
	public String queryRoleGroupCount()
	{
		System.out.println("UserServiceImpl is queryRoleGroupCount start....");
		
		DB db =new DB();
		
		List<StuAndRole> lists =db.queryRoleGroupCount();
		
		System.out.println("--->"+lists.size());
		
		JSONArray array =new JSONArray();
		for(StuAndRole crole:lists)
		{
			JSONObject obj =new JSONObject();
			obj.put("rname", crole.getRname());
			obj.put("rcount", crole.getRcount());
			array.add(obj);
		}
		System.out.println("JSON-->"+array.toString());
		
		return array.toString();
		
	}

	@Override
	public String queryStuAndKmCount(String name)
	{
		// TODO Auto-generated method stub
		
		System.out.println("UserServiceImpl is queryStuAndKmCount start....");
		
		DB db =new DB();
		
		String data=db.queryStuAndKmCount(name);
		
		System.out.println("data-->"+data);
		return data;
	}

	@Override
	public String queryStu(String username, String pwd)
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl is queryStu start....");
		
		DB db =new DB();
		int count=db.queryStu(username, pwd);
		
		if(count>0)
		{
			return "登录成功";
		}
		return "登录失败，请检查";
	}

	@Override
	public String queryMenuData()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl is queryMenuData start....");
		DB db =new DB();
		
		List<Menu> lists=db.queryMenuData();
		
		String strJson=com.alibaba.fastjson.JSONArray.toJSONString(lists);
		System.out.println("strJson--->"+strJson);
		
		return strJson;
	}

}

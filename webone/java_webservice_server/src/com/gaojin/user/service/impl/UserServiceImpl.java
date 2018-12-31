package com.gaojin.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import com.gaojin.model.Classes;
import com.gaojin.model.Menu;


import com.gaojin.model.Role;
import com.gaojin.model.StuAndRole;

import com.gaojin.user.dao.DBMysql;
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
		
		
		DBMysql db =new DBMysql();
		//db = (DB) maps.get("db");
		
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
		
		DBMysql db =new DBMysql();
		
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
		
		DBMysql db =new DBMysql();
		
		String data=db.queryStuAndKmCount(name);
		
		System.out.println("data-->"+data);
		return data;
	}

	@Override
	public String queryStu(String username, String pwd)
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl is queryStu start....");
		
		DBMysql db =new DBMysql();
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
		DBMysql db =new DBMysql();
		
		List<Menu> lists=db.queryMenuData();
		
		String strJson=com.alibaba.fastjson.JSONArray.toJSONString(lists);
		System.out.println("strJson--->"+strJson);
		
		return strJson;
	}

	@Override
	public String queryClassToSex()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl is queryClassToSex start....");
		DBMysql db =new DBMysql();
		
		List<Classes> lists=db.queryClassToSex();
		String strJson=com.alibaba.fastjson.JSONArray.toJSONString(lists);
		System.out.println("List<Classes>-->strJson-->"+strJson);
		
		return strJson;
	}

	@Override
	public String queryClassToPc()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl is queryClassToPc start....");
		DBMysql db =new DBMysql();
		
		List<Classes> lists =db.queryClassToPc();
		String strJson=com.alibaba.fastjson.JSONArray.toJSONString(lists);
		System.out.println("List<Classes>-->strJson-->"+strJson);
		return strJson;
	}

	@Override
	public String queryAgeCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl is quaryAgeCount start....");
		DBMysql db =new DBMysql();
		
		List<Classes> lists =db.queryAgeCount();
		String strJson=com.alibaba.fastjson.JSONArray.toJSONString(lists);
		System.out.println("List<Classes>-->strJson-->"+strJson);
		return strJson;
	}

	@Override
	public String queryNaplaceCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl is queryNaplaceCount start....");
		DBMysql db =new DBMysql();
		
		List<Classes> lists =db.queryNaplaceCount();
		
		String strJson=com.alibaba.fastjson.JSONArray.toJSONString(lists);
		System.out.println("List<Classes>-->strJson-->"+strJson);
		return strJson;
	}

	@Override
	public String queryProvCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl is queryProvCount start....");
		DBMysql db =new DBMysql();
		
		List<Classes> lists =db.queryProvCount();
		
		String strJson=com.alibaba.fastjson.JSONArray.toJSONString(lists);
		System.out.println("List<Classes>-->strJson-->"+strJson);
		return strJson;
	}

	@Override
	public String queryClaAgeCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl is queryClaAgeCount start....");
		DBMysql db =new DBMysql();
		
		List<Classes> lists =db.queryClaAgeCount();
		String strJson=com.alibaba.fastjson.JSONArray.toJSONString(lists);
		System.out.println("List<Classes>-->strJson-->"+strJson);
		return strJson;
	}

	@Override
	public String queryCityCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl is queryCityCount start....");
		DBMysql db =new DBMysql();
		
		List<Classes> lists =db.queryCityCount();
		
		String strJson=com.alibaba.fastjson.JSONArray.toJSONString(lists);
		System.out.println("List<Classes>-->strJson-->"+strJson);
		return strJson;
	}

	@Override
	public String queryClaProCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl is queryClaProCount start....");
		DBMysql db =new DBMysql();
		
		List<Classes> lists =db.queryClaProCount();
		
		String strJson=com.alibaba.fastjson.JSONArray.toJSONString(lists);
		System.out.println("List<Classes>-->strJson-->"+strJson);
		return strJson;
	}
	
	

	@Override
	public String queryFoodCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl is queryFoodCount start....");
		DBMysql db =new DBMysql();
		
		List<Classes> lists=db.queryFoodCount();
		String strJson=com.alibaba.fastjson.JSONArray.toJSONString(lists);
		System.out.println("List<Peop>-->strJson-->"+strJson);
		return strJson;
	}

}

package com.gaojin.user.dao;

import java.util.List;

import com.gaojin.model.Menu;

public interface DB
{
	public List queryRoleData();
	
	public List queryRoleGroupCount();
	
	public String queryStuAndKmCount(String stuName);
	
	public int queryStu(String name,String pwd);
	
	public List<Menu> queryMenuData();
	
	public List queryClassToSex();
	
	public List queryClassToPc();
	
	public List queryAgeCount();
	
	public List queryFoodCount();
	
	public List queryNaplaceCount();
	
	public List queryProvCount();
	
	public List queryClaAgeCount();
	
	public List queryCityCount();
	
	public List queryClaProCount();
}

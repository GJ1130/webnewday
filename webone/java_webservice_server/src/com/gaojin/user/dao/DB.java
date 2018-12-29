package com.gaojin.user.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gaojin.file.FilePropertiesUtils;
import com.gaojin.model.Menu;
import com.gaojin.model.Role;
import com.gaojin.model.StuAndRole;

public class DB
{
	static String urlimg="";
	static
	{
		urlimg=FilePropertiesUtils.getImageUtilPath();
	}
	private Connection conn;
	
	public DB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bj1622","root","123456");
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public List queryRoleData()
	{
		String sql="SELECT * FROM t_role";
		List<Role> lists =new ArrayList<Role>();
		try
		{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			ResultSet rs =pstmt.executeQuery();
			
			while(rs.next())
			{
				Role r =new Role();
				r.setRid(rs.getInt(1));
				r.setRname(rs.getString(2));
				
				lists.add(r);
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(null !=conn)
			{
				try
				{
					conn.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return lists;
		
	}
	public List queryRoleGroupCount()
	{
		String sql="SELECT  rname,COUNT(sjob)    FROM  t_students " +
				" RIGHT  JOIN  t_role ON sjob=rid  GROUP BY  rname";
		
		List<StuAndRole> lists =new ArrayList<StuAndRole>();
		
		try
		{
			PreparedStatement pstmt =conn.prepareStatement(sql);
			
			ResultSet rs =pstmt.executeQuery();
			
			while(rs.next())
			{
				StuAndRole crole=new StuAndRole();
				crole.setRname(rs.getString(1));
				crole.setRcount(rs.getInt(2));
				
				lists.add(crole);
				
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try
				{
					conn.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return lists;
	}
	
	public String queryStuAndKmCount(String stuName)
	{
		String sql="SELECT COUNT(kid),sname  FROM (SELECT   * FROM  t_students  WHERE  sname=?) " +
				"tmp INNER  JOIN t_score  ON tmp.sid=t_score.sid  GROUP  BY sname";
		
		String data="";
		
		
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stuName);
			ResultSet rs =pstmt.executeQuery();
			
			while(rs.next())
			{
				data=rs.getInt(1)+","+rs.getString(2);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try
				{
					conn.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return data;
	}
	public int queryStu(String name,String pwd)
	{
		String sql="SELECT COUNT(*) FROM t_students WHERE sname=? AND spwd=?";
		
		PreparedStatement pstmt=null;
		try
		{
			 pstmt =conn.prepareStatement(sql);
			 pstmt.setString(1, name);
			 pstmt.setString(2,pwd);
			 ResultSet rs =pstmt.executeQuery();
			 
			 while(rs.next())
			 {
				return rs.getInt(1);
				 
			 }
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try
				{
					conn.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return 0;
		
	}
	public List<Menu> queryMenuData()
	{
		String sql="SELECT * FROM t_classes";
		List<Menu> listMenu =new ArrayList<Menu>();
		
		try
		{
			PreparedStatement pstmt=conn.prepareStatement(sql);
			ResultSet rs =pstmt.executeQuery();
			
			while(rs.next())
			{
				Menu menu =new Menu();
				menu.setCid(rs.getInt(1));
				menu.setCname(rs.getString(2));
				menu.setCurl(rs.getString(3));
				menu.setImgpath(urlimg+rs.getString(4));
				listMenu.add(menu);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(null!=conn)
			{
				try
				{
					conn.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return listMenu;
		
	}

}

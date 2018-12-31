package com.gaojin.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gaojin.file.FilePropertiesUtils;

import com.gaojin.model.Classes;
import com.gaojin.model.Menu;

import com.gaojin.model.Role;
import com.gaojin.model.StuAndRole;

public class DBMysql implements DB
{
	static String urlimg="";
	static
	{
		urlimg=FilePropertiesUtils.getImageUtilPath();
	}
	private Connection conn;
	
	public DBMysql()
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
	@Override
	public List queryClassToSex()
	{
		// TODO Auto-generated method stub
		
		System.out.println("DBMysql is queryClassToSex start....");
		
		String sql="SELECT psex,COUNT(*) FROM t_people GROUP BY psex";
		
		List<Classes> listClass=new ArrayList<Classes>();
		
		try
		{
			PreparedStatement pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				Classes cs=new Classes();
				cs.setPsex(rs.getString(1));
				cs.setCount(rs.getDouble(2));
				
				listClass.add(cs);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
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
		
		
		return listClass;
	}
	@Override
	public List queryClassToPc()
	{
		// TODO Auto-generated method stub
		System.out.println("DBMysql is queryClassToPc start....");
		String sql="SELECT pclass,COUNT(*) FROM t_people GROUP BY pclass";
		
		List<Classes> listClass=new ArrayList<Classes>();
		
		try
		{
			PreparedStatement pstmt=conn.prepareStatement(sql);
			
			ResultSet rs =pstmt.executeQuery();
			
			while(rs.next())
			{
				Classes cs=new Classes();
				cs.setPclass(rs.getString(1));
				cs.setCount(rs.getDouble(2));
				
				listClass.add(cs);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
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
		return listClass;
	}
	@Override
	public List queryAgeCount()
	{
		// TODO Auto-generated method stub
		System.out.println("DBMysql is quaryAgeCount start....");
		String sql="SELECT SUBSTR(NOW(),1,4)-SUBSTR(pbirthday,1,4) AS 'age'," +
				"COUNT(*) AS '数量' FROM t_people GROUP BY SUBSTR(NOW(),1,4)-SUBSTR(pbirthday,1,4)";
		
		List<Classes> listClass=new ArrayList<Classes>();
		
		
		try
		{
			PreparedStatement pstmt=conn.prepareStatement(sql);
			
			ResultSet rs =pstmt.executeQuery();
			
			while(rs.next())
			{
				Classes cs=new Classes();
				cs.setAge(rs.getString(1));
				cs.setCount(rs.getDouble(2));
				
				listClass.add(cs);
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
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
		return listClass;
	}
	
	//未完成的
	@Override
	public List queryFoodCount()
	{
		// TODO Auto-generated method stub
		System.out.println("DBMysql is queryFoodCount start....");
	String sql="SELECT pclass,COUNT(*) FROM t_people GROUP BY pclass";
	
	List<Classes> listClass=new ArrayList<Classes>();
	
	try
	{
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		ResultSet rs =pstmt.executeQuery();
		
		while(rs.next())
		{
			Classes cs=new Classes();
			cs.setPclass(rs.getString(1));
			cs.setCount(rs.getDouble(2));
			
			listClass.add(cs);
		}
	} catch (SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally
	{
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
	return listClass;
}
	
	public List queryNaplaceCount()
	{
		// TODO Auto-generated method stub
		
		System.out.println("DBMysql is queryNaplaceCount start....");
		String sql="SELECT SUBSTR(paddress,3,3) AS 'city'," +
				"FORMAT(COUNT(*)/(SELECT COUNT(*) FROM t_people WHERE paddress LIKE '江苏%'),2) AS '居住地比例'" +
				"FROM t_people WHERE t_people.`paddress` LIKE '江苏%' GROUP BY SUBSTR(paddress,3,3)";
		
		List<Classes> listClass=new ArrayList<Classes>();
		
		
		try
		{
			PreparedStatement pstmt=conn.prepareStatement(sql);
			
			ResultSet rs =pstmt.executeQuery();
			
			while(rs.next())
			{
				Classes cs=new Classes();
				cs.setCity(rs.getString(1));
				cs.setCount(rs.getDouble(2));
				
				listClass.add(cs);
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
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
		return listClass;
	}
	@Override
	public List queryProvCount()
	{
		// TODO Auto-generated method stub

		System.out.println("DBMysql is queryProvCount start....");
		String sql="SELECT SUBSTR(paddress,1,2) AS 'prov'," +
				"FORMAT((COUNT(*)/(SELECT COUNT(*) FROM t_people)),2) AS '省份比例'" +
				"FROM t_people GROUP BY SUBSTR(paddress,1,2)";
		
		List<Classes> listClass=new ArrayList<Classes>();
		
		
		try
		{
			PreparedStatement pstmt=conn.prepareStatement(sql);
			
			ResultSet rs =pstmt.executeQuery();
			
			while(rs.next())
			{
				Classes cs=new Classes();
			
				cs.setProv(rs.getString(1));
				cs.setCount(rs.getDouble(2));
				
				listClass.add(cs);
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
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
		return listClass;
	}
	@Override
	public List queryClaAgeCount()
	{
		// TODO Auto-generated method stub
		System.out.println("DBMysql is queryClaAgeCount start....");
		String sql="SELECT SUBSTR(NOW(),1,4)-SUBSTR(pbirthday,1,4) AS 'age'," +
				"COUNT(*) AS '数量' FROM t_people GROUP BY SUBSTR(NOW(),1,4)-SUBSTR(pbirthday,1,4)";
		
		List<Classes> listClass=new ArrayList<Classes>();
		
		
		try
		{
			PreparedStatement pstmt=conn.prepareStatement(sql);
			
			ResultSet rs =pstmt.executeQuery();
			
			while(rs.next())
			{
				Classes cs=new Classes();
				cs.setAge(rs.getString(1));
				cs.setCount(rs.getDouble(2));
				
				listClass.add(cs);
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
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
		return listClass;
	}
	@Override
	public List queryCityCount()
	{
		// TODO Auto-generated method stub
		System.out.println("DBMysql is queryCityCount start....");
		String sql="SELECT SUBSTR(paddress,3,3) AS 'city'," +
				"FORMAT(COUNT(*)/(SELECT COUNT(*) FROM t_people WHERE paddress LIKE '江苏%'),2) AS '居住地比例'" +
				"FROM t_people WHERE t_people.`paddress` LIKE '江苏%' GROUP BY SUBSTR(paddress,3,3)";
		
		List<Classes> listClass=new ArrayList<Classes>();
		
		
		try
		{
			PreparedStatement pstmt=conn.prepareStatement(sql);
			
			ResultSet rs =pstmt.executeQuery();
			
			while(rs.next())
			{
				Classes cs=new Classes();
				cs.setCity(rs.getString(1));
				cs.setCount(rs.getDouble(2));
				
				listClass.add(cs);
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
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
		return listClass;
	}
	@Override
	public List queryClaProCount()
	{
		// TODO Auto-generated method stub
		System.out.println("DBMysql is queryClaProCount start....");
		String sql="SELECT SUBSTR(paddress,1,2) AS 'prov'," +
				"FORMAT((COUNT(*)/(SELECT COUNT(*) FROM t_people)),2) AS '省份比例'" +
				"FROM t_people GROUP BY SUBSTR(paddress,1,2)";
		
		List<Classes> listClass=new ArrayList<Classes>();
		
		
		try
		{
			PreparedStatement pstmt=conn.prepareStatement(sql);
			
			ResultSet rs =pstmt.executeQuery();
			
			while(rs.next())
			{
				Classes cs=new Classes();
			
				cs.setProv(rs.getString(1));
				cs.setCount(rs.getDouble(2));
				
				listClass.add(cs);
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
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
		return listClass;
	}
	

}

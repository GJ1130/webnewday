package com.gaojin.model;

public class Menu
{
	private int cid;
	public int getCid()
	{
		return cid;
	}
	public void setCid(int cid)
	{
		this.cid = cid;
	}
	public String getCname()
	{
		return cname;
	}
	public void setCname(String cname)
	{
		this.cname = cname;
	}
	public String getCurl()
	{
		return curl;
	}
	public void setCurl(String curl)
	{
		this.curl = curl;
	}
	public String getImgpath()
	{
		return imgpath;
	}
	public void setImgpath(String imgpath)
	{
		this.imgpath = imgpath;
	}
	private String cname;
	private String curl;
	private String imgpath;

}

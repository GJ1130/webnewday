package com.gaojin.user.service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="http://bj1622.com/wsd1")
public interface IUserService
{
	//��ѯ��ɫ������
	@WebMethod 
	public String queryRoleData();
	
	//�ҳ�ѧ����ְ�������
	
	@WebMethod
	public String  queryRoleGroupCount();
	
	//ѧ����ѧ�γ̵�����
	@WebMethod 
	public String queryStuAndKmCount(String name);
	

	//ѧ����¼��Ϣ
	@WebMethod
	public String queryStu(String username,String pwd);
	//���ݿ��
	@WebMethod
	public String queryMenuData();
}

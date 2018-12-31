package com.gaojin.user.service.interfaces;

import java.util.List;

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
	
	//�Ա����
	public String queryClassToSex();
	//�༶����
	public String queryClassToPc();
	
	//�������
	public String queryAgeCount();
	
	//ϲ����ʲôʳ��
	public String queryFoodCount();
	
	//�������
	public String queryNaplaceCount();
	
	//ʡ�ݱ���
	public String queryProvCount();
	
    public String queryClaAgeCount();
	
	public String queryCityCount();
	
	public String queryClaProCount();
}

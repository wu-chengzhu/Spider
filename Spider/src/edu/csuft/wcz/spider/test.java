package edu.csuft.wcz.spider;

public class test {
public static void main(String[] args) {
	String info= "����: �ǵ��������� Adam Elliot ����: ���ݡ������� Toni Collette / ����... 2009 / �Ĵ����� / ���� ����  ";
	String s0[]=info.split("\\/");
	String yearS[]=s0[1].split("\\...");  //����ݵ��ַ���
	String year=yearS[1]; //����ַ���
	String country=s0[2];
	String type=s0[3];

	
	for(int i=0;i<s0.length;i++) {
		System.out.println(s0[i]);
	}
}
}

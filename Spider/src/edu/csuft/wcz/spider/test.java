package edu.csuft.wcz.spider;

public class test {
public static void main(String[] args) {
	String info= "导演: 亚当·艾略特 Adam Elliot 主演: 托妮·科莱特 Toni Collette / 菲利... 2009 / 澳大利亚 / 剧情 动画  ";
	String s0[]=info.split("\\/");
	String yearS[]=s0[1].split("\\...");  //含年份的字符串
	String year=yearS[1]; //年份字符串
	String country=s0[2];
	String type=s0[3];

	
	for(int i=0;i<s0.length;i++) {
		System.out.println(s0[i]);
	}
}
}

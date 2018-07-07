package edu.csuft.wcz.spider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * ����
 * 
 * @author 67340
 * @since 2018/7/3
 */
public class Spider implements Runnable {

	String url;
	private List<Film> filmList = new ArrayList<>(); // ��̬����

	/**
	 * ����Ĺ��췽��
	 * 
	 * @param url
	 */
	public Spider(String url, List<Film> films) {
		this.url = url;
		this.filmList = films;
	}
      @Override
	public void run() {   //�̵߳�run����
		try {

			Document doc = Jsoup.connect(url).timeout(10000).get();//  ����������
			Elements items = doc.select(".grid_view .item");      //�ڽ��������������Ԫ��

			for (Element item : items) {
				Film film = new Film();
				String id = item.select("em").text();
				String path = item.select(".pic img").attr("src");
				String title = item.select(".title").text();
				String quote = item.select(".quote .inq").text();
				String rating = item.select(".star .rating_num").text(); // ����

				String info = item.select(".bd p").first().text(); // ȡ����Ӱ��Ϣ������Ŀ
				
				String s0[] = info.split("\\/");
				String str1[] = s0[0].split("\\: "); // �ֳ����ݺ�����
				film.setDirector(str1[1].replaceAll("����", "")); // ���õ���
				if (str1.length < 3) // ������Ա
				{
					film.setActor(null);
				} else {
					film.setActor(str1[2].split("\\...")[0]);
				}

				String yearS[] = s0[s0.length - 3].split("\\ "); // ����ݵ��ַ���
				try {
					film.setYear(Integer.parseInt(yearS[yearS.length - 1].trim())); // �������
				} catch (NumberFormatException e) {
					String str[] = yearS[yearS.length - 1].split("\\("); // ����ĳ�������ʽ����ݴ���
					film.setYear(Integer.parseInt(str[0]));
				}
				film.setCountry(s0[s0.length - 2]); // ���ù���
				film.setType(s0[s0.length - 1]);// ���� ����

				String ratingP = item.select(".star span").last().text(); // ��������
				String s1[] = ratingP.split("\\��"); // ������ֻȡ������
				film.setId(Integer.parseInt(id));
				film.setTitle(title);
				film.setPath(path);
				film.setQuote(quote);
				film.setRating(Double.parseDouble(rating)); // ����

				film.setStar(Integer.parseInt(s1[0]));

				filmList.add(film);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}

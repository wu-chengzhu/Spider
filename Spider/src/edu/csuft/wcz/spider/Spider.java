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
 * 爬虫
 * 
 * @author 67340
 * @since 2018/7/3
 */
public class Spider implements Runnable {

	String url;
	private List<Film> filmList = new ArrayList<>(); // 动态数组

	/**
	 * 爬虫的构造方法
	 * 
	 * @param url
	 */
	public Spider(String url, List<Film> films) {
		this.url = url;
		this.filmList = films;
	}
      @Override
	public void run() {   //线程的run方法
		try {

			Document doc = Jsoup.connect(url).timeout(10000).get();//  解析请求结果
			Elements items = doc.select(".grid_view .item");      //在结果中找这个类里的元素

			for (Element item : items) {
				Film film = new Film();
				String id = item.select("em").text();
				String path = item.select(".pic img").attr("src");
				String title = item.select(".title").text();
				String quote = item.select(".quote .inq").text();
				String rating = item.select(".star .rating_num").text(); // 评分

				String info = item.select(".bd p").first().text(); // 取出电影信息具体项目
				
				String s0[] = info.split("\\/");
				String str1[] = s0[0].split("\\: "); // 分出导演和主演
				film.setDirector(str1[1].replaceAll("主演", "")); // 设置导演
				if (str1.length < 3) // 设置演员
				{
					film.setActor(null);
				} else {
					film.setActor(str1[2].split("\\...")[0]);
				}

				String yearS[] = s0[s0.length - 3].split("\\ "); // 含年份的字符串
				try {
					film.setYear(Integer.parseInt(yearS[yearS.length - 1].trim())); // 设置年份
				} catch (NumberFormatException e) {
					String str[] = yearS[yearS.length - 1].split("\\("); // 对于某个特殊格式的年份处理
					film.setYear(Integer.parseInt(str[0]));
				}
				film.setCountry(s0[s0.length - 2]); // 设置国家
				film.setType(s0[s0.length - 1]);// 设置 类型

				String ratingP = item.select(".star span").last().text(); // 评价人数
				String s1[] = ratingP.split("\\人"); // 把评价只取出人数
				film.setId(Integer.parseInt(id));
				film.setTitle(title);
				film.setPath(path);
				film.setQuote(quote);
				film.setRating(Double.parseDouble(rating)); // 评分

				film.setStar(Integer.parseInt(s1[0]));

				filmList.add(film);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}

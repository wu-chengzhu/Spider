package edu.csuft.wcz.spider;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.google.gson.Gson;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 爬虫程序的启动器
 * 
 * @author 67340
 *
 */
public class App {

	public static void main(String[] args) {

		List<Film> filmList = Collections.synchronizedList(new LinkedList<>()); // 动态数组 并让线程池同步

		ExecutorService pool = Executors.newFixedThreadPool(10); // 容量固定10个
		int j;
		try {
			for (int i = 0; i < 10; i++) {
				j = 0 + i * 25;
				pool.execute(new Spider("https://movie.douban.com/top250?start=" + j + "&filter=", filmList)); // 让线程池执行不同网页的爬取
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.shutdown(); // 关闭线程池
		}

		while (true) {

			if (pool.isTerminated() == true) { // 当所有任务都关了就开始写数据
				writeData(filmList);
				break;
			} else { // 否则睡一秒 不要一直执行
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void writeData(List<Film> films) { // 写入数据方法 存到数据库 下图片

		Collections.sort(films, new Film()); // 对动态数组里的数排序 compartor接口 自己定义compare排序方法

		 for (Film film : films) { //输出看一下
		 System.out.println(film);
		 }

		// 存储到数据库

		// 建立连接
		SqlSession session = null;
		try {
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(new FileReader("config.xml")); // 工厂构造器
																											// 先建构造器

			session = factory.openSession(); // 建立会话

			// 获得了接口的具体实现
			FilmMapper mapper = session.getMapper(FilmMapper.class); // 得到映射

			for (Film f : films) { // 通过映射往数据库中插入数据
				mapper.insert(f);
			}
			session.commit(); // 事物提交
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close(); // 最后关闭数据库连接
			System.out.println("存储成功");
		}

		ExecutorService pool = Executors.newFixedThreadPool(8); // 开启八个线程 下载图片
		for (Film film : films) { // 遍历数组
			pool.execute(new ImgLoader(film));
		}
		pool.shutdown();

	}

}

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
 * ��������������
 * 
 * @author 67340
 *
 */
public class App {

	public static void main(String[] args) {

		List<Film> filmList = Collections.synchronizedList(new LinkedList<>()); // ��̬���� �����̳߳�ͬ��

		ExecutorService pool = Executors.newFixedThreadPool(10); // �����̶�10��
		int j;
		try {
			for (int i = 0; i < 10; i++) {
				j = 0 + i * 25;
				pool.execute(new Spider("https://movie.douban.com/top250?start=" + j + "&filter=", filmList)); // ���̳߳�ִ�в�ͬ��ҳ����ȡ
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.shutdown(); // �ر��̳߳�
		}

		while (true) {

			if (pool.isTerminated() == true) { // ���������񶼹��˾Ϳ�ʼд����
				writeData(filmList);
				break;
			} else { // ����˯һ�� ��Ҫһֱִ��
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void writeData(List<Film> films) { // д�����ݷ��� �浽���ݿ� ��ͼƬ

		Collections.sort(films, new Film()); // �Զ�̬������������� compartor�ӿ� �Լ�����compare���򷽷�

		 for (Film film : films) { //�����һ��
		 System.out.println(film);
		 }

		// �洢�����ݿ�

		// ��������
		SqlSession session = null;
		try {
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(new FileReader("config.xml")); // ����������
																											// �Ƚ�������

			session = factory.openSession(); // �����Ự

			// ����˽ӿڵľ���ʵ��
			FilmMapper mapper = session.getMapper(FilmMapper.class); // �õ�ӳ��

			for (Film f : films) { // ͨ��ӳ�������ݿ��в�������
				mapper.insert(f);
			}
			session.commit(); // �����ύ
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close(); // ���ر����ݿ�����
			System.out.println("�洢�ɹ�");
		}

		ExecutorService pool = Executors.newFixedThreadPool(8); // �����˸��߳� ����ͼƬ
		for (Film film : films) { // ��������
			pool.execute(new ImgLoader(film));
		}
		pool.shutdown();

	}

}

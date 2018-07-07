package edu.csuft.wcz.spider;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;




public class ImgLoader implements Runnable {

	private Film film;

	public ImgLoader(Film film) {//�������̬������·����ͼƬ
		this.film = film;
	}

	@Override
	public void run() {
		File path = new File("pic");   //����һ���ļ�����
		if (!path.exists())  //���·��������
			path.mkdir();    // �������Ŀ¼
		String name = String.format("%03d_%s.jpg", film.getId(), film.getTitle().split(" ")[0]);  //������ID�ţ�3Ϊ�������ӱ���
		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(path, name)));) {

			byte[] data = new OkHttpClient.Builder()
					.connectTimeout(60, TimeUnit.SECONDS)   //����ʱ��60ms
					.readTimeout(60, TimeUnit.SECONDS)
					.writeTimeout(60, TimeUnit.SECONDS)
					.build()
					.newCall(new Request.Builder().url(film.getPath()).build()).execute().body().bytes();

			out.write(data);  //
			out.close();
			System.out.println(Thread.currentThread().getName() + " ���� " + name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

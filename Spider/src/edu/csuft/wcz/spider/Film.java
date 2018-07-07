package edu.csuft.wcz.spider;

import java.util.Comparator;

/**
 * ӰƬ����Ϣ��ʵ���ࣩ
 * 
 * @author 67340
 *
 */
public class Film implements Comparator<Film> {

	/**
	 * ����
	 */
	private int id;
	/**
	 * Ƭ��
	 */
	private String title;
	/**
	 * ����
	 */
	private String director;
	/**
	 * ����
	 */
	private String actor;
	/**
	 * ��Ӱ���
	 */
	private int year;
	/**
	 * ��Ӱ����
	 */
	private String country;
	/**
	 * ��Ӱ����
	 */
	private String type;
	/**
	 * ��������
	 */
	private int ratingP;
	/**
	 * ����
	 */
	private double rating;
	/**
	 * ����·��
	 */
	private String path;
	/**
	 * ��Ҫ
	 */
	private String quote;

	@Override
	public String toString() {
		return "Film [id=" + id + "," + " title=" + title + "," + " director=" + director + ", " + "actor=" + actor
				+ "," + "year=" + year + "," + "country=" + country + "," + "type=" + type + "," + "ratingP=" + ratingP
				+ "," + " rating=" + rating + ", path=" + path + ", quote=" + quote + "]";
	}

	public Film() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public double getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public int getRatingP() {
		return ratingP;
	}

	public void setRatingP(int ratingP) {
		this.ratingP = ratingP;
	}

	public double getStar() {
		return ratingP;
	}

	public void setStar(int star) {
		this.ratingP = star;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String poster) {
		this.path = poster;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	@Override
	public int compare(Film a, Film b) {  //�������
		if (a.id > b.id) {
			return 1;
		} else {
			return -1;
		}
	}

}

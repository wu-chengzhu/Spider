package edu.csuft.wcz.spider;

import java.util.Comparator;

/**
 * 影片的信息（实体类）
 * 
 * @author 67340
 *
 */
public class Film implements Comparator<Film> {

	/**
	 * 排名
	 */
	private int id;
	/**
	 * 片名
	 */
	private String title;
	/**
	 * 导演
	 */
	private String director;
	/**
	 * 主演
	 */
	private String actor;
	/**
	 * 电影年份
	 */
	private int year;
	/**
	 * 电影国家
	 */
	private String country;
	/**
	 * 电影类型
	 */
	private String type;
	/**
	 * 评价人数
	 */
	private int ratingP;
	/**
	 * 评分
	 */
	private double rating;
	/**
	 * 海报路径
	 */
	private String path;
	/**
	 * 概要
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
	public int compare(Film a, Film b) {  //排序规则
		if (a.id > b.id) {
			return 1;
		} else {
			return -1;
		}
	}

}

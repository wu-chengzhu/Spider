package edu.csuft.wcz.spider;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author 67340
 *
 */
// ע�� ӳ����
@Mapper
public interface FilmMapper {
	@Insert("insert into films(id,title,director,actor,year,country,type,ratingP,rating,path,quote) values(#{id},#{title},#{director},#{actor},#{year},#{country},#{type},#{ratingP},#{rating},#{path},#{quote})")
	void insert(Film m);

	// ���䣨��ħ���� ����Ȥ����ȥ��
	@Select("select * from films where id=#{pk}") // ������һ������
	Film load(int pk); // pk������

	@Select("select * from films")
	List<Film> find();
}

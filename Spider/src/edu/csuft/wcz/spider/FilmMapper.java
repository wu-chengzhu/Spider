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
// 注解 映射器
@Mapper
public interface FilmMapper {
	@Insert("insert into films(id,title,director,actor,year,country,type,ratingP,rating,path,quote) values(#{id},#{title},#{director},#{actor},#{year},#{country},#{type},#{ratingP},#{rating},#{path},#{quote})")
	void insert(Film m);

	// 反射（黑魔法） 有兴趣可以去看
	@Select("select * from films where id=#{pk}") // 后面是一个参数
	Film load(int pk); // pk是主键

	@Select("select * from films")
	List<Film> find();
}

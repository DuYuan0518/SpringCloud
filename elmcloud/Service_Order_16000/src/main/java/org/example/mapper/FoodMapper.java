package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.Food;

import java.util.List;

@Mapper
public interface FoodMapper {

	@Select("select * from food where businessId=#{businessId} order by foodId")
	public List<Food> listFoodByBusinessId(Integer businessId);
	
	@Select("select * from food where foodId=#{foodId}")
	public Food getFoodById(Integer foodId);
}

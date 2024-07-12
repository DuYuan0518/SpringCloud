package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.OrderDetailet;

import java.util.List;

@Mapper
public interface OrderDetailetMapper {

	public int saveOrderDetailetBatch(List<OrderDetailet> list);
	
	public List<OrderDetailet> listOrderDetailetByOrderId(Integer orderOd);
}

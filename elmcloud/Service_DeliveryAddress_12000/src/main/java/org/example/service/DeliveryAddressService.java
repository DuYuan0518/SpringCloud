package org.example.service;

import java.util.List;

import org.example.entity.DeliveryAddress;

public interface DeliveryAddressService {

	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId);
	
	public DeliveryAddress getDeliveryAddressById(Integer daId);
	public int saveDeliveryAddress(DeliveryAddress deliveryAddress);
	public int updateDeliveryAddress(DeliveryAddress deliveryAddress);
	public int removeDeliveryAddress(Integer daId);
}

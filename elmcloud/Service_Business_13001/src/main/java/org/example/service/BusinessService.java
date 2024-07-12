package org.example.service;

import org.example.entity.Business;

import java.util.List;

public interface BusinessService {

	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);
	public Business getBusinessById(Integer businessId);
}

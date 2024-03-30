package com.sashikanta.ims.service;

import java.util.List;

import com.sashikanta.ims.entities.Sale;

public interface SaleService {
	
	//save sale
	Sale saveSale(Sale sale);
	
	//update sale
	Sale updateSale(Sale sale);
	
	//delete sale
	void deleteSale(Sale sale);
	
	//get sale
	Sale gertSaleById(long cId);
	
	//list of sales
	List<Sale> getAllSales();

}

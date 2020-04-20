package com.jl.order.pojo;

import java.io.Serializable;
import java.util.List;

import com.jl.pojo.TbOrder;
import com.jl.pojo.TbOrderItem;
import com.jl.pojo.TbOrderShipping;

public class OrderInfo extends TbOrder implements Serializable{
	
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
}

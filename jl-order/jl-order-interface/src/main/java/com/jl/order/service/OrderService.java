package com.jl.order.service;

import com.jl.common.pojo.TaotaoResult;
import com.jl.order.pojo.OrderInfo;

public interface OrderService {
	
	TaotaoResult createOrder(OrderInfo orderInfo);
}

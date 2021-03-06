package com.jl.order.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jl.common.pojo.TaotaoResult;
import com.jl.mapper.TbOrderItemMapper;
import com.jl.mapper.TbOrderMapper;
import com.jl.mapper.TbOrderShippingMapper;
import com.jl.order.jedis.JedisClient;
import com.jl.order.pojo.OrderInfo;
import com.jl.order.service.OrderService;
import com.jl.pojo.TbOrderItem;
import com.jl.pojo.TbOrderShipping;

/**
 * 订单处理
 * @author liuxin
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${ORDER_ID_GEN_KEY}")
	private String ORDER_ID_GEN_KEY;
	@Value("${ORDER_ID_BEGIN_VALUE}")
	private String ORDER_ID_BEGIN_VALUE;
	@Value("${ORDER_ITEM_ID_GEN_KEY}")
	private String ORDER_ITEM_ID_GEN_KEY;
	@Override
	public TaotaoResult createOrder(OrderInfo orderInfo) {
		
		//判断有无订单号 如果没有就设置一个初始值
		if (!jedisClient.exists(ORDER_ID_GEN_KEY)) {
			//设置初始值
			jedisClient.set(ORDER_ID_GEN_KEY, ORDER_ID_BEGIN_VALUE);
		}
		// 生成订单号，可以使用redis的incr生成 得到一个字符串
		String orderId = jedisClient.incr("ORDER_ID_GEN").toString();
		//向订单表插入数据，需要齐全pojo属性  orderinfo继承了tborder
		orderInfo.setOrderId(orderId);
		//免邮费
		orderInfo.setPostFee("0");
		//1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭',
		orderInfo.setStatus(1);
		//订单创建时间
		orderInfo.setCreateTime(new Date());
		orderInfo.setUpdateTime(new Date());
		//向订单表插入数据，
		orderMapper.insert(orderInfo);
		//向订单明细表插入数据  从orderinfo中取出来 
		List<TbOrderItem> orderItems = orderInfo.getOrderItems();
		//做循环遍历取tbOrderItem里每一个数据
		for (TbOrderItem tbOrderItem : orderItems) {
			//获得明细主键 
			String oid = jedisClient.incr(ORDER_ITEM_ID_GEN_KEY).toString();
			//补全属性
			tbOrderItem.setId(oid);
			tbOrderItem.setOrderId(orderId);
			//向订单明细表插入数据
			orderItemMapper.insert(tbOrderItem);
		}
		//向订单物流部插入数据
		//取出物流信息
		TbOrderShipping orderShipping = orderInfo.getOrderShipping();
		//补全属性
		orderShipping.setOrderId(orderId);;
		orderShipping.setCreated(new Date());
		orderShipping.setUpdated(new Date());
		//向订单物流部插入数据
		orderShippingMapper.insert(orderShipping);
		//返回订单号
		return TaotaoResult.ok(orderId);
	}

}

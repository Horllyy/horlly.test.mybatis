package My.mapper;

import My.po.Orders;
import My.po.OrdersCustom;
import My.po.User;

import java.util.List;

/**
 * 订单mapper
 */

public interface OrdersMapperCustom {

    //查询订单关联查询用户
    public List<OrdersCustom> findOrdersUser() throws Exception;

    //查询订单关联查询用户及订单明细
    public List<Orders> findOrdersUserOrderDetail() throws Exception;


    //查询用户购买的商品信息
    public List<User> findUserOrdersOrderDetailItems() throws Exception;

    //查询订单关联查询用户（用户信息延迟加载）
    public List<Orders> findOrderUserLazyLoading() throws Exception;
}

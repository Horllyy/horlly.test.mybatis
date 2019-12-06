import My.mapper.OrdersMapperCustom;
import My.po.Orders;
import My.po.OrdersCustom;
import My.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class OrdersMapperCustomTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception{
        String resource="SqlMapConfig.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Test
    public void findOrdersUser() throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();

        OrdersMapperCustom ordersMapperCustom=sqlSession.getMapper(OrdersMapperCustom.class);

        List<OrdersCustom> ordersCustomList=ordersMapperCustom.findOrdersUser();

        System.out.println(ordersCustomList);

        sqlSession.close();
    }

    @Test
    public void findOrdersUserOrderDetail() throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();

        OrdersMapperCustom ordersMapperCustom=sqlSession.getMapper(OrdersMapperCustom.class);

        List<Orders> list=ordersMapperCustom.findOrdersUserOrderDetail();

        System.out.println(list);

        sqlSession.close();
    }


    //用run你会发现只有一条消息显示出来 对 那是因为我最终只映射到user一个类中 而这个类中只有一个对象
    //但是这个user里面它有层层嵌套 一层套一层 你用debug就能发现其中的结构端倪
    //计算机太妙了。
    @Test
    public void findUserOrdersOrderDetailItems() throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();

        OrdersMapperCustom ordersMapperCustom=sqlSession.getMapper(OrdersMapperCustom.class);

        List<User> list=ordersMapperCustom.findUserOrdersOrderDetailItems();

        System.out.println(list);

        sqlSession.close();
    }


     //延迟加载
    @Test
    public void findOrderUserLazyLoading() throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();

        OrdersMapperCustom ordersMapperCustom=sqlSession.getMapper(OrdersMapperCustom.class);

        List<Orders> list=ordersMapperCustom.findOrderUserLazyLoading();

        //遍历上面得到的订单列表
        for (Orders order:list
             ) {
            //执行getUser（）去查询用户信息 实现按需延迟加载(我这里直接遍历就加载了 现实中当然你可以设置一些按钮啊什么的使功能更具体化意义化)
            User user=order.getUser();
            System.out.println(user);
        }

        sqlSession.close();
    }
}

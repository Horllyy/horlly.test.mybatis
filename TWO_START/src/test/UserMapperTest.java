import My.mapper.UserMapper;
import My.po.User;
import My.po.UserCustomer;
import My.po.UserQureyVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

public class UserMapperTest {


    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception{
        String resource="SqlMapConfig.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 测试接口中的findUserByName方法做示范
     * @throws Exception
     */
    @Test
    public void testFindUserByName() throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();

        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        List<User> userList = userMapper.findUserByName("林");

        for (User user:userList
             ) {
            System.out.println(user);
        }

        sqlSession.close();

    }

    /**
     * 测试接口中的insertUser方法做示范
     * @throws Exception
     */
    @Test
    public void testInsertUser() throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();

        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        User user=new User();
        user.setUsername("费渡");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        user.setBirthday(sdf.parse( "1996-10-01"));
        user.setAddress("中国");
        user.setSex("男");
        userMapper.insertUser(user);

        //原来commit还是要自己加的。。。
        sqlSession.commit();
        sqlSession.close();
        System.out.println("新增的id="+user.getId());

    }

    /**
     * 测试 pojo包装类型 综合查询 + sql拼接技术测试
     * @throws Exception
     */
    @Test
    public void testFindUserList() throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();

        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        UserQureyVo userQueryVo=new UserQureyVo();
        UserCustomer userCustomer=new UserCustomer();
        userCustomer.setSex("男");
        //若是没有参数 此部分sql语句就不会拼接上去 和原来使用默认值的原版不同
//        userCustomer.setUsername("费");
        userQueryVo.setUserCustomer(userCustomer);
        List<UserCustomer> userCustomerList=userMapper.findUserList(userQueryVo);

        for (UserCustomer usercustomer:userCustomerList
             ) {
            System.out.println(usercustomer);
        }
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void testCache() throws Exception{

        SqlSession sqlSession1=sqlSessionFactory.openSession();
        SqlSession sqlSession2=sqlSessionFactory.openSession();
        SqlSession sqlSession3=sqlSessionFactory.openSession();

        UserMapper userMapper1=sqlSession1.getMapper(UserMapper.class);
        User user1=userMapper1.findUserById(1);
        System.out.println(user1);
        //这里执行关闭操作 sqlsession中的数据才会写到二级缓存区域 不然只会在一级缓存中
        sqlSession1.close();

        //commit()操作
        UserMapper userMapper3=sqlSession3.getMapper(UserMapper.class);
        User user3=userMapper3.findUserById(1);
        user3.setUsername("一家之主");
        userMapper3.updateUser(user3);
        //清空usermapper下的二级缓存
        sqlSession3.commit();
        sqlSession3.close();

        UserMapper userMapper2=sqlSession2.getMapper(UserMapper.class);
        User user2=userMapper2.findUserById(1);
        System.out.println(user2);
        sqlSession1.close();
    }
}

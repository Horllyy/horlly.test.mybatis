package My.first;

import My.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 这其实是UserTest UserDaoImpl的测试类 我怎么写到这里来了。。。
 * 测试类里反而只测了一个方法。。。莫名其妙
 */
public class MybatisFirst {
    @Test
    public void findUserByIdTest() throws IOException {
        String resource="SqlMapConfig.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        User user=sqlSession.selectOne("test.findUserById",1);
        System.out.println(user);
        sqlSession.close();
    }

    /**
     * 通过名字模糊查询
     * @throws IOException
     */

    @Test
    public void findUserByNameTest() throws IOException {
        String resource="SqlMapConfig.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<User> users =sqlSession.selectList("test.findUserByName","林");
        for (User user:users
             ) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    /**
     * 自增主键的返回
     * @throws IOException
     * @throws ParseException
     */

    @Test
    public void insertUserTest() throws IOException, ParseException {
        String resource="SqlMapConfig.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        User user=new User();
        user.setUsername("托尼斯塔克");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        user.setBirthday(sdf.parse( "1996-10-01"));
        user.setAddress("美国");
        user.setSex("男");
        sqlSession.insert("test.insertUser",user);
        System.out.println("新增的id="+user.getId());
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteUserTest() throws IOException, ParseException {
        String resource="SqlMapConfig.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession=sqlSessionFactory.openSession();

        sqlSession.delete("test.deleteUser",31);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateUserTest() throws IOException, ParseException {
        String resource="SqlMapConfig.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession=sqlSessionFactory.openSession();

//        User user=new User();
//        user.setId(1);
//        user.setUsername("刑丛连");
//        user.setSex("1");
//        user.setAddress("永川");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        user.setBirthday(sdf.parse( "1988-10-01"));
//        sqlSession.update("test.updateUser",user);
        //我喜欢下面这样改 更科学 不用每个属性都输入 只需要输入想修改属性 上面的属性若有一个没改  那个属性就会默认传入空值null 不好。
        //当然 和实际开发联系起来可能上面这种更科学 因为当和网页相联系 或许全部的值传来传去更正常 不然还要多很多筛选判断 不见得就更高效

        User user=sqlSession.selectOne("test.findUserById",1);
        user.setAddress("永川");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        user.setBirthday(sdf.parse( "1988-10-01"));
        sqlSession.update("test.updateUser",user);

        sqlSession.commit();
        sqlSession.close();
    }


}


package My.dao;

import My.po.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserDaoImpl implements UserDao {

    //要向dao中注入SqlSessionFactory 可通过构造方法注入
    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory=sqlSessionFactory;
    }

    public User findUserById(int id) throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();

        User user=sqlSession.selectOne("test.findUserById",id);

        sqlSession.close();

        return user;
    }

    public void insertUser(User user) throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();

        sqlSession.insert("test.insertUser",user);

        sqlSession.commit();

        sqlSession.close();

    }

    public void deleteUser(int id) throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();

        sqlSession.insert("test.deleteUser",id);

        sqlSession.commit();

        sqlSession.close();
    }
}

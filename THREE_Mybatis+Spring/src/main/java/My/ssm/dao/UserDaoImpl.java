package My.ssm.dao;

import My.ssm.po.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    public User findUserById(int id) throws Exception {
        //继承SqlSessionDaoSupport通过方法getSqlSession（）来得到SqlSession
        SqlSession sqlSession=this.getSqlSession();

        User user=sqlSession.selectOne("test.findUserById",id);

        //会自动关闭 所以这一步也不需要了
//        sqlSession.close();

        return user;
    }

}

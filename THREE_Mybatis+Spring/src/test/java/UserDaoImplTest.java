import My.ssm.dao.UserDao;
import My.ssm.po.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDaoImplTest {
private ApplicationContext applicationContext;
    @Before
    public void setUp() throws Exception{
         applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");

    }

    @Test
    public void testFindUsrById() throws Exception {
        UserDao userDao=applicationContext.getBean("userDao",UserDao.class);
        User user=userDao.findUserById(1);
        System.out.println(user);
    }
}

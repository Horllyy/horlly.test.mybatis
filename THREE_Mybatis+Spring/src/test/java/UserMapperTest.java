import My.ssm.mapper.UserMapper;
import My.ssm.po.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserMapperTest {
    private ApplicationContext applicationContext;
    @Before
    public void setUp() throws Exception{
        applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");

    }

    @Test
    public void testFindUsrById() throws Exception {
        UserMapper userMapper=applicationContext.getBean("userMapper",UserMapper.class);
        User user=userMapper.findUserById(1);
        System.out.println(user);
    }
}

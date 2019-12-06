package My.mapper;

import My.po.User;
import My.po.UserCustomer;
import My.po.UserQureyVo;

import java.util.List;

public interface UserMapper {

//规范2. mapper.java接口中的方法名和mapper.xmk中statement的id一致
//规范3. mapper.java接口中的方法输入参数和mapper.xmk中statement的parameterType一致
//规范3. mapper.java接口中的方法返回值类型和mapper.xmk中statement的resultType一致
    public User findUserById(int id) throws Exception;

    //我操这返回值明明就不一样。。。
    public List<User> findUserByName(String value) throws Exception;

    public void insertUser(User user) throws Exception;

    public void updateUser(User user) throws Exception;

    //pojo包装类型 综合查询
    public List<UserCustomer> findUserList(UserQureyVo userQureyVo) throws Exception;

}

package My.ssm.mapper;

import My.ssm.po.User;

public interface UserMapper {

//规范2. mapper.java接口中的方法名和mapper.xmk中statement的id一致
//规范3. mapper.java接口中的方法输入参数和mapper.xmk中statement的parameterType一致
//规范3. mapper.java接口中的方法返回值类型和mapper.xmk中statement的resultType一致
    public User findUserById(int id) throws Exception;

}

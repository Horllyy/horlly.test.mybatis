package My.ssm.dao;

import My.ssm.po.User;

public interface UserDao {

    public User findUserById(int id) throws Exception;

}

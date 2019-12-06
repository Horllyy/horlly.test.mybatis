mybatis和spring整合：
  原始dao方法：（我认为其他都没变 原理就是把一部分属于mybatis中SqlMapConfig.xml的配置：SqlSessionFactory，数据源datasource等，
                 转到spring的applicationContext.xml中配置）

       1.按数据库表属性创建po中类（如：User）
       2.写sql语句（sqlmap/User.xml）
       3.引入含sql语句的mapper文件（mybatis/SqlMapConfig.xml）
       4.配置spring的applicationContext.xml（SqlSessionFactory，数据源datasource，dao层的bean），并辅之以文件db.properties等
       5.编写dao层接口和实现类（具体使用sql语句的方法）
       6.测试

       add：1.前后中间再穿插以检查核对和pom.xml中Jar包的依赖引入
            2.值得一提的是：UserDaoImpl类中SqlSessionDaoSupport的继承 使得得到SqlSession不需要繁琐的获得 只需要使用getSqlSession（）方法即可

   mapper代理方法：
       1.步骤如上 几乎没有差别 差别在：
         （1）将dao层中的两个文件+ sqlmap/User.xml， 换成：My.ssm.mapper.UserMapper + sqlmap/UserMapper.xml
         （2）sqlSessionFactory数据来源的不同，下面第二点也有所体现

       2.和原始dao整合相比较， applicationContext.xml 中的配置 可屏蔽对：
           <bean id="userDao" class="My.ssm.dao.UserDaoImpl">
                  <property name="sqlSessionFactory" ref="sqlSessionFactory"></property>
           </bean>
         的注册，加上：
         <!--以下mapper配置 是根据mapper接口生成代理对象-->
         此句以下内容。（最后一个bean来自于逆向工程自动生成代码的注册 它也采用了mapper代理方式 所以刚好一起）


   整合补充：差别总结： 1.文件结构略有不同
                        2.sqlSessionFactory来源不同
             亮点： extends SqlSessionDaoSupport 父类的使用
             遗憾：批量扫描功能我都没有用出来 我相信今后mapper多了以后这个功能会有用处 起码在mapper代理整合中 十分方便
                   甚至只要在applicationContext.xml一个文件中批量扫描mapper接口 省去了在SqlMapConfig.xml中引入mapper.xml的步骤
                   日后可精进的地方还有很多 让时间来融合一切吧。



   此模块中还包括一部分逆向工程的的测试代码：
       My.ssm.po.Items
       My.ssm.po.ItemsExample

       My.ssm.mapper.ItemsMapper
       sqlmap/ItemsMapper.xml

       以及：applicationContext.xml 中 itemsMapper bean的注册
         和：mybatis/SqlMapConfig.xml 中  <mapper resource="sqlmap/ItemsMapper.xml"></mapper> 的 mapper.xml文件引入

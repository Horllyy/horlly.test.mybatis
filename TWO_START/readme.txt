1.入门程序：

  My.first文件夹下 MybatisFirst
  My.jdbc文件夹下 JdbcTest
  My.po文件夹下 User

 resources文件夹下 sqlmap文件夹下User.xml
 log4j
 SqlMapConfig


2.原始方法开发dao：

 新建：
  My.dao文件夹下 UserDao UserDaoImpl
  test文件夹下 UserDaoImplTest
 旧：
  resources文件夹下 sqlmap文件夹下User.xml

3.mapper代理方法：

 新建：
  resources-mapper-UserMapper.xml
  My-mapper-UserMapper.java
  test-UserMapperTest

4.优化配置SqlMapConfig.xml文件：
  新建：resources-db.properties


5.pojo包装类型 综合查询
  新建：
       resources-mapper-UserMapper.xml 中配置
       My-mapper-UserMapper.java 中方法
       test-UserMapperTest 中方法
       My.po-UserCustomer.java
       My.po-UserQueryVo.java

6.动态sql技术（此部分穿插测试 改装中findUserList这条statement配置 没有另外新建）
  新建：
      见 resources-mapper-UserMapper.xml 中findUserList配置
      见 test-UserMapperTest 中testFindUserList部分方法注释


<高级映射下面有一张关系图参考 来使例子有意义化 可想像可联系 在mybatis文件夹中>
7.高级映射：一对一 一对多 多对多（每个例子我分别给了一个 涉及:resultType resultMap的初级嵌套 resultMap的多级嵌套）
  新建：
       My.po-Items
       My.po-Orderdetail
       My.po-Orders
       My.po-OrdersCustom

       resources-mapper-OrdersMapperCustom.xml
       My.mapper-OrdersMapperCustom.java

       test-OrdersMapperCustomTest
  旧：
      SqlMapConfig.xml中增加了一个mapper映射文件：  <mapper resource="mapper/OrdersMapperCustom.xml"></mapper>

8.延迟加载(需有两个mapper方法 或更多 一个执行之后 有需要再往后执行)
  旧;
           resources-mapper-OrdersMapperCustom.xml
           resources-mapper-UserMapper 一个statement的恰好调用
           My.mapper-OrdersMapperCustom.java

           test-OrdersMapperCustomTest

           SqlMapConfig.xml的中 setting 延迟加载配置


9.二级缓存测试

  旧：resources-SqlMapConfig.xml
      resources-mapper-UserMapper.xml
      My-po-User.java
      test-UserMapperTest


10.整合mybatis和chcache
  新建：
      resources-ehcache.xml

  旧;
     resources-mapper-UserMapper 中 cache 标签设置
     test-UserMapperTest 如9所示二级缓存测试依旧有效
     （别忘记 SqlMapConfig.xml的中 setting 二级缓存开启配置）
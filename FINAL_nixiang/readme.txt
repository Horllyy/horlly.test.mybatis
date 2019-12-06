这个module其实是为THREE中的逆向工程打基础 它只实现了一个最核心的步骤：
                    配置环境和文件 使mapper和po文件夹 按数据库的样子打造出来。

之所以分成两部分，有两个原因：
                  1.要再在这个module中重新配置一遍整合环境 告辞。
                  2.涉及到重名文件覆盖问题 所以我们更倾向于直接搞一个module用来生成 再copy需要的文件 省去出其不意的麻烦。

注意点：
      1.generatorConfig.xml中的 几个路径，举例：

          targetPackage="My.ssm.po"
          targetProject="..\FINAL_nixiang\src\main\java"

          下面这个将会成为原始路径 上面这个会成为最终的包名路径展示出来

          PS：开始配置文件到手   targetProject=".\src" 直接生成在 ONE_START\src 包下了，你懂我意思吧，对比一下就知道怎么写了。

       2.我这里只用了 generatorConfig.xml 这个配置文件 其他的以后会用到。
       3.My.ssm.po 中的example类 作用和基础类对比一下就知道 它的作用更加进阶 更智能。不是简单的set & get方法，而是多加了一些参数，可以按条件查询。
         它们相互结合，经 My.ssm.mapper 中的接口融会贯通，可以实现非常强大好用的条件查询，条件之间可以叠加。
         详情可见 THREE_Mybatis+Spring\src\test\java\ItemsMapperTest.java 测试。
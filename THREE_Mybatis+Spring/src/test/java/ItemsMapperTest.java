import My.ssm.mapper.ItemsMapper;
import My.ssm.po.Items;
import My.ssm.po.ItemsExample;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class ItemsMapperTest {
    private ApplicationContext applicationContext;

    private ItemsMapper itemsMapper;

    @Before
    public void setUp() throws Exception {
        applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        itemsMapper=applicationContext.getBean("itemsMapper",ItemsMapper.class);
    }

    @Test
    public void testInsert(){
        Items item=new Items();
        item.setName("beats耳机");
        item.setId(5);
        item.setDetail("想拥有。。。");
        item.setPrice(1600f);
        item.setCreatetime(new Date());
        itemsMapper.insert(item);
    }

    //example类简单来理解就是简单查询的叠加 类似 条件1+条件2+...这种感觉
    @Test
    public void testSelectByExample(){
        ItemsExample itemsExample=new ItemsExample();
        ItemsExample.Criteria criteria=itemsExample.createCriteria();
        //条件1 名字
        criteria.andNameEqualTo("beats耳机");
        //条件2 id
        criteria.andIdEqualTo(5);
        List<Items> itemsList=itemsMapper.selectByExample(itemsExample);
        System.out.println(itemsList);
    }

    @Test
    public void testSelectByPrimaryKey(){
        Items item=itemsMapper.selectByPrimaryKey(1);
        System.out.println(item);
    }

}

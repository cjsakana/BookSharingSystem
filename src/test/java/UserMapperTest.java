import kg.us.sakanatang.config.SpringConfig;
import kg.us.sakanatang.domain.entity.User;
import kg.us.sakanatang.mapper.UserMapper;
import kg.us.sakanatang.utils.BCryptUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void create(){
        User user=new User();
        user.setAvatar("https://img2.baidu.com/it/u=3839186672,3565557071&fm=253&fmt=auto&app=120&f=JPEG?w=667&h=500");
        user.setName("cjy");
        user.setEmail("666.moemail.cc");
        user.setPassword(BCryptUtil.encode("123456"));
        user.setSex(1);
        user.setRole(0);
        int raw=userMapper.insert(user);
        System.out.println(raw);
    }
}

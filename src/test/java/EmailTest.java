import kg.us.sakanatang.config.RedisConfig;
import kg.us.sakanatang.config.SpringConfig;
import kg.us.sakanatang.utils.EnvReader;
import kg.us.sakanatang.utils.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class EmailTest {
    @Test
    public void a(){
        System.out.println(EnvReader.getEnv("path"));
    }

    @Test
    public void b(){
        System.out.println(Util.RandomNumberGenerator());
    }
}

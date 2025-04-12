import kg.us.sakanatang.config.SpringConfig;
import kg.us.sakanatang.utils.BCryptUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class BCryptTest {
    @Test
    public void a(){
        System.out.println(BCryptUtil.encode("123456"));
    }
    @Test
    public void b(){
        System.out.println(BCryptUtil.matches("123456",
                "$2a$10$seSt4q.bEqeDY8cBJawsWeP2otjStW1H.GGBdCIZOqXy97iIzWqXi"));
    }
}

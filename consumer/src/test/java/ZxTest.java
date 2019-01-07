import com.lee.Consumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Consumer.class)
@RunWith(SpringRunner.class)
public class ZxTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test1() {

        System.out.println(redisTemplate.opsForSet().add("1","2222"));
        System.out.println(redisTemplate.opsForSet().add("1","1111"));
        System.out.println(redisTemplate.opsForSet().isMember("1", "1111") + "----");


    }

}


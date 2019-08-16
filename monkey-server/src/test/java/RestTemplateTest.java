import com.smile.monkeyapi.enitity.ResponseResult;
import com.smile.monkeyserver.MonkeyServerApplication;
import com.smile.monkeyserver.restapi.RestService;
import com.smile.monkeyserver.restapi.impl.RestServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName RestTemplateTest
 * @Author kris
 * @Date 2019/8/16
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MonkeyServerApplication.class)
public class RestTemplateTest {
    @Autowired
    private RestService restService;


    @Test
    public void invokeGet(){
        ResponseResult responseResult = restService.monkeyGet("http://localhost:9719/statistics//surf", null, ResponseResult.class);
        System.out.println(responseResult);
    }


}

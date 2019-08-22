import com.smile.monkeyapi.enitity.ResponseResult;
import com.smile.monkeyserver.MonkeyServerApplication;
import com.smile.monkeyserver.restapi.RestService;
import com.smile.monkeyserver.restapi.impl.RestServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RestTemplateTest
 * @Author kris
 * @Date 2019/8/16
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MonkeyServerApplication.class)
public class RestTemplateTest {
    private final static Integer FORTIME = 2000;
    @Autowired
    private RestService restService;


    @Test
    public void invokeGet() {
        ResponseResult responseResult = restService.monkeyGet("http://localhost:9719/statistics/surf", null, ResponseResult.class);
        System.out.println(responseResult);
    }

    @Test
    public void mockMultiInvokeGet() {
        ExecutorService executorService = new ThreadPoolExecutor(500, 1000, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(5000));
        for (int i = 0; i < FORTIME; i++) {
            executorService.execute(() -> {
                ResponseResult responseResult = restService.monkeyGet("http://monkey.com:9770/statistics/test/dis-lock", null, ResponseResult.class);
                System.out.println(responseResult);
            });
        }
    }


    @Test
    public void mergeExcel(){
        
    }
}


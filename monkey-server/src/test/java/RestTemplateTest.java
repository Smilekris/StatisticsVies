import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.smile.monkeyapi.enitity.ResponseResult;
import com.smile.monkeyserver.MonkeyServerApplication;
import com.smile.monkeyserver.restapi.RestService;
import com.smile.monkeyserver.restapi.impl.RestServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;
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


//    @Test
    public void invokeGet() {
        ResponseResult responseResult = restService.monkeyGet("http://localhost:9719/statistics/surf", null, ResponseResult.class);
        System.out.println(responseResult);
    }

//    @Test
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

    private static final Algorithm ALGORITHM= Algorithm.HMAC256("security");
    public static void encode() {
        //通过秘钥生成一个算法
        String token = JWT.create()
                //设置签发者
                .withIssuer("test")
                //设置过期时间为一个小时
                .withExpiresAt(new Date(System.currentTimeMillis()+60*60*1000))
                //设置用户信息
                .withClaim("name","小明")
                .withClaim("age",20)
                .sign(ALGORITHM);

        System.out.println(token);
//        return token;
    }
    private static final JWTVerifier JWT_VERIFIER= JWT.require(ALGORITHM).withIssuer("test").build();
    public static void decode(String token) {
        DecodedJWT decodedJWT = JWT_VERIFIER.verify(token);
        System.out.println(decodedJWT);
        Claim c1 = decodedJWT.getClaim("name");
        String as = c1.as(String.class);
        System.out.println(as);
        //如果校验失败会抛出异常
        //payload可从decodeJWT中获取
    }

    public static void main(String[] args) {
        decode("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0ZXN0IiwibmFtZSI6IuWwj-aYjiIsImV4cCI6MTU3NjEyMzI1NCwiYWdlIjoyMH0.4yEulU-wBVXkCE1Anq6DsFG9MwwRn9ka5kBqFS8Vp2A");
//        encode();
    }
}


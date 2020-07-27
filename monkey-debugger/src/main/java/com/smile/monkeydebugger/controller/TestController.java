package com.smile.monkeydebugger.controller;

import com.smile.monkeydebugger.service.ChainAsbtract;
import com.smile.monkeydebugger.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @ClassName TestController
 * @Author yamei
 * @Date 2020/7/27
 **/
@RestController
@RequestMapping("/test")
public class TestController {
//    @Autowired
    private final List<TestService> testServices;
//    @Autowired
    private final List<ChainAsbtract> chains;

    private ChainAsbtract target;

    public TestController(List<TestService> testServices, List<ChainAsbtract> chains) {
        this.testServices = testServices;
        this.chains = chains;
        chains.stream().sorted(Comparator.comparing(ChainAsbtract::getOrder));
        int size = chains.size();
        for (int i = 0;i<size-1;i++){
            chains.get(i).setNext(chains.get(i+1));
        }
        target = chains.get(0);
    }

    @GetMapping("/sowhat")
    public String justTest(){
        for (TestService service : testServices) {
            service.doPrint();
        }
        return "ok";
    }

    @GetMapping("/chain")
    public String testChain(){
        target.print("");
        return "ok";
    }
}

package com.anz.account;

import com.anz.account.exception.NoDataFoundException;
import com.anz.account.response.AccountResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WebClient {

    @Autowired
    public RestTemplate restTemplate;

    public AccountResponse getAccountDetailsFallBack() throws NoDataFoundException {
        throw new NoDataFoundException("THis is the exception");
    }

    @HystrixCommand(fallbackMethod = "getAccountDetailsFallBack",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60")
            }
    )
    public AccountResponse   getCall() throws NoDataFoundException {
        String message = restTemplate.getForObject("http://localhost:9090/employees/" + "hello", String.class);
        System.out.println(message);
        return new AccountResponse();
    }
}

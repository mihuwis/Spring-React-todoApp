package com.progresspoint.demofirstWebApp.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path = "/basicauth")
    public String basicAuthCheck() {
        return "Oki";
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "static hello";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("hello bean");
    }

    @GetMapping(path = "/hello-world/{name}")
    public HelloWorldBean helloWorldWithVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("hello variable . %s", name));
    }
}

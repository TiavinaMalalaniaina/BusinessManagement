package com.example.rh.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController{

    @GetMapping("testing")
    public void test() {
        System.out.println("Tiavina Malalaniaina");
    }
}

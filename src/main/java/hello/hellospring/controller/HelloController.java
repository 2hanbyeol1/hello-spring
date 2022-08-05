package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    // View 환경설정
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "한별님");
        return "hello";
    }

    // MVC와 템플릿 엔진
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API - 문자 처리
    @GetMapping("hello-string")
    @ResponseBody // body: http의 body (html의 body X)
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello 한별"이라는 데이터를 그대로 내려줌 (html X)
    }

    // API - 객체 처리
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // ctrl + shift + enter
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}

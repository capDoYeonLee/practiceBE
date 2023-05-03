package hello.hellospring.controller;

import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController{

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!"); // key, value
        return "hello"; // template/hello.html
    }

    // GetMapping 어노테이션은 url 요청을 아래 method와 mapping해준는 역할을 한다. Get/Pose 중 Get을 의미한다.
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }


    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam(value = "name")String name){
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체로 반환하면 HttpMessageConverter(JsonConverter/StringConverter)가 자연스럽게 JSON으로 변환
    }
    static class Hello {
        // private으로 설정한 이유는 외부에서 값을 변경을 최대한으로 막기 위해서
        private String name; // name 변수를 접근하기 위해 getter,setter를 사용
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}

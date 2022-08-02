package introduction.springintroduction.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IntroductionController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name", required = true) String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // HTTP의 바디에 return하는 값을 넣어주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;  // name이 spring이면 "hello spring"이 된다.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //브라우저로 보면 json형태로 나오는데 객체를 json으로 매핑하여 보내주기 때문이다.
        // 객체를 반환하면서 @ResponseBody설정이 되어 있다면 json으로 반환하는 것으로 스프링에 기본으로 설정되어 있다.
    }

    static class Hello{
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }


    }
}

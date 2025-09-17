package com.example.spring_basics;

import org.springframework.web.bind.annotation.*;

@RestController //prosty webservice,
// który wystawimy do możliwości użycia
public class HelloExample {
    @RequestMapping("/") //metoda wywołana, kiedy użytkownik wejdzie na stronę główną witryny
    public String hello(){
        return "Hello world";
    }

    @RequestMapping("/greeting") //localhost:1212/greeting?name=Ania
    public String helloAgain(@RequestParam(value = "name",
            defaultValue = "User") String name) {
        return "Hello " + name;
    }

    //parametry w ścieżce:
    @RequestMapping("/hi/{who}")
        public String hi(@PathVariable String who){
            return String.format("Hello %s!", who);
    }
}

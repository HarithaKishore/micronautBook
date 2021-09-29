package buddy_project.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller(value = "/hello")
public class HelloController {

    @Get(value = "/{name}")
    public String printName(String name) {
        return "Hello " + name;
    }
}

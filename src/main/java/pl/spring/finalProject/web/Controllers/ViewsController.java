package pl.spring.finalProject.web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewsController {

    @RequestMapping("/core")
    public String coreFile2() {
        return "core";
    }

    @RequestMapping("/searchConnections")
    public String search() {
        return "DropDownList";
    }
}

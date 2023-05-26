package org.example.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author: Derek.huang on 2023/5/26 8:10.
 */
@ApiIgnore
@Controller
public class IndexController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getInfo() {
        return "redirect:/swagger-ui.html";
        //return "redirect:/index.html";
    }
}

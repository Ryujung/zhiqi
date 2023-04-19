package com.zhiqi.web.controller.tool;

import com.zhiqi.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author RyuJung
 * @since 2023/4/19-11:04
 */
@Controller
public class SwaggerController  extends BaseController {

    @GetMapping("/tool/swagger")
    public String index() {
        return redirect("/swagger-ui.html");
    }

}

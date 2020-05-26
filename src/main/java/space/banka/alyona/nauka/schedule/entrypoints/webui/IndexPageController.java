package space.banka.alyona.nauka.schedule.entrypoints.webui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexPageController {

    @RequestMapping("/")
    String redirectToLargeTimesheet() {
        return "redirect:/departments/all/timesheet";
    }
}

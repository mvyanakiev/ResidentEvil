package residentevil.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/viruses")
public class VirusController extends BaseController {

    @GetMapping("/add")
    public ModelAndView add(){
        return super.view("add-virus");
    }




}

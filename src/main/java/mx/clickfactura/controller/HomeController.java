package mx.clickfactura.controller;


import mx.clickfactura.util.TipoCambioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by SISTEMAS03 on 02/03/2016.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {


    @GetMapping(value = {"/"})
    public String registra() {
        return "redirect:/swagger-ui.html";
    }


}

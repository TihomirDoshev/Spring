package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.UserRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @GetMapping("users/register")
    private String viewRegister(Model model){
        //model.addAttribute("registerData",new UserRegisterDTO());
    return "register";

    }
    @PostMapping("users/register")
    private String doRegister(@Valid UserRegisterDTO data,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            //redirectAttributes.addFlashAttribute("registerData",data);
            //TODO errors
            return "register";
        }


        //register user
        return "redirect:/users/login";
    }
    @GetMapping("users/login")
    public String viewLogin(){
        return "login";
    }


}

package org.modelmapperexercise;

import org.modelmapperexercise.service.UserService;
import org.modelmapperexercise.service.dto.UserRegisterDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class CommandRunner implements CommandLineRunner {

    private final UserService userService;

    public CommandRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        while (!input.equals("END")){
            String[] tokens = input.split("\\|");
            String command = "";
            switch (tokens[0]){
                case "RegisterUser":
                    command =  this.userService.registerUser(new UserRegisterDTO(tokens[1],tokens[2],tokens[3],tokens[4]));
                    break;
                case "LoginUser":
                    break;
                case "Logout ":
                    break;
            }
            System.out.println(command);
            input = reader.readLine();
        }
    }
}

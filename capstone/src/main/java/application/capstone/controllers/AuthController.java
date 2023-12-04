package application.capstone.controllers;

import application.capstone.entities.User;
import application.capstone.exceptions.BadRequestException;
import application.capstone.payloads.NewUserDTO;
import application.capstone.payloads.UserLoginDTO;
import application.capstone.payloads.UserLoginSuccessDTO;
import application.capstone.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/public/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public UserLoginSuccessDTO login(@RequestBody UserLoginDTO body){

        return new UserLoginSuccessDTO(authService.authenticateUser(body));
    }




    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody @Validated NewUserDTO body , BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.register(body);
            }catch (IOException e){
                System.err.println(e.getMessage());
                return null;
            }

        }
    }

}

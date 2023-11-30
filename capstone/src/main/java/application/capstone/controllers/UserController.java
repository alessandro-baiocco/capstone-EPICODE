package application.capstone.controllers;


import application.capstone.entities.User;
import application.capstone.exceptions.BadRequestException;
import application.capstone.exceptions.NotFoundException;
import application.capstone.payloads.NewUserDTO;
import application.capstone.payloads.PUTUserDTO;
import application.capstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/private/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("")
    public Page<User> getAllUser(@RequestParam(defaultValue = "0")int page ,
                                 @RequestParam(defaultValue = "10")int size,
                                 @RequestParam(defaultValue = "id")String order){
        return userService.getAllUser(page , size , order);
    }




    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Validated NewUserDTO body , BindingResult validation) {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return userService.save(body);
            }catch (IOException e){
                throw new RuntimeException("problema lato server");
            }

        }

    }

    @PutMapping("/{id}")
    public User findByIdAndUpdate(@PathVariable UUID id , @RequestBody @Validated PUTUserDTO body , BindingResult validation) throws NotFoundException {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return userService.findByIdAndUpdate(id , body);
            }catch (IOException e){
                throw new RuntimeException("problema lato server");
            }
        }
    }


    @GetMapping("/me")
    public UserDetails getProfile(@AuthenticationPrincipal UserDetails currentUser){
        return currentUser;
    };

    @PutMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentUser, @RequestBody PUTUserDTO body){
        try {
            return userService.findByIdAndUpdate(currentUser.getId(), body);
        }catch (IOException e){
        throw new RuntimeException("problema lato server");
    }

    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getProfile(@AuthenticationPrincipal User currentUser){
        userService.findByIdAndDelete(currentUser.getId());
    };



    @GetMapping("/{id}")
    public User findById(@PathVariable UUID id) throws NotFoundException{
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void findByIdAndDelete(@PathVariable UUID id) throws NotFoundException{
        userService.findByIdAndDelete(id);
    }



}

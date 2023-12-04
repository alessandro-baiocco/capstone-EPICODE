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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/private/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getAllUser(@RequestParam(defaultValue = "0")int page ,
                                 @RequestParam(defaultValue = "10")int size,
                                 @RequestParam(defaultValue = "id")String order){
        return userService.getAllUser(page , size , order);
    }






    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
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
    public User putMyProfile(@AuthenticationPrincipal User currentUser, @RequestBody PUTUserDTO body){
        try {
            return userService.findByIdAndUpdate(currentUser.getId(), body);
        }catch (IOException e){
        throw new RuntimeException("problema lato server");
    }

    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMyProfile(@AuthenticationPrincipal User currentUser){
        userService.findByIdAndDelete(currentUser.getId());
    };

    @PatchMapping("/me/upload")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User changeMyProfilePicture(@AuthenticationPrincipal User currentUser, @RequestParam("avatar") MultipartFile body ){
        try {
            return userService.setMyPicture(currentUser , body);
        }catch (IOException e){
            throw new RuntimeException("problema lato server");
        }

    };



    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findById(@PathVariable UUID id) throws NotFoundException{
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findByIdAndDelete(@PathVariable UUID id) throws NotFoundException{
        userService.findByIdAndDelete(id);
    }



}

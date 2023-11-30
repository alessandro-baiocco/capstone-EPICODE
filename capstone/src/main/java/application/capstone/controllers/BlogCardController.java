package application.capstone.controllers;


import application.capstone.entities.BlogCard;
import application.capstone.exceptions.BadRequestException;
import application.capstone.exceptions.NotFoundException;
import application.capstone.payloads.PUTBlogCardDTO;
import application.capstone.service.BlogCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/public/cards")
public class BlogCardController {
    @Autowired
    private BlogCardService blogCardService;


    @GetMapping("")
    public Page<BlogCard> getAllArticle(@RequestParam(defaultValue = "0")int page ,
                                        @RequestParam(defaultValue = "10")int size,
                                        @RequestParam(defaultValue = "id")String order){
        return blogCardService.getAllBlogCard(page , size , order);
    }





    @PutMapping("/{id}")
    public BlogCard findByIdAndUpdate(@PathVariable UUID id , @RequestBody @Validated PUTBlogCardDTO body , BindingResult validation) throws NotFoundException {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return blogCardService.findByIdAndUpdate(id , body);
            }catch (IOException e){
                throw new RuntimeException("problema lato server");
            }
        }
    }



    @GetMapping("/{id}")
    public BlogCard findById(@PathVariable UUID id) throws NotFoundException{
        return blogCardService.findById(id);
    }





}

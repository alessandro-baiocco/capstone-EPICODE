package application.capstone.controllers;

import application.capstone.entities.BlogArticle;
import application.capstone.entities.BlogCard;
import application.capstone.enums.Genere;
import application.capstone.enums.Tema;
import application.capstone.exceptions.BadRequestException;
import application.capstone.exceptions.NotFoundException;
import application.capstone.payloads.NewBlogArticleDTO;
import application.capstone.payloads.PUTBlogArticleDTO;
import application.capstone.service.BlogArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/public/articles")
public class BlogArticleController {
    @Autowired
    private BlogArticleService blogArticleService;


    @GetMapping("")
    public Page<BlogArticle> getAllArticle(@RequestParam(defaultValue = "0")int page ,
                                           @RequestParam(defaultValue = "10")int size,
                                           @RequestParam(defaultValue = "id")String order){
        return blogArticleService.getAllArticle(page , size , order);
    }




    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BlogArticle save(@RequestBody @Validated NewBlogArticleDTO body ,  BindingResult validation) {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return blogArticleService.save(body);
            }catch (IOException e){
                throw new RuntimeException("problema lato server");
            }

        }

    }

    @PutMapping("/{id}")
    public BlogArticle findByIdAndUpdate(@PathVariable UUID id , @RequestBody @Validated  PUTBlogArticleDTO body ,  BindingResult validation) throws NotFoundException {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
        return blogArticleService.findByIdAndUpdate(id , body);
    }catch (IOException e){
                throw new RuntimeException("problema lato server");
            }
        }
    }



    @GetMapping("/{id}")
    public BlogArticle findById(@PathVariable UUID id) throws NotFoundException{
        return blogArticleService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void findByIdAndDelete(@PathVariable UUID id) throws NotFoundException{
        blogArticleService.findByIdAndDelete(id);
    }



    @PatchMapping("/{id}/primary")
    public BlogArticle changePrimaryPicture(@PathVariable UUID id , @RequestParam("picture") MultipartFile body) throws NotFoundException , IOException{
       return blogArticleService.setPrimaryPicture(id , body);
    }
    @PatchMapping("/{id}/secondary")
    public BlogArticle changeSecondaryPicture(@PathVariable UUID id , @RequestParam("picture") MultipartFile body) throws NotFoundException , IOException{
        return blogArticleService.setSecondaryPicture(id , body);
    }




}

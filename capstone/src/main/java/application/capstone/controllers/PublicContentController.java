package application.capstone.controllers;

import application.capstone.entities.BlogArticle;
import application.capstone.entities.BlogCard;
import application.capstone.exceptions.NotFoundException;
import application.capstone.service.BlogArticleService;
import application.capstone.service.BlogCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/public/content")
public class PublicContentController {
    @Autowired
    private BlogArticleService blogArticleService;
    @Autowired
    private BlogCardService blogCardService;


    @GetMapping("/articles")
    public Page<BlogArticle> getAllArticle(@RequestParam(defaultValue = "0")int page ,
                                           @RequestParam(defaultValue = "10")int size,
                                           @RequestParam(defaultValue = "id")String order){
        return blogArticleService.getAllArticle(page , size , order);
    }

    @GetMapping("/articles/{id}")
    public BlogArticle findArticleById(@PathVariable UUID id) throws NotFoundException {
        return blogArticleService.findById(id);
    }


    @GetMapping("/cards/{id}")
    public BlogCard findCardById(@PathVariable UUID id) throws NotFoundException{
        return blogCardService.findById(id);
    }


    @GetMapping("/cards")
    public Page<BlogCard> getAllCards(@RequestParam(defaultValue = "0")int page ,
                                      @RequestParam(defaultValue = "10")int size,
                                      @RequestParam(defaultValue = "id")String order){
        return blogCardService.getAllBlogCard(page , size , order);
    }





}

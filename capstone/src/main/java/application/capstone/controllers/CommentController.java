package application.capstone.controllers;

import application.capstone.entities.Comment;
import application.capstone.entities.User;
import application.capstone.exceptions.BadRequestException;
import application.capstone.exceptions.NotFoundException;

import application.capstone.payloads.NewCommentDTO;
import application.capstone.payloads.PUTCommentDTO;
import application.capstone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("")
    public Page<Comment> getAllComment(@RequestParam(defaultValue = "0")int page ,
                                           @RequestParam(defaultValue = "10")int size,
                                           @RequestParam(defaultValue = "id")String order){
        return commentService.getAllComment(page , size , order);
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment save(@RequestBody @Validated NewCommentDTO body , BindingResult validation , @AuthenticationPrincipal User currentUser) {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return commentService.save(body , currentUser);
            }catch (IOException e){
                throw new RuntimeException("problema lato server");
            }

        }
    }

    @PutMapping("/{id}")
    public Comment findByIdAndUpdate(@PathVariable UUID id , @RequestBody @Validated PUTCommentDTO body , BindingResult validation) throws NotFoundException {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return commentService.findByIdAndUpdate(id , body);
            }catch (IOException e){
                throw new RuntimeException("problema lato server");
            }
        }
    }

    @GetMapping("/{id}")
    public Comment findById(@PathVariable UUID id) throws NotFoundException{
        return commentService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void findByIdAndDelete(@PathVariable UUID id) throws NotFoundException{
        commentService.findByIdAndDelete(id);
    }


}



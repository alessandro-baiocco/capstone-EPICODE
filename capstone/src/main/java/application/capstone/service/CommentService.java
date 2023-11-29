package application.capstone.service;

import application.capstone.entities.Comment;
import application.capstone.entities.User;
import application.capstone.enums.Genere;
import application.capstone.enums.Role;
import application.capstone.exceptions.BadRequestException;
import application.capstone.exceptions.NotFoundException;
import application.capstone.payloads.CommentDTO;
import application.capstone.payloads.PutCommentDTO;
import application.capstone.repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class CommentService {


    @Autowired
    private CommentsRepository commentsRepo;
    @Autowired
    private UserService userService;



    public Comment save(CommentDTO body , UUID userID) {

        User user = userService.findById(userID);

        Comment newComment = new Comment();

        newComment.setComment(body.comment());
        newComment.setUser(user);





        return commentsRepo.save(newComment);
    }


    public Comment findByIdAndUpdate(UUID id , PutCommentDTO body) throws NotFoundException{
        Comment found = findById(id);

        found.setComment(body.comment());

        return  commentsRepo.save(found);
    }



    public Comment findById(UUID id) throws NotFoundException{
        return commentsRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }




    public Page<Comment> getAllComment(int page , int size , String order){
        Pageable pageable = PageRequest.of(page, size , Sort.by(order));
        return commentsRepo.findAll(pageable);
    }





    public void findByIdAndDelete(UUID id) throws NotFoundException{
        Comment found = findById(id);
        commentsRepo.delete(found);
    }







}

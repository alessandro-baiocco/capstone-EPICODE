package application.capstone.service;

import application.capstone.entities.BlogArticle;
import application.capstone.entities.Comment;
import application.capstone.entities.User;
import application.capstone.exceptions.BadRequestException;
import application.capstone.exceptions.NotFoundException;
import application.capstone.payloads.NewCommentDTO;
import application.capstone.payloads.PUTCommentDTO;
import application.capstone.repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class CommentService {


    @Autowired
    private CommentsRepository commentsRepo;
    @Autowired
    private BlogArticleService blogArticleService;
    @Autowired
    private UserService  userService;



    public Comment save(NewCommentDTO body , User user) throws IOException {

        BlogArticle blogArticle = blogArticleService.findById(body.blog());

        Comment newComment = new Comment();

        newComment.setUser(user);
        newComment.setComment(body.comment());
        newComment.setBlogArticle(blogArticle);

        return commentsRepo.save(newComment);
    }


    public Comment findByIdAndUpdate(UUID userId , UUID id , PUTCommentDTO body) throws NotFoundException , IOException{
        Comment found = findById(id);
        if(found.getUser().getId().equals(userId)){
            found.setComment(body.comment());
        }else {
            throw new BadRequestException("questo commento non è tuo");
        }


        return  commentsRepo.save(found);
    }



    public Comment findById(UUID id) throws NotFoundException{
        return commentsRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }




    public Page<Comment> getAllComment(int page , int size , String order , boolean ascending ){
        Pageable pageable = PageRequest.of(page, size , Sort.by(order));
        if (!ascending) pageable = PageRequest.of(page, size, Sort.by(order).descending());
        return commentsRepo.findAll(pageable);
    }





    public void findByIdAndDelete(UUID id) throws NotFoundException{
        Comment found = findById(id);
        commentsRepo.delete(found);
    }







}

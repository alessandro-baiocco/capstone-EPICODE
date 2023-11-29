package application.capstone.service;

import application.capstone.entities.BlogArticle;
import application.capstone.entities.BlogCard;
import application.capstone.enums.Genere;
import application.capstone.exceptions.BadRequestException;
import application.capstone.exceptions.NotFoundException;
import application.capstone.payloads.PutBlogCardDTO;
import application.capstone.repositories.BlogCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BlogCardService {

    @Autowired
    private BlogCardRepository cardRepo;

    public void save(BlogArticle blogArticle , String description) {

        BlogCard newBlogCard = new BlogCard();

        newBlogCard.setTitolo(blogArticle.getTitolo());
        newBlogCard.setGenere(blogArticle.getGenresList().stream().toList().get(0));
        newBlogCard.setDescription(description);
        newBlogCard.setBlogArticle(blogArticle);

        cardRepo.save(newBlogCard);
    }


    public BlogCard findByIdAndUpdate(UUID id , PutBlogCardDTO body) throws NotFoundException {
        BlogCard found = findById(id);

        found.setTitolo(body.titolo());
        try {
            found.setGenere(Genere.valueOf(body.genere()));
        }catch (IllegalArgumentException ex){
            throw new BadRequestException("genere " + body.genere() +  " non valido");
        }

        found.setDescription(body.description());

        return  cardRepo.save(found);
    }



    public BlogCard findById(UUID id) throws NotFoundException{
        return cardRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }




    public Page<BlogCard> getAllBlogCard(int page , int size , String order){
        Pageable pageable = PageRequest.of(page, size , Sort.by(order));
        return cardRepo.findAll(pageable);
    }





    public void findByIdAndDelete(UUID id) throws NotFoundException{
        BlogCard found = findById(id);
        cardRepo.delete(found);
    }



}

package application.capstone.service;

import application.capstone.entities.BlogArticle;

import application.capstone.entities.BlogCard;
import application.capstone.enums.Genere;

import application.capstone.enums.Tema;
import application.capstone.exceptions.BadRequestException;
import application.capstone.exceptions.NotFoundException;
import application.capstone.payloads.NewBlogArticleDTO;

import application.capstone.payloads.PUTBlogArticleDTO;

import application.capstone.repositories.BlogArticleRepository;
import application.capstone.repositories.BlogCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.*;

public class BlogArticleService {

    @Autowired
    private BlogArticleRepository blogArticleRepo;
    @Autowired
    private BlogCardRepository blogCardRepo;

    public BlogArticle save(NewBlogArticleDTO body) {

        BlogArticle newBlog = new BlogArticle();

        newBlog.setTitolo(body.titolo());
        newBlog.setSvillupatore(body.svillupatore());
        newBlog.setPubblicazione(body.pubblicazione());
        try {
            newBlog.setTema(body.tema());
        }catch (IllegalArgumentException ex){
            throw new BadRequestException("tema non valido");
        }

        newBlog.setStoria(body.storia());
        newBlog.setEsperienza(body.esperienza());
        newBlog.setConsigli(body.consigli());
        String[] generi = body.genere().split(",");
        Set<Genere> generiSet = new HashSet<>();

        for (String s : generi) {
            try {
                generiSet.add(Genere.valueOf(s));
            } catch (IllegalArgumentException ex) {
                throw new BadRequestException("genere " + s + " non valido");
            }
        }

        newBlog.setGenresList(generiSet);
        newBlog.setComments(new ArrayList<>());

        BlogArticle savedBlog = blogArticleRepo.save(newBlog);
        BlogCard newBlogCard = new BlogCard();

        newBlogCard.setTitolo(savedBlog.getTitolo());
        newBlogCard.setGenere(savedBlog.getTema());
        newBlogCard.setDescription(body.desciption());
        newBlogCard.setBlogArticle(savedBlog);

        blogCardRepo.save(newBlogCard);

        return savedBlog;
    }


    public BlogArticle findByIdAndUpdate(UUID id , PUTBlogArticleDTO body) throws NotFoundException {
        BlogArticle newBlog = new BlogArticle();

        newBlog.setTitolo(body.titolo());
        newBlog.setSvillupatore(body.svillupatore());
        newBlog.setPubblicazione(body.pubblicazione());
        try {
            newBlog.setTema(Tema.valueOf(body.tema().trim().toUpperCase()));
        }catch (IllegalArgumentException ex){
            throw new BadRequestException("tema non valido");
        }

        newBlog.setStoria(body.storia());
        newBlog.setEsperienza(body.esperienza());
        newBlog.setConsigli(body.consigli());

        String[] generi = body.genere().split(",");
        Set<Genere> generiSet = new HashSet<>();

        for (String s : generi) {
            try {
                generiSet.add(Genere.valueOf(s.trim().toUpperCase()));
            } catch (IllegalArgumentException ex) {
                throw new BadRequestException("genere " + s + " non valido");
            }
        }

        newBlog.setGenresList(generiSet);

        newBlog.setComments(new ArrayList<>());




        return blogArticleRepo.save(newBlog);
    }



    public BlogArticle findById(UUID id) throws NotFoundException{
        return blogArticleRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException{
        BlogArticle found = findById(id);
        BlogCard foundCard = found.getBlogCard();
        blogCardRepo.delete(foundCard);
        blogArticleRepo.delete(found);

    }


}

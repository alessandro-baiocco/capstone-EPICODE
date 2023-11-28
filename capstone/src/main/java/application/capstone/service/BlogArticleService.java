package application.capstone.service;

import application.capstone.entities.BlogArticle;

import application.capstone.enums.Genere;

import application.capstone.enums.Tema;
import application.capstone.exceptions.BadRequestException;
import application.capstone.exceptions.NotFoundException;
import application.capstone.payloads.NewBlogArticleDTO;

import application.capstone.payloads.PUTBlogArticleDTO;

import application.capstone.repositories.BlogArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.*;

public class BlogArticleService {

    @Autowired
    private BlogArticleRepository blogArticleRepo;

    public BlogArticle save(NewBlogArticleDTO body) {

        BlogArticle newBlog = new BlogArticle();

        newBlog.setTitolo(body.titolo());
        newBlog.setSvillupatore(body.svillupatore());
        newBlog.setPubblicazione(body.pubblicazione());
        try {
            newBlog.setTema(Tema.valueOf(body.tema()));
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


        return blogArticleRepo.save(newBlog);
    }


    public BlogArticle findByIdAndUpdate(UUID id , PUTBlogArticleDTO body) throws NotFoundException {
        BlogArticle newBlog = new BlogArticle();

        newBlog.setTitolo(body.titolo());
        newBlog.setSvillupatore(body.svillupatore());
        newBlog.setPubblicazione(body.pubblicazione());
        try {
            newBlog.setTema(Tema.valueOf(body.tema()));
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


        return blogArticleRepo.save(newBlog);
    }



    public BlogArticle findById(UUID id) throws NotFoundException{
        return blogArticleRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException{
        BlogArticle found = findById(id);
        blogArticleRepo.delete(found);
    }


}

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.io.IOException;
import java.util.*;

@Service
public class BlogArticleService {

    @Autowired
    private BlogArticleRepository blogArticleRepo;
    @Autowired
    private BlogCardService blogCardService;

    public BlogArticle save(NewBlogArticleDTO body) throws IOException {

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
        blogCardService.save(savedBlog , body.desciption());

        return savedBlog;
    }


    public BlogArticle findByIdAndUpdate(UUID id , PUTBlogArticleDTO body) throws NotFoundException , IOException {
        BlogArticle found = findById(id);

        found.setTitolo(body.titolo());
        found.setSvillupatore(body.svillupatore());
        found.setPubblicazione(body.pubblicazione());
        try {
            found.setTema(Tema.valueOf(body.tema().trim().toUpperCase()));
        }catch (IllegalArgumentException ex){
            throw new BadRequestException("tema non valido");
        }

        found.setStoria(body.storia());
        found.setEsperienza(body.esperienza());
        found.setConsigli(body.consigli());

        String[] generi = body.genere().split(",");
        Set<Genere> generiSet = new HashSet<>();

        for (String s : generi) {
            try {
                generiSet.add(Genere.valueOf(s.trim().toUpperCase()));
            } catch (IllegalArgumentException ex) {
                throw new BadRequestException("genere " + s + " non valido");
            }
        }

        found.setGenresList(generiSet);

        found.setComments(new ArrayList<>());




        return blogArticleRepo.save(found);
    }



    public BlogArticle findById(UUID id) throws NotFoundException{
        return blogArticleRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException{
        BlogArticle found = findById(id);
        blogArticleRepo.delete(found);
    }

    public Page<BlogArticle> getAllArticle(int page , int size , String order){
        Pageable pageable = PageRequest.of(page, size , Sort.by(order));
        return blogArticleRepo.findAll(pageable);
    }


}

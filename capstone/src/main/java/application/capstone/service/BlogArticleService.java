package application.capstone.service;

import application.capstone.entities.BlogArticle;

import application.capstone.entities.BlogCard;
import application.capstone.entities.User;
import application.capstone.enums.Genere;

import application.capstone.enums.Tema;
import application.capstone.exceptions.BadRequestException;
import application.capstone.exceptions.NotFoundException;
import application.capstone.payloads.NewBlogArticleDTO;

import application.capstone.payloads.PUTBlogArticleDTO;

import application.capstone.repositories.BlogArticleRepository;
import application.capstone.repositories.BlogCardRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.*;

@Service
public class BlogArticleService {

    @Autowired
    private BlogArticleRepository blogArticleRepo;
    @Autowired
    private BlogCardRepository blogCardRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private Cloudinary cloudinary;

    public BlogArticle save(NewBlogArticleDTO body) throws IOException {

        BlogArticle newBlog = new BlogArticle();

        User userBlog = userService.findById(body.user());


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
        newBlog.setUser(userBlog);

        BlogArticle savedBlog = blogArticleRepo.save(newBlog);
        BlogCard newBlogCard = new BlogCard();

        newBlogCard.setTitolo(savedBlog.getTitolo());
        newBlogCard.setGenere(savedBlog.getTema());
        newBlogCard.setDescription(body.desciption());
        newBlogCard.setBlogArticle(savedBlog);

        blogCardRepo.save(newBlogCard);

        return savedBlog;
    }


    public BlogArticle findByIdAndUpdate(UUID id , PUTBlogArticleDTO body) throws NotFoundException , IOException {
        BlogArticle found = findById(id);

        found.setTitolo(body.titolo());
        found.setSvillupatore(body.svillupatore());
        found.setPubblicazione(body.pubblicazione());

        found.setTema(body.tema());

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
        BlogCard foundCard = found.getBlogCard();
        blogCardRepo.delete(foundCard);
        blogArticleRepo.delete(found);

    }

    public BlogArticle setPrimaryPicture(UUID id , MultipartFile file) throws IOException, NotFoundException {
        BlogArticle found = findById(id);
        BlogCard cardFound = found.getBlogCard();
        String newImage = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setImmaginePrimaria(newImage);
        cardFound.setCover(newImage);

        blogCardRepo.save(cardFound);
        return blogArticleRepo.save(found);
    }

    public BlogArticle setSecondaryPicture(UUID id , MultipartFile file) throws IOException, NotFoundException {
        BlogArticle found = findById(id);
        String newImage = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setImmagineSecondaria(newImage);
        return blogArticleRepo.save(found);
    }

    public Page<BlogArticle> getAllArticle(int page , int size , String order){
        Pageable pageable = PageRequest.of(page, size , Sort.by(order));
        return blogArticleRepo.findAll(pageable);
    }






}

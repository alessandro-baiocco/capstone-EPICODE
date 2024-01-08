package application.capstone.controllers;

import application.capstone.entities.BlogArticle;
import application.capstone.entities.User;
import application.capstone.exceptions.BadRequestException;
import application.capstone.exceptions.NotFoundException;
import application.capstone.payloads.NewBlogArticleDTO;
import application.capstone.payloads.PUTBlogArticleDTO;
import application.capstone.service.BlogArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/private/articles")
public class BlogArticleController {
    @Autowired
    private BlogArticleService blogArticleService;


    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CREATOR')")
    @ResponseStatus(HttpStatus.CREATED)
    public BlogArticle save(@RequestBody @Validated NewBlogArticleDTO body, @AuthenticationPrincipal User currentUser, BindingResult validation) throws BadRequestException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return blogArticleService.save(body, currentUser);
            } catch (IOException e) {
                throw new BadRequestException(validation.getAllErrors());
            }

        }

    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CREATOR')")
    public BlogArticle findByIdAndUpdate(@PathVariable UUID id, @RequestBody @Validated PUTBlogArticleDTO body, @AuthenticationPrincipal User currentUser, BindingResult validation) throws NotFoundException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return blogArticleService.findByIdAndUpdate(currentUser, id, body);
            } catch (IOException e) {
                throw new RuntimeException("problema lato server");
            }
        }
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findByIdAndDelete(@PathVariable UUID id) throws NotFoundException {
        blogArticleService.findByIdAndDelete(id);
    }


    @PatchMapping("/{id}/primary")
    public BlogArticle changePrimaryPicture(@PathVariable UUID id, @RequestParam("picture") MultipartFile body) throws NotFoundException, IOException {
        return blogArticleService.setPrimaryPicture(id, body);
    }

    @PatchMapping("/{id}/secondary")
    public BlogArticle changeSecondaryPicture(@PathVariable UUID id, @RequestParam("picture") MultipartFile body) throws NotFoundException, IOException {
        return blogArticleService.setSecondaryPicture(id, body);
    }


}

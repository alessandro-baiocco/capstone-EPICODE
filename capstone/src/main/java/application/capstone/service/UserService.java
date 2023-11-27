package application.capstone.service;


import application.capstone.entities.User;
import application.capstone.enums.Genere;
import application.capstone.enums.Role;
import application.capstone.exceptions.BadRequestException;
import application.capstone.exceptions.NotUserFoundException;
import application.capstone.payloads.NewUserDTO;
import application.capstone.payloads.PutUserDTO;
import application.capstone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;




    public User findByEmail(String email){
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new NotUserFoundException("Utente con email " + email + " non trovato!"));
    }
    public User findByUserName(String userName){
        return userRepo.findByUserName(userName)
                .orElseThrow(() -> new NotUserFoundException("Utente con userName " + userName + " non trovato!"));
    }



    public User save(NewUserDTO body) {
        userRepo.findByEmail(body.email()).ifPresent( user -> {
            try {
                throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });
        userRepo.findByUserName(body.userName()).ifPresent( user -> {
            try {
                throw new BadRequestException("L'username " + user.getUsername() + " è già utilizzato!");
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });

        User newUser = new User();


        newUser.setCognome(body.cognome());
        newUser.setNome(body.nome());
        newUser.setUsername(body.userName());
        newUser.setPassword(body.password());
        newUser.setEmail(body.email());
        newUser.setRuolo(Role.USER);

        if(body.generePreferito() != null){
            try {
                newUser.setGenerePreferito(Genere.valueOf(body.generePreferito().toUpperCase()));
            }catch ( IllegalArgumentException ex){
                throw new BadRequestException("genere non valido");
            }

        }


        return userRepo.save(newUser);
    }


    public User findByIdAndUpdate(int id , PutUserDTO body) throws NotUserFoundException{
        User found = findById(id);
        if (found.getAvatar().equals("https://ui-avatars.com/api/?name=" + found.getNome().replace(" " , "") + "+" + found.getCognome().replace(" " , ""))){
            found.setAvatar("https://ui-avatars.com/api/?name=" + body.nome().replace(" " , "") + "+" + body.cognome().replace(" " , ""));
        }
        found.setNome(body.nome());
        found.setCognome(body.cognome());
        found.setPassword(body.password());
        found.setUsername(body.userName());
        if(body.generePreferito() != null){
            try {
                found.setGenerePreferito(Genere.valueOf(body.generePreferito().toUpperCase()));
            }catch ( IllegalArgumentException ex){
                throw new BadRequestException("genere non valido");
            }

        }
        return  userRepo.save(found);
    }



    public User findById(int id) throws NotUserFoundException{
        return userRepo.findById(id).orElseThrow(() -> new NotUserFoundException(id));
    }




    public Page<User> getAllUser(int page , int size , String order){
        Pageable pageable = PageRequest.of(page, size , Sort.by(order));
        return userRepo.findAll(pageable);
    }





    public void findByIdAndDelete(int id) throws NotUserFoundException{
        User found = findById(id);
        userRepo.delete(found);
    }








}
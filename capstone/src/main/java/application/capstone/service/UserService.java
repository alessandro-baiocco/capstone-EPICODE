package application.capstone.service;


import application.capstone.entities.User;
import application.capstone.enums.Genere;
import application.capstone.enums.Role;
import application.capstone.exceptions.BadRequestException;
import application.capstone.exceptions.NotFoundException;
import application.capstone.payloads.NewUserDTO;
import application.capstone.payloads.PUTUserDTO;
import application.capstone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;




    public User findByEmail(String email){
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));
    }
    public User findByUserName(String userName){
        return userRepo.findByUsername(userName)
                .orElseThrow(() -> new NotFoundException("Utente con userName " + userName + " non trovato!"));
    }



    public User save(NewUserDTO body) throws IOException {
        userRepo.findByEmail(body.email()).ifPresent( user -> {
            try {
                throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });
        userRepo.findByUsername(body.userName()).ifPresent( user -> {
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
        newUser.setComments(new ArrayList<>());


        return userRepo.save(newUser);
    }


    public User findByIdAndUpdate(UUID id , PUTUserDTO body) throws NotFoundException , IOException{
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



    public User findById(UUID id) throws NotFoundException{
        return userRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }




    public Page<User> getAllUser(int page , int size , String order){
        Pageable pageable = PageRequest.of(page, size , Sort.by(order));
        return userRepo.findAll(pageable);
    }





    public void findByIdAndDelete(UUID id) throws NotFoundException{
        User found = findById(id);
        userRepo.delete(found);
    }








}
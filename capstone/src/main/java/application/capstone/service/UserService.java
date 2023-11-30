package application.capstone.service;


import application.capstone.entities.User;
import application.capstone.enums.Genere;
import application.capstone.exceptions.BadRequestException;
import application.capstone.exceptions.NotFoundException;
import application.capstone.payloads.PUTUserDTO;
import application.capstone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
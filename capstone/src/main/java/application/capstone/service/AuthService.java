package application.capstone.service;

import application.capstone.entities.User;
import application.capstone.enums.Genere;
import application.capstone.enums.Role;
import application.capstone.exceptions.BadRequestException;
import application.capstone.exceptions.UnauthorizedException;
import application.capstone.payloads.NewUserDTO;
import application.capstone.payloads.UserLoginDTO;
import application.capstone.repositories.UserRepository;
import application.capstone.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class AuthService {
    @Autowired
    private UserService usersService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUser(UserLoginDTO body){
        User user = usersService.findByUserName(body.userName());


        if(bcrypt.matches(body.password(), user.getPassword())){

            return jwtTools.createToken(user);
        }else {

            throw new UnauthorizedException("credenziali non valide!");
        }


    }


    public User register(NewUserDTO body) throws IOException {
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
        newUser.setAvatar("https://ui-avatars.com/api/?name=" + body.nome().replace(" " , "") + "+" + body.cognome().replace(" " , ""));
        newUser.setNome(body.nome());
        newUser.setUsername(body.userName());
        newUser.setPassword(bcrypt.encode(body.password()));
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






}

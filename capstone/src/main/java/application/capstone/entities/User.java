package application.capstone.entities;

import application.capstone.enums.Genere;
import application.capstone.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@JsonIgnoreProperties({"password" , "comments", "enabled", "credentialsNonExpired", "accountNonExpired", "accountNonLocked"})
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    private String cognome;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Comment> comments;
    private String password;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private Genere generePreferito;
    private String username;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role ruolo;
    @JsonIgnore
    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<BlogArticle> blogs;


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setGenerePreferito(Genere generePreferito) {
        this.generePreferito = generePreferito;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRuolo(Role ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.ruolo.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

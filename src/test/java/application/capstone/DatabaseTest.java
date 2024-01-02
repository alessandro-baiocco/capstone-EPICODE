package application.capstone;

import application.capstone.entities.BlogArticle;
import application.capstone.enums.Genere;
import application.capstone.enums.Tema;
import application.capstone.exceptions.BadRequestException;
import application.capstone.exceptions.NotFoundException;
import application.capstone.payloads.NewBlogArticleDTO;
import application.capstone.repositories.BlogArticleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
public class DatabaseTest {

    @Autowired
    private BlogArticleRepository blogArticleRepo;

    static BlogArticle blogArticle;



//test nel salvatagio di un nuovo blog
    @Test
    public void save() {
        BlogArticle newBlog = new BlogArticle();

        newBlog.setTitolo("fawfwa");
        newBlog.setSvillupatore("wgagawgwag");
        newBlog.setPubblicazione("awfgwgagawg");
        try {
            newBlog.setTema(Tema.valueOf("FANTASCIENTIFICO"));
        }catch (IllegalArgumentException ex){
            throw new BadRequestException("tema non valido");
        }

        newBlog.setStoria("awogfbapowgbawoèigbwaèpogibapèsigbèapowsibgèaskoplgbaèoiwebglak<sbgsalkbgaèlwkibfsoibawovfgbaousvbfoabnglisbgv" +
                "oaibvwgoasknmèopiashfgpaighapèsoibèaopiwbgèoaipbsgflkbawgoibawgoipbawègpoibasèlàkbfèaowibgoipaslkbgaibwgoèipansgèoiabwèg" +
                "oibasoivbgaowibgèipoabwèàipogbalòkàsbngg+pagbihw+pgba+pobnsg+poawbg+paobnsèopiklabwpigbhaspojngpoiab+w+gpbas+pgobn+apwobig+apsoibg");//cerco di capire come storare enormi testi nel database
        newBlog.setEsperienza("oèwpjafèogawognèagjwawgpèajwgèpg");
        newBlog.setConsigli("awfgawgsgapwihogawpohgjpèoag");


        Set<Genere> generes = new HashSet<>();
        generes.add(Genere.SPARATUTTO);
        newBlog.setGenresList(generes);


        newBlog.setComments(new ArrayList<>());

        BlogArticle savedBlog = blogArticleRepo.save(newBlog);
        System.out.println(savedBlog.getId()); // volevo vedere se potevo ottenere l'id dell'oggetto salvato
        System.out.println(savedBlog.getStoria());

    }


    @ParameterizedTest
    @CsvSource({"6d3bfd4b-7ad1-4967-9b20-110cddab2b4e"})
    public void findById(UUID id) throws NotFoundException {
        System.out.println( blogArticleRepo.findById(id).orElseThrow(() -> new NotFoundException(id)));//controllo se i contenuti vengono estratti dal DB corretamente
    }





}

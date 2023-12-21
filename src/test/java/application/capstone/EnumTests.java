package application.capstone;

import application.capstone.enums.Genere;
import application.capstone.enums.Tema;
import application.capstone.exceptions.BadRequestException;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnumTests {



    //piccolo esperimento per comprendere meglio come funziona il valueOf()
    @ParameterizedTest
    @CsvSource({ "sparatutto, ROUGELIKE, ZOMBIE","ROUGELIKE , ROUGELIKE, ZOMBIE", "3, 3, 6"})
    void testListEnum(String str ){
        Set<Genere> genereSet = new HashSet<>();
        System.out.println(str);
        String[] generi = str.split(",");
        System.out.println(Arrays.toString(generi));
        try {
            for (int i = 0 ; i < generi.length ; i++ ){
                System.out.println(generi[i]);
                System.out.println(Genere.valueOf(generi[i].toUpperCase()));
                genereSet.add(Genere.valueOf(generi[i].toUpperCase()));

            }
            System.out.println(genereSet);

        }catch (IllegalArgumentException ex){
            System.out.println("genere non valido");
            throw new BadRequestException("generi non validi");
        }

    }



    @ParameterizedTest
    @CsvSource({ "FANTASCIENTIFICO","POSTAPOCALITTICO"})
    public void isValid(String str) {
        Tema tema = Tema.valueOf(str);
        List<Tema> temi = Arrays.stream(Tema.values()).toList();
        System.out.println(temi.contains(tema));
    } // testo la validation class 
}









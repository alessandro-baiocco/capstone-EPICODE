package application.capstone;

import application.capstone.enums.Genere;
import application.capstone.exceptions.BadRequestException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.HashSet;
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



}

package com.hmvss.api.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class Utility {

    /**
     * Metodo utilitario para generar una clave dinamica.
     * @return String
     * @throws ParseException
     */
    public String passwordGenerator(){
        log.debug("entrando en PasswordGenerator");
        return generateRandomPassword(8);
    }

    /**
     * Metodo utilitario para generar una clave dinamica.
     * @return String
     * @throws ParseException
     */
    public  String generateRandomPassword(int len) {
        // Rango ASCII – alfanumérico (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // cada iteración del bucle elige aleatoriamente un carácter del dado
        // rango ASCII y lo agrega a la instancia `StringBuilder`

        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }

    public Boolean getvalidationAge(LocalDate bornDate){
        boolean olderThan18;
        int age = Period.between(bornDate, LocalDate.now()).getYears();
        // Validar si la edad es menor o igual a 18
        if (age <= 18) {
            olderThan18 = false;
            System.out.println("La persona es menor o igual a 18 años.");
        } else {
            olderThan18 = true;
            System.out.println("La persona es mayor de 18 años.");
        }
        return olderThan18;
    }
}

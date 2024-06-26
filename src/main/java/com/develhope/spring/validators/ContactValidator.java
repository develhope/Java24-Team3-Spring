package com.develhope.spring.validators;

import com.develhope.spring.exceptions.InvalidContactException;
import org.springframework.stereotype.Component;

@Component
public class ContactValidator {

    public void validateEmail(String email) throws InvalidContactException {
        if (email == null || email.isEmpty()) {
            throw new InvalidContactException("Email field must not be empty");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidContactException("Invalid email: " + email);
        }
    }

    public void validatePassword(String password) throws Exception {
        if (password == null || password.isEmpty()) {
            throw new InvalidContactException("Password field must not be empty");
        }

        if (!password.matches("(?=.*[A-Z])(?=.*[@#$%^&+=!?])(?=.*[0-9])(?=\\S+$).{8,255}")) {
            throw new InvalidContactException("Invalid password: " + password +
                    ". Password must be at least 8 character long, must have at least 1 uppercase character, " +
                    "1 lowercase character, 1 digit and 1 special character (@#$%^&+=!?))");
        }
    }

    public void validatePhoneNumber(String phoneNumber) throws Exception {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new InvalidContactException("phoneNumber must not be empty");
        }

        if (phoneNumber.replace(" ", "").length() != phoneNumber.length()) {
            throw new InvalidContactException("phoneNumber must not have spaces");
        }

        /* COMPOSIZIONE DI UN NUMERO DI TELEFONO VALIDO A LIVELLO INTERNAZIONALE:
        - Codice Paese: Normalmente va da 1 a 3 cifre.
        - Numero di Telefono Locale: Può variare considerevolmente in lunghezza, quindi si accetta una lunghezza variabile.
        - Estensioni: Alcuni numeri di telefono possono avere un'estensione (ad esempio, +1234567890x123), quindi viene gestita con (?:x.+)?.
         */
        if (!phoneNumber.matches("^\\+\\d{1,3}\\d{4,14}(?:x.+)?$")) {
            throw new InvalidContactException("Invalid phone number: " + phoneNumber +
                    ". PhoneNumber must have a prefix");
        }

    }


}

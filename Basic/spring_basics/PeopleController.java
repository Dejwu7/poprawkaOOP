package com.example.spring_basics;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("person")
public class PeopleController {
    @GetMapping()
    public String get(){
        return "person"; //musi istnieć template thymeleaf
    }

    @GetMapping("/people")
    public String getPeople(Model model) { //Spring daje nam obiekt 'Model',
        // do którego możemy dodać dane, które chcemy wprzekazać do szablonu HTML
        model.addAttribute("people", 5); //Tutaj dodajemy do modelu coś, co nazywamy 'people' i przypisujemy
        //temu wartość 5. To jest jak wkładanie danych do pudełka z etykietą "person".
        return "people"; //okej, znajdź szablon HTML o nazwie "people.html", przekaż do niego dane i wyświetl
    }
}

//MVC (Model-View-Controller) to wzorzec architektoniczny, który
//dzieli aplikację na trzy główne komponenty
//1. Model - zarządza danymi, reaguje na polecenia od
//kontrolera i informuje widok o zmianach w danych
//np. produkty, zamówienia, użytkownicy
//2. View (Widok) odpowiada za prezentację danych użytkownikowi
//np. strona html, która wyświetla listę produktów
//3. Controller - obsługuje np.
//dodawanie produktów do koszuka, aktualizację zamówień itp
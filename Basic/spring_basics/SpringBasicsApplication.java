package com.example.spring_basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBasicsApplication {

	public static void main(String[] args) {

		SpringApplication.run(
				SpringBasicsApplication.class, args);
	}

}
//Adnotacja @SpringBootApplication odpowiada
// trzem kolejnym adnotacjom:
//@Configuration - pozwala używac adnotacji do
// wstrzykiwania zależności.
//@EnableAutoConfiguration - konfiguruje Springa
//ze względu na przekazane ustawienia
//@ComponentScan - mówi springowi, aby szukał
// komponentów, konfiguracji

//W springu aby utworzyć usługę restową
//trzeba skorzystać z kontrollera @RestController,
//który obsłuży zapytania HTTP

//@RequestMapping zapewnia, że żądanie HTTP np pod adres
//localhose:8080/ będzie obsłużone przez metodę
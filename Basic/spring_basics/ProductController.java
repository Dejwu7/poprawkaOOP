package com.example.spring_basics;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Kontroler w Spring Boot, który zajmuje się produktami
@RestController //Mówi springowi, że ta klasa będzie odpowiedzialna za obsługę żądań sieciowych
@RequestMapping("product") // Wszystkie żądania zaczynające się od /product będą trafiać do tego kontrolera

public class ProductController {

    //Tworzę stały produkt, który zawsze będzie zwracany jako odpowiedź
    private final Product product = new Product(
            "Chicken",
            10,
            new Product.ProductionLabel("PL123456", LocalDate.of(2024, 5, 22)));
    // tworzenie instancji obiektu w przypadku rekordów zagnieżdżonych

    private final List<Product> products = new ArrayList<>();
    //będziemy mapować i udostępniać listę produktów
    public ProductController() {
        // Dodawanie produktów do listy
        products.add(
                new Product(
                        "Chicken",
                        10,
                        new Product.ProductionLabel("PL123456", LocalDate.of(2024, 5, 22))
                )
        );

        products.add(
                new Product(
                        "Turnip",
                        3,
                        new Product.ProductionLabel("A random guy", LocalDate.of(2024, 5, 20))
                )
        );
    }

    //Mapujemy żądania HTTP typu GET na tę metodę
    //Żądania mogą być wysyłane na /product, /product/ lub /product/item
    @GetMapping({"product", "/", "item"})
    Product getItem(){
        return product;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return products;
    }

    //metoda post
    @PostMapping()
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        products.add(product);
        return new ResponseEntity<>("Product added", HttpStatus.CREATED);
        //np. $ curl -X POST \
        // http://localhost:1212/product \
        // -H 'Content-Type: application/json' \
        // -d '{ "name": "Honey",
        // "quantity": 50,
        // "productionLabel": {
        // "producer": "Bees",
        // "productionDate": "2024-05-20" } }'
    }

    //POST - używane do tworzenia nowych zasobów
    //PUT - używana do aktualizacji istniejącego zasobu
    @PutMapping("{index}")
    public ResponseEntity<String> updateProduct (
            @PathVariable int index, @RequestBody Product update
    ) {
        if(index<0 || index >= products.size()){
            return ResponseEntity.notFound().build();
        } else {
            this.products.set(index, update);
            return ResponseEntity.ok("Product updated successfuly");
        }
    }

    //Metoda delete nie różni się bardzo
    @DeleteMapping("{index}")
    public ResponseEntity<String> deleteProduct(@PathVariable int index){
        if(index<0 || index >= products.size()){
            return ResponseEntity.notFound().build();
        } else {
            this.products.remove(index);
            return ResponseEntity.ok("Product deleted");
        }
    }
}

//Mapping to sposób, w jaki Spring decyduje, która metoda
//kontrolera ma zostać wywołana w odpowiedzi na określoną ścieżkę
//URL i typ żądania HTTP (GET, POST, itp)
//@RequestMapping określa podstawową ścieżkę, którą kontroler obsługuje
//@GetMapping określa, które dokładnie ścieżki URL wewnątrz tej
//podstawowej ścieżki wywołują daną metodę, jeśli ktoś użyje żądania GET
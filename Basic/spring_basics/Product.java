package com.example.spring_basics;

import java.time.LocalDate;

//prosty rekord produkt, który przechowuje dane o produkcie
public record Product(String name, int quantity,
                      ProductionLabel productionLabel) {
    public record ProductionLabel(String producer, LocalDate productionDate) {}
    //ProductionLabel jest tzw rekordem zagnieżdżonym
}


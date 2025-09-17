package org.example.server;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ChartGenerator {

    public static byte[] createChart(String line, int chartNumber) {
        // Stworzenie datasetu dla wykresu
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Podziel linie na wartości
        String[] values = line.split(",");

        for (int i = 0; i < values.length; i++) {
            dataset.addValue(Double.parseDouble(values[i]), "Value", Integer.toString(i + 1));
        }

        // Utworzenie wykresu
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Wykres Linii " + chartNumber, // Tytuł wykresu
                "X-Axis", // Etykieta osi X
                "Y-Axis", // Etykieta osi Y
                dataset, // Dane
                PlotOrientation.VERTICAL,
                true, true, false);

        // Zapisz wykres do ByteArrayOutputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ChartUtils.writeChartAsPNG(baos, lineChart, 800, 600);
        } catch (IOException e) {
            System.out.println("Problem z generowaniem wykresu: " + e.getMessage());
        }

        // Zwróć tablicę bajtów
        return baos.toByteArray();
    }
}



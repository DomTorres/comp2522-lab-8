package ca.bcit.comp2522.lab8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Quiz9
{
    private static final String pathToTextFile = "country.txt";
    private static final String letterBoundOne = "A";
    private static final String letterBoundTwo = "T";
    private static final String nonVowels = "[^AEIOUaeiou]";
    private static final String emptyString = "";
    private static final int exactLength = 2;

    public static void main(String[] args)
    {
        final Path countriesPath;
        final String[] arrayCountries;
        final List<String> listOfCountries;
        final List<String> test;
        List<String> countries; // Error when made final

        // Read text file named "country.txt"
        countriesPath = Paths.get("week8countries.txt");

        countries = null;

        try
        {
            countries = Files.readAllLines(countriesPath);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }

        // a) Collect in an array, all Countries that start with A or T, in uppercase, sorted by length
        arrayCountries = filteredStream(countries)
                .filter(c -> !c.isBlank())
                .filter(c -> c.startsWith(letterBoundOne) || c.startsWith(letterBoundTwo))
                .map(String::toUpperCase)
                .sorted(Comparator.comparing(String::length))
                .toArray(String[]::new);

        // Print to check output
        for(final String country : arrayCountries)
        {
            System.out.println(country);
        }

        // b) Print in reverse all countries that contain exactly 2 vowels
        listOfCountries = filteredStream(countries)
                .filter(c -> !c.isBlank())
                .filter(c -> c.replaceAll(nonVowels, emptyString).length() == exactLength)
                .sorted(Comparator.reverseOrder())
                .toList();

        listOfCountries.forEach(System.out::println);
    }

    private static Stream<String> filteredStream(final List<String> countries)
    {
        return countries.stream().filter(c -> c != null);
    }
}

package ca.bcit.comp2522.lab8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Main
{
    private static final String DIR_NAME = "matches";

    private static final int LONG_COUNTRY_LEN = 10;
    private static final int SHORT_COUNTRY_LEN = 5;
    private static final char FIRST_CHAR = 'A';
    private static final String END_WORD = "land";
    private static final String CONTAINS_WORD = "united";
    private static final int NUM_OF_WORDS = 1;
    private static final String FIRST_CHAR_QUESTION_FIFTEEN = "Z";
    private static final int MIN_LENGTH = 3;


    public static void main(String[] args)
    {
        final Path countriesPath;
        final Path matches;
        final Path data;
        List<String> countries;

        // Read a text file named "week8countries.txt",
        countriesPath = Paths.get("week8countries.txt");

        // If the subdirectory "matches" does not exist, create it.
        matches = Paths.get(DIR_NAME);

        if(Files.notExists(matches))
        {
            try
            {
                Files.createDirectory(matches);
            }
            catch(final IOException e)
            {
                e.printStackTrace();
            }
        }

        //  Write results into a file "data.txt" within the "matches" directory.
        data = Paths.get(DIR_NAME, "data.txt");

        if(Files.notExists(data))
        {
            try
            {
                Files.createFile(data);
            }
            catch(final IOException e)
            {
                e.printStackTrace();
            }
        }

        // Read the File: Read all lines from "countries.txt" into a list.
        countries = null;

        try
        {
            countries = Files.readAllLines(countriesPath);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }

        // 1. Long Country Names
        try
        {
            Files.writeString(data,
                    "Country names longer than " + LONG_COUNTRY_LEN + " characters:\n",
                    StandardOpenOption.APPEND);

            final List<String> longCountryNames;

            longCountryNames = filteredStream(countries)
                    .filter(country -> country.length() > LONG_COUNTRY_LEN)
                    .toList();

            Files.write(data,
                    longCountryNames,
                    StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }

        // 2. Short Country Names
        try
        {
            Files.writeString(data,
                    "Country names shorter than " + SHORT_COUNTRY_LEN + " characters:\n",
                    StandardOpenOption.APPEND);

            final List<String> shortCountryNames;

            shortCountryNames = filteredStream(countries)
                    .filter(country -> country.length() < SHORT_COUNTRY_LEN)
                    .toList();

            Files.write(data,
                    shortCountryNames,
                    StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }

        // 3. Country names that start with "A"
        try
        {
            Files.writeString(data,
                    "Country names starting with '" + FIRST_CHAR + "':\n",
                    StandardOpenOption.APPEND);

            final List<String> countryNamesStartWithA;

            countryNamesStartWithA = filteredStream(countries)
                    .filter(country -> country.startsWith("A"))
                    .toList();

            Files.write(data,
                    countryNamesStartWithA,
                    StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }

        // 4. All country names that end with land
        try
        {
            Files.writeString(data,
                    "\n4. Country names that end with '" + END_WORD + "':\n",
                    StandardOpenOption.APPEND);

            final List<String> countryNamesEndWithLand;

            countryNamesEndWithLand = filteredStream(countries)
                    .filter(country -> country.endsWith(END_WORD))
                    .toList();

            Files.write(data,
                    countryNamesEndWithLand,
                    StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }

        // 5. Contains the word "United"
        try
        {
            Files.writeString(data,
                    "\n5. Country names that contain the word " + CONTAINS_WORD + ":\n",
                    StandardOpenOption.APPEND);

            final List<String> countryNamesContainsUnited;

            countryNamesContainsUnited = filteredStream(countries)
                    .filter(country -> country.toLowerCase().contains(CONTAINS_WORD))
                    .toList();

            Files.write(data,
                    countryNamesContainsUnited,
                    StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }

        // 6. List all country names in alphabetical order
        try
        {
            Files.writeString(data,
                    "\n6. Country names in alphabetical order\n",
                    StandardOpenOption.APPEND);

            final List<String> countryNamesSorted;

            countryNamesSorted = filteredStream(countries)
                    .filter(country -> !country.isBlank())
                    .sorted()
                    .toList();

            Files.write(data,
                    countryNamesSorted,
                    StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }

        // 7. List all country names in reverse alphabetical order
        try
        {
            Files.writeString(data,
                    "\n7. Country names in reverse alphabetical order:\n",
                    StandardOpenOption.APPEND);

            final List<String> countryNamesReverse;

            countryNamesReverse = filteredStream(countries)
                    .filter(country -> !country.isBlank())
                    .sorted(Comparator.reverseOrder())
                    .toList();

            Files.write(data,
                    countryNamesReverse,
                    StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }

        // 8. Unique first letters of all country names
        try
        {
            Files.writeString(data,
                    "\n8. List all the unique first letters of all country names.\n",
                    StandardOpenOption.APPEND);

            final List<String> uniqueFirstLetters;

            uniqueFirstLetters = countries.stream()
                    .map(country -> country.substring(0, 1))
                    .distinct()
                    .toList();

            Files.write(data,
                    uniqueFirstLetters,
                    StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }

        // 9. Total count of country names
        try
        {
            Files.writeString(data,
                    "\n9. Total number of countries: \n",
                    StandardOpenOption.APPEND);

            final long totalCountryNames;
            final String totalCountryNamesString;

            totalCountryNames = filteredStream(countries)
                    .filter(country -> !country.isBlank())
                    .count();

            totalCountryNamesString = "" + totalCountryNames;

            Files.writeString(data,
                    totalCountryNamesString + System.lineSeparator(),
                    StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }

        // 10. Longest Country name
        try
        {
            Files.writeString(data,
                    "\n10. Longest Country name: \n",
                    StandardOpenOption.APPEND);

            final Optional<String> longestCountryName;

            longestCountryName = filteredStream(countries)
                    .filter(country -> !country.isBlank())
                    .max(Comparator.comparingInt(String::length));

            longestCountryName.ifPresent(name ->
            {
                try
                {
                    Files.writeString(data,
                                    name + System.lineSeparator(),
                                    StandardOpenOption.APPEND);
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
            );
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }

        // 11. Shortest Country Name
        try
        {
            Files.writeString(data,
                    "\n11. Shortest Country name: \n",
                    StandardOpenOption.APPEND);

            final Optional<String> shortestCountryName;

            shortestCountryName = filteredStream(countries)
                    .filter(country -> !country.isBlank())
                    .min(Comparator.comparingInt(String::length));

            shortestCountryName.ifPresent(name ->
                    {
                        try
                        {
                            Files.writeString(data,
                                    name + System.lineSeparator(),
                                    StandardOpenOption.APPEND);
                        }
                        catch(IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
            );
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }

        // 12. All Country names in uppercase
        try
        {
            Files.writeString(data,
                    "\n12. Country names in all uppercase: \n",
                    StandardOpenOption.APPEND);

            final List<String> countryNamesUppercase;

            countryNamesUppercase = filteredStream(countries)
                    .filter(country -> !country.isBlank())
                    .toList();

            countryNamesUppercase.forEach(name ->
                {
                    try
                    {
                        Files.writeString(data,
                                name.toUpperCase() + System.lineSeparator(),
                                StandardOpenOption.APPEND);
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            );

        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }

        // 13. Countries with More than One word
        try
        {
            Files.writeString(data,
                    "\n13. Countries with words greater than " + NUM_OF_WORDS +":\n",
                    StandardOpenOption.APPEND);

            final List<String> listOfCountries;

            listOfCountries = filteredStream(countries)
                    .filter(country -> !country.isBlank())
                    .filter(country -> country.contains(" "))
                    .toList();

            Files.write(data,
                    listOfCountries,
                    StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }

        // 14. Map each country name to its character count
        try
        {
            Files.writeString(data,
                    "\n14. Country with their Character count: \n",
                    StandardOpenOption.APPEND);

            final List<String> countryNames;

            countryNames = filteredStream(countries)
                    .filter(country -> !country.isBlank())
                    .toList();

            countryNames.forEach(name ->
                    {
                        try
                        {
                            final String nameWithCount = name + ": " + name.length() + " Characters";
                            Files.writeString(data,
                                    nameWithCount + System.lineSeparator(),
                                    StandardOpenOption.APPEND);
                        }
                        catch(IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
            );
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }

        // 15. True if any country name starts with "Z"
        try
        {
            Files.writeString(data,
                    "\n15. True if any country starts with " + FIRST_CHAR_QUESTION_FIFTEEN + ":\n",
                    StandardOpenOption.APPEND);

            final boolean isInListOfCountries;
            final String isInListOfCountriesString;

            isInListOfCountries = filteredStream(countries)
                    .anyMatch(country -> country.startsWith(FIRST_CHAR_QUESTION_FIFTEEN));

            isInListOfCountriesString = isInListOfCountries + System.lineSeparator();

            Files.writeString(data,
                    isInListOfCountriesString,
                    StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }

        // 16. True if all country names are longer than 3 characters
        try
        {
            Files.writeString(data,
                    "\n16. True if all country has more than " + MIN_LENGTH + " characters\n",
                    StandardOpenOption.APPEND);

            final boolean isInListOfCountries;
            final String isInListOfCountriesString;

            isInListOfCountries = filteredStream(countries)
                    .allMatch(country -> country.length() > MIN_LENGTH);

            isInListOfCountriesString = isInListOfCountries + System.lineSeparator();

            Files.writeString(data,
                    isInListOfCountriesString,
                    StandardOpenOption.APPEND);
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }

    private static Stream<String> filteredStream(final List<String> strings)
    {
        return strings.stream()
                .filter(s -> s != null);
    }
}
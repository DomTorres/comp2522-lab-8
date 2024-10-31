package ca.bcit.comp2522.lab8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main
{
    private static final String DIR_NAME = "matches";

    public static void main(String[] args)
    {
        final Path countriesPath;
        List<String> countries;
        final Path matches;
        final Path data;

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


    }
}
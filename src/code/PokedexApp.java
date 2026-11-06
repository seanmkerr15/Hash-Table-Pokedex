package code;
import java.io.IOException;
import java.util.Scanner;

public class PokedexApp {
	
	public static ChainingHashTable<String, Pokemon> pokedex = new ChainingHashTable<>(11);
	
    public static void main(String[] args) throws IOException {
    	
        
        String filename = args.length == 0 ? "pokemon.csv" : args[0];
        try {
            int loaded = PokemonLoader.load(filename, pokedex);
            System.out.println("Loaded " + loaded + " Pokemon records.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Could not load CSV: " + e.getMessage());
            System.out.println("Put pokemon.csv in the project root (working directory).");
            return;
        }

        Scanner input = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n1. Search\n2. Add/update\n3. Remove\n4. Show buckets\n0. Exit");
            System.out.print("Choice: ");
            String choice = input.nextLine().trim();
            switch (choice) {
                case "1": searchPokemon(input); break;
                case "2": addOrUpdatePokemon(input); break;
                case "3": removePokemon(input); break;
                case "4": pokedex.printTable(); break;
                case "0": running = false; break;
                default: System.out.println("Choose 0 through 4.");
            }
        }
    }

     static void searchPokemon(Scanner input) {
        // TODO 1: Complete this menu action.
    	System.out.println("Please enter a name: ");
    	String choice = input.next().trim().toLowerCase();
    	Pokemon p = pokedex.get(choice);
    	if (p == null) {
    		System.out.println("Not Found");
    	}
    	else {
    		System.out.println("Found: " + p);
    	}
     
    }

    private static void addOrUpdatePokemon(Scanner input) {
    	String name = "";
    	while (name.isEmpty()) {
    		System.out.println("Please enter the name of a Pokemon: ");
    		name = PokemonLoader.normalizeName(input.next());
    	}
        //additional details to create pokemon.
    	 int id = readNonNegativeInt(input, "Please enter the Pokemon ID: ");
    	 int height = readNonNegativeInt(input, "Please enter the height: ");
    	 int weight = readNonNegativeInt(input, "Please enter the weight: ");
    	 int exp = readNonNegativeInt(input, "Please enter the base experience: ");
    	 
    	 //Checks if name existed(alike hashcodes have been dealt with in code)
    	 boolean existed = pokedex.get(name) != null;
    	    
        Pokemon p = new Pokemon(id, name, height, weight, exp);
        pokedex.insert(name, p);
        
        if (existed) {
        	System.out.println(name + " has been updated");
        	System.out.println(pokedex.get(name));
        }
        else {
        	System.out.println(name + " has been added");
        	System.out.println(pokedex.get(name));
        }
        
    }

    private static void removePokemon(Scanner input) {
        System.out.println("Please input a Pokemon to remove: ");
        String choice = input.next().trim().toLowerCase();
        Pokemon p = pokedex.get(choice);
        if (p == null) {
        	System.out.println("Pokemon could not be found");
        }
        else {
        	pokedex.remove(choice);
        	System.out.println("Pokemon has successfully been removed.");
        	
        }
        
    }

    // Provided: reads a non-negative integer using nextLine(), avoiding leftover newlines.
    private static int readNonNegativeInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(input.nextLine().trim());
                if (value >= 0) { return value; }
            } catch (NumberFormatException e) {
                // Explain the error and ask again.
            }
            System.out.println("Enter a whole number of zero or more.");
        }
    }
}

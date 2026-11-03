package code;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

public class PokemonLoader {
    // Names use lowercase identifiers such as pikachu and charizard.
    public static String normalizeName(String name) {
        return name.trim().toLowerCase(Locale.ROOT);
    }

    public static int load(String filename, ChainingHashTable<String, Pokemon> table)
            throws IOException {
        int count = 0;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8)) {
            String header = reader.readLine();
            if (!"id,name,height_dm,weight_hg,base_experience".equals(header)) {
                throw new IOException("Unexpected CSV header.");
            }
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) { continue; }
                // This prepared CSV has no quoted fields or embedded commas.
                String[] fields = line.split(",", -1);
                if (fields.length != 5) { throw new IOException("Expected five CSV fields."); }
                String name = normalizeName(fields[1]);
                Pokemon pokemon = new Pokemon(Integer.parseInt(fields[0]), name,
                    Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]));
                table.insert(name, pokemon);
                count++;
            }
        }
        return count;
    }
}

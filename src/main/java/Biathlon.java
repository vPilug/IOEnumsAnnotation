import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Biathlon {

    private String csvPath;
    private List<Athlete> standings;

    public Biathlon(String csvPath){
        this.csvPath = csvPath;
        launch();
    }

    public void launch() {

        String csvString = handleFileReading();
        List<Athlete> athletes = parseCSV(csvString);
        this.standings = calculateStandings(athletes);

    }

    public void getResult() {
        System.out.println("Winner - " + standings.get(0).getAthleteName() + " " + standings.get(0).getTotalTimeResult());
        System.out.println("Runner-up - " + standings.get(1).getAthleteName() + " " + standings.get(1).getTotalTimeResult());
        System.out.println("Third Place - " + standings.get(2).getAthleteName() + " " + standings.get(2).getTotalTimeResult());
    }

    private String handleFileReading() {
        String csv = "";
        try (BufferedReader br = new BufferedReader(new FileReader(this.csvPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                csv += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csv;
    }

    public static List<Athlete> parseCSV(String csv) {
        List<Athlete> athletes = new ArrayList<>();

        String[] lines = csv.split("\n");
        for (String line : lines) {
            String[] parts = line.split(",");
            int athleteNumber = Integer.parseInt(parts[0]);
            String athleteName = parts[1];
            String countryCode = parts[2];
            String skiTimeResult = parts[3];
            String[] shootingResults = Arrays.copyOfRange(parts, 4, parts.length);

            athletes.add(new Athlete(athleteNumber, athleteName, countryCode, skiTimeResult, shootingResults));
        }

        return athletes;
    }

    public static List<Athlete> calculateStandings(List<Athlete> athletes) {
        List<Athlete> copyList = new ArrayList<>(List.copyOf(athletes));
        copyList.sort(new AthleteComparator());
        return copyList.subList(0, 3);
    }
}
class AthleteComparator implements Comparator<Athlete> {
    public int compare(Athlete a1, Athlete a2) {
        return a1.getTotalTimeResult().compareTo(a2.getTotalTimeResult());
    }
}

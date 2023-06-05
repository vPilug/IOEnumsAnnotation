import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BiathlonTest {

    @Test
    public void testParseCSV() {

        String csv = "11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo\n" +
                "1,Jimmy Smiles,UK,29:15,xxoox,xooxo,xxxxo\n" +
                "27,Piotr Smitzer,CZ,30:10,xxxxx,xxxxx,xxxxx";

        List<Athlete> athletes = Biathlon.parseCSV(csv);

        Assertions.assertEquals(3, athletes.size());

        Athlete athlete1 = athletes.get(0);
        Assertions.assertEquals(11, athlete1.getAthleteNumber());
        Assertions.assertEquals("Umar Jorgson", athlete1.getAthleteName());
        Assertions.assertEquals("SK", athlete1.getCountryCode());
        Assertions.assertEquals("30:27", athlete1.getSkiTimeResult());
        Assertions.assertArrayEquals(new String[]{"xxxox", "xxxxx", "xxoxo"}, athlete1.getShootingResults());
    }

    @Test
    public void testCalculateStandings() {
        List<Athlete> athletes = List.of(
                new Athlete(11, "Umar Jorgson", "SK", "30:27", new String[]{"xxxox", "xxxxx", "xxoxo"}),
                new Athlete(1, "Jimmy Smiles", "UK", "29:15", new String[]{"xxoox", "xooxo", "xxxxo"}),
                new Athlete(27, "Piotr Smitzer", "CZ", "30:10", new String[]{"xxxxx", "xxxxx", "xxxxx"})
        );

        List<Athlete> standings = Biathlon.calculateStandings(athletes);

        Assertions.assertEquals(3, standings.size());

        Assertions.assertEquals("Piotr Smitzer", standings.get(0).getAthleteName());
        Assertions.assertEquals("Jimmy Smiles", standings.get(1).getAthleteName());
        Assertions.assertEquals("Umar Jorgson", standings.get(2).getAthleteName());

        Assertions.assertEquals("30:10 (30:10 + 0)", standings.get(0).getTotalTimeResult());
        Assertions.assertEquals("30:15 (29:15 + 60)", standings.get(1).getTotalTimeResult());
        Assertions.assertEquals("30:57 (30:27 + 30)", standings.get(2).getTotalTimeResult());
    }
}
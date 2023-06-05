public class Athlete {
    private int athleteNumber;
    private String athleteName;
    private String countryCode;
    private String skiTimeResult;
    private String[] shootingResults;

    public Athlete(int athleteNumber, String athleteName, String countryCode, String skiTimeResult, String[] shootingResults) {
        this.athleteNumber = athleteNumber;
        this.athleteName = athleteName;
        this.countryCode = countryCode;
        this.skiTimeResult = skiTimeResult;
        this.shootingResults = shootingResults;
    }

    public int getAthleteNumber() {
        return athleteNumber;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getSkiTimeResult() {
        return skiTimeResult;
    }

    public String[] getShootingResults() {
        return shootingResults;
    }

    public int getPenaltyTime() {
        int penaltyTime = 0;
        for (String shootingResult : shootingResults) {
            for (char symbol : shootingResult.toCharArray()) {
                ShotResult enumSymbol = ShotResult.fromSymbol(symbol);
                if (enumSymbol == ShotResult.MISS) {
                    penaltyTime += 10;
                }
            }
        }
        return penaltyTime;
    }
@Result
    public String getTotalTimeResult() {
        String[] timeParts = skiTimeResult.split(":");
        int minutes = Integer.parseInt(timeParts[0]);
        int seconds = Integer.parseInt(timeParts[1]);
        int penaltyTime = getPenaltyTime();
        int totalSeconds = minutes * 60 + seconds + penaltyTime;
        return String.format("%d:%02d (%s + %d)", totalSeconds / 60, totalSeconds % 60, skiTimeResult, penaltyTime);
    }
}
enum ShotResult {
    HIT('x'),
    MISS('o');

    private char symbol;

    ShotResult(char symbol) {
        this.symbol = symbol;
    }

    public static ShotResult fromSymbol(char symbol) {
        for (ShotResult result : ShotResult.values()) {
            if (result.symbol == symbol) {
                return result;
            }
        }
        throw new IllegalArgumentException("Invalid symbol: " + symbol);
    }
}
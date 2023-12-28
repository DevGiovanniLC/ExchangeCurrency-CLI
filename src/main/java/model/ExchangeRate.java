package model;

public class ExchangeRate {
    private final Currency From;
    private final Currency To;
    private final Double rate;
    private final String date;

    public ExchangeRate(Currency from, Currency to, String date) {
        this.From = from;
        this.To = to;
        this.rate = calculateRate();
        this.date = date;
    }

    private Double calculateRate() {
        return (this.From.referenceValue() * 1000) / (this.To.referenceValue() * 1000);
    }

    public Double getRate(){
        return this.rate;
    }

    @Override
    public String toString() {
        return String.format("""
                        %s\s
                        from = %s\s
                        to = %s
                         rate = %f""",
                this.date,
                this.From,
                this.To,
                this.rate
        );
    }
}

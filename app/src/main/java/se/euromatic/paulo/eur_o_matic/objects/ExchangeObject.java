package se.euromatic.paulo.eur_o_matic.objects;

/** * Created by Paulo Vila Nova on 2017-02-23.
 */

public class ExchangeObject {

    private String valueCode;
    private double valueRate;
    private String base;
    private String date;


    public ExchangeObject(String valueCode, double valueRate) {
        this.valueCode = valueCode;
        this.valueRate = valueRate;
    }

    public ExchangeObject() {
    }

    public ExchangeObject(double valueRate, String date) {
        this.valueRate = valueRate;
        this.date = date;
    }

    public String getValueCode() {
        return valueCode;
    }

    public void setValueCode(String valueCode) {
        this.valueCode = valueCode;
    }

    public double getValueRate() {
        return valueRate;
    }

    public void setValueRate(double valueRate) {
        this.valueRate = valueRate;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

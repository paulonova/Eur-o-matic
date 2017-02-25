package se.euromatic.paulo.eur_o_matic.objects;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/** * Created by Paulo Vila Nova on 2017-02-23.
 */

public class Helper {

    private static ArrayList<ExchangeObject> exchangeObjectArrayList = new ArrayList<>();
    public static ArrayList<ExchangeObject> exchangeObjectHistoricList = new ArrayList<>();


    private static final String AUD = "AUD";
    private static final String BGN = "BGN";
    private static final String BRL = "BRL";
    private static final String CAD = "CAD";
    private static final String CHF = "CHF";
    private static final String CNY = "CNY";
    private static final String CZK = "CZK";
    private static final String DKK = "DKK";
    private static final String GBP = "GBP";
    private static final String HKD = "HKD";
    private static final String HRK = "HRK";
    private static final String HUF = "HUF";
    private static final String IDR = "IDR";
    private static final String ILS = "ILS";
    private static final String INR = "INR";
    private static final String JPY = "JPY";
    private static final String KRW = "KRW";
    private static final String MXN = "MXN";
    private static final String MYR = "MYR";
    private static final String NOK = "NOK";
    private static final String NZD = "NZD";
    private static final String PHP = "PHP";
    private static final String PLN = "PLN";
    private static final String RON = "RON";
    private static final String RUB = "RUB";
    private static final String SEK = "SEK";
    private static final String SGD = "SGD";
    private static final String THB = "THB";
    private static final String TRY = "TRY";
    private static final String USD = "USD";
    private static final String ZAR = "ZAR";



    public static void saveJsonRatesInArrayList(JSONObject rates) throws JSONException {


        final Double aud = rates.getDouble(AUD);
        final Double bgn = rates.getDouble(BGN);
        final Double brl = rates.getDouble(BRL);
        final Double cad = rates.getDouble(CAD);
        final Double chf = rates.getDouble(CHF);
        final Double cny = rates.getDouble(CNY);
        final Double czk = rates.getDouble(CZK);
        final Double dkk = rates.getDouble(DKK);
        final Double gbp = rates.getDouble(GBP);
        final Double hkd = rates.getDouble(HKD);
        final Double hrk = rates.getDouble(HRK);
        final Double huf = rates.getDouble(HUF);
        final Double idr = rates.getDouble(IDR);
        final Double ils = rates.getDouble(ILS);
        final Double inr = rates.getDouble(INR);
        final Double jpy = rates.getDouble(JPY);
        final Double krw = rates.getDouble(KRW);
        final Double mxn = rates.getDouble(MXN);
        final Double myr = rates.getDouble(MYR);
        final Double nok = rates.getDouble(NOK);
        final Double nzd = rates.getDouble(NZD);
        final Double php = rates.getDouble(PHP);
        final Double pln = rates.getDouble(PLN);
        final Double ron = rates.getDouble(RON);
        final Double rub = rates.getDouble(RUB);
        final Double sek = rates.getDouble(SEK);
        final Double sgd = rates.getDouble(SGD);
        final Double thb = rates.getDouble(THB);
        final Double mtry =rates.getDouble(TRY);
        final Double usd = rates.getDouble(USD);
        final Double zar = rates.getDouble(ZAR);

        exchangeObjectArrayList.clear();
        exchangeObjectArrayList.add(new ExchangeObject(AUD, aud ));
        exchangeObjectArrayList.add(new ExchangeObject(BGN, bgn ));
        exchangeObjectArrayList.add(new ExchangeObject(BRL, brl ));
        exchangeObjectArrayList.add(new ExchangeObject(CAD, cad ));
        exchangeObjectArrayList.add(new ExchangeObject(CHF, chf ));
        exchangeObjectArrayList.add(new ExchangeObject(CNY, cny ));
        exchangeObjectArrayList.add(new ExchangeObject(CZK, czk ));
        exchangeObjectArrayList.add(new ExchangeObject(DKK, dkk ));
        exchangeObjectArrayList.add(new ExchangeObject(GBP, gbp ));
        exchangeObjectArrayList.add(new ExchangeObject(HKD, hkd ));
        exchangeObjectArrayList.add(new ExchangeObject(HRK, hrk ));
        exchangeObjectArrayList.add(new ExchangeObject(HUF, huf ));
        exchangeObjectArrayList.add(new ExchangeObject(IDR, idr ));
        exchangeObjectArrayList.add(new ExchangeObject(ILS, ils ));
        exchangeObjectArrayList.add(new ExchangeObject(INR, inr ));
        exchangeObjectArrayList.add(new ExchangeObject(JPY, jpy ));
        exchangeObjectArrayList.add(new ExchangeObject(KRW, krw ));
        exchangeObjectArrayList.add(new ExchangeObject(MXN, mxn ));
        exchangeObjectArrayList.add(new ExchangeObject(MYR, myr ));
        exchangeObjectArrayList.add(new ExchangeObject(NOK, nok ));
        exchangeObjectArrayList.add(new ExchangeObject(NZD, nzd ));
        exchangeObjectArrayList.add(new ExchangeObject(PHP, php ));
        exchangeObjectArrayList.add(new ExchangeObject(PLN, pln ));
        exchangeObjectArrayList.add(new ExchangeObject(RON, ron ));
        exchangeObjectArrayList.add(new ExchangeObject(RUB, rub ));
        exchangeObjectArrayList.add(new ExchangeObject(SEK, sek ));
        exchangeObjectArrayList.add(new ExchangeObject(SGD, sgd ));
        exchangeObjectArrayList.add(new ExchangeObject(THB, thb ));
        exchangeObjectArrayList.add(new ExchangeObject(TRY, mtry));
        exchangeObjectArrayList.add(new ExchangeObject(USD, usd ));
        exchangeObjectArrayList.add(new ExchangeObject(ZAR, zar ));

        //Log.d("ARRAY_TEST", " test: " + exchangeObjectArrayList.size());

    }



    public static ArrayList<ExchangeObject> getExchangeObjectArrayList(){
        //Log.d("MAIN_RATE","LIST: " + exchangeObjectArrayList.size());
        return exchangeObjectArrayList;
    }

    public static ArrayList<ExchangeObject> getExchangeObjectHistoricList(){
        //Log.d("HISTORY","H: " + exchangeObjectHistoricList.size());
        return exchangeObjectHistoricList;
    }

}

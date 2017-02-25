package se.euromatic.paulo.eur_o_matic.objects;

import java.util.Comparator;

/** * Created by Paulo Vila Nova on 2017-02-25.
 */

public class MyComparator implements Comparator<ExchangeObject> {


    @Override
    public int compare(ExchangeObject exchangeObject, ExchangeObject t1) {

        return exchangeObject.getDate().compareTo(t1.getDate());

    }
}

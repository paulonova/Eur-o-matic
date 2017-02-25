package se.euromatic.paulo.eur_o_matic.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.h6ah4i.android.widget.advrecyclerview.utils.RecyclerViewAdapterUtils;
import java.util.List;
import se.euromatic.paulo.eur_o_matic.R;
import se.euromatic.paulo.eur_o_matic.activities.HistoryActivity;
import se.euromatic.paulo.eur_o_matic.objects.ExchangeObject;
import se.euromatic.paulo.eur_o_matic.objects.Helper;

/** * Created by Paulo Vila Nova on 2017-02-23.
 */

public class MainValueAdapter extends RecyclerView.Adapter<MainValueAdapter.ViewHolder> {

    public static final String VALUE_CODE = "value_code";

    private Context context;
    private LayoutInflater inflater;
    private List<ExchangeObject> exchangeObjectList;

    public MainValueAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.exchangeObjectList = Helper.getExchangeObjectArrayList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_value_model, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ExchangeObject exchangeObject = exchangeObjectList.get(position);

        holder.circleImgMoneyLogo.setImageResource(R.drawable.money);
        holder.textViewValueCode.setText(exchangeObject.getValueCode());
        holder.textViewExchangeRates.setText(exchangeObject.getValueRate() + "");

    }

    @Override
    public int getItemCount() {
        return exchangeObjectList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView circleImgMoneyLogo;
        public TextView textViewValueCode, textViewExchangeRates;
        private View container;

        public ViewHolder(View itemView) {
            super(itemView);
            circleImgMoneyLogo = (ImageView)itemView.findViewById(R.id.circleImgMoneyLogo);
            textViewValueCode = (TextView) itemView.findViewById(R.id.textViewValueCode);
            textViewExchangeRates = (TextView) itemView.findViewById(R.id.textViewExchangeRates);

            container = (itemView.findViewById(R.id.relativeLayoutMainContainer));
            container.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder vh = RecyclerViewAdapterUtils.getViewHolder(view);
            final int position = vh.getAdapterPosition();
            ExchangeObject exchangeObject = exchangeObjectList.get(position);

            switch (view.getId()){

                case R.id.relativeLayoutMainContainer:
//                    Toast.makeText(context, "Position: " + position + " code: " + exchangeObject.getValueCode(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, HistoryActivity.class);
                    intent.putExtra(VALUE_CODE, exchangeObject.getValueCode());
                    context.startActivity(intent);
                    break;
            }

        }
    }
}

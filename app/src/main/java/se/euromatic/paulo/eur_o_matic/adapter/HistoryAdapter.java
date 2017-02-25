package se.euromatic.paulo.eur_o_matic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.List;
import se.euromatic.paulo.eur_o_matic.R;
import se.euromatic.paulo.eur_o_matic.objects.ExchangeObject;
import se.euromatic.paulo.eur_o_matic.objects.Helper;

/** * Created by Paulo Vila Nova on 2017-02-24.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<ExchangeObject> retroactiveDates;


    public HistoryAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.retroactiveDates = Helper.getExchangeObjectHistoricList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_model, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ExchangeObject historyObject = retroactiveDates.get(position);

        holder.circleImgHistoryLogo.setImageResource(R.drawable.ic_eu_flag);
//        holder.textViewDate.setText(historyObject.getValueCode());
        holder.textViewDate.setText(historyObject.getDate());
        holder.textViewHistoryRates.setText(historyObject.getValueRate() + "");

    }

    @Override
    public int getItemCount() {
        return retroactiveDates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView circleImgHistoryLogo;
        public TextView textViewDate, textViewHistoryRates;

        public ViewHolder(View itemView) {
            super(itemView);
            circleImgHistoryLogo = (ImageView)itemView.findViewById(R.id.circleImgHistoryLogo);
            textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
            textViewHistoryRates = (TextView) itemView.findViewById(R.id.textViewHistoryRates);
        }
    }

}

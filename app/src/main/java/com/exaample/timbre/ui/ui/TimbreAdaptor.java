package com.exaample.timbre.ui.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.exaample.timbre.R;
import com.exaample.timbre.models.Timbru;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TimbreAdaptor extends BaseAdapter {

    private List<Timbru> lstTimbre = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public List<Timbru> getLstTimbre() {
        return lstTimbre;
    }

    public TimbreAdaptor(List<Timbru> lstTimbre, Context context) {
        this.lstTimbre = lstTimbre;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lstTimbre.size();
    }

    @Override
    public Object getItem(int position) {
        return lstTimbre.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.listview_item, parent, false);

        TextView tvSerie = row.findViewById(R.id.tvSerie);
        TextView tvTematica = row.findViewById(R.id.tvTematica);
        TextView tvAn = row.findViewById(R.id.tvAn);
        TextView tvMarime = row.findViewById(R.id.tvMarime);

        if(lstTimbre.get(position).getNou()==1){
            tvSerie.setTextColor(Color.parseColor("#00FF00"));
            tvTematica.setTextColor(Color.parseColor("#00FF00"));
            tvAn.setTextColor(Color.parseColor("#00FF00"));
            tvMarime.setTextColor(Color.parseColor("#00FF00"));
        }

        tvSerie.setText("Serie:  " + lstTimbre.get(position).getSerie());
        tvAn.setText("An:   " + String.valueOf(lstTimbre.get(position).getAn()));
        tvTematica.setText("Tematica:   "+ lstTimbre.get(position).getTematica() );
        tvMarime.setText("Marime:   " + lstTimbre.get(position).getMarime());

        return row;
    }

    public void updateLista(List<Timbru> listaNoua) {
        try {

            this.lstTimbre = listaNoua;
            notifyDataSetChanged();

        } catch (Exception e) {
            Log.e("eroare ", e.getMessage());
        }
    }
}

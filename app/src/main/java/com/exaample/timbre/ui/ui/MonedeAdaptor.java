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
import com.exaample.timbre.models.Moneda;
import com.exaample.timbre.models.Timbru;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MonedeAdaptor extends BaseAdapter {

    private List<Moneda> lstMonede = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public List<Moneda> getLstMonede() {
        return lstMonede;
    }

    public MonedeAdaptor(List<Moneda> lstMonede, Context context) {
        this.lstMonede = lstMonede;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lstMonede.size();
    }

    @Override
    public Object getItem(int position) {
        return lstMonede.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.monede_listview_item, parent, false);

        TextView tvNume = row.findViewById(R.id.tvNume);
        TextView tvValoare = row.findViewById(R.id.tvValoare);
        TextView tvAn = row.findViewById(R.id.tvAn);

        tvNume.setText("Serie:  " + lstMonede.get(position).getNume());
        tvAn.setText("An:   " + String.valueOf(lstMonede.get(position).getAn()));
        tvValoare.setText("Tematica:   " + lstMonede.get(position).getValoare());

        return row;
    }

    public void updateLista(List<Moneda> listaNoua) {
        try {

            this.lstMonede = listaNoua;
            notifyDataSetChanged();

        } catch (Exception e) {
            Log.e("eroare ", e.getMessage());
        }
    }
}

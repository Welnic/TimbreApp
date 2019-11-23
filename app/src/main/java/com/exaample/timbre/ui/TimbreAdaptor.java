package com.exaample.timbre.ui;

import android.content.Context;
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
        TextView tvAn = row.findViewById(R.id.tvAn);
        TextView tvRaritate =  row.findViewById(R.id.tvRaritate);

        tvSerie.setText(lstTimbre.get(position).getSerie());
        tvAn.setText(String.valueOf(lstTimbre.get(position).getAn()));
        tvRaritate.setText(lstTimbre.get(position).getRaritate());

        return row;
    }
    public void updateLista(List<Timbru> listaNoua ){
        try{

            this.lstTimbre=listaNoua;
            notifyDataSetChanged();

        }catch (Exception e){
            Log.e("eroare ",e.getMessage());
        }}
}

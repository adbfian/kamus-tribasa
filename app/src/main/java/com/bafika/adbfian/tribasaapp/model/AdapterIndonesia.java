package com.bafika.adbfian.tribasaapp.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bafika.adbfian.tribasaapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adbfian on 24/09/2016.
 */
public class AdapterIndonesia extends RecyclerView.Adapter<AdapterIndonesia.IndonesiaHolder> {

    private List<DataKata> daftarKata;
    private Context context;
    private RecyclerItemClickListener recyclerItemClickListener;

    public AdapterIndonesia(Context context){
        this.context = context;
        this.daftarKata = new ArrayList<>();
    }

    private void add(DataKata item) {
        daftarKata.add(item);
        notifyItemInserted(daftarKata.size() - 1);
    }

    public void addAll(List<DataKata> contactList) {
        for (DataKata dataKata : contactList) {
            add(dataKata);
        }
    }

    public void remove(DataKata item) {
        int position = daftarKata.indexOf(item);
        if (position > -1) {
            daftarKata.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public DataKata getItem(int position){
        return daftarKata.get(position);
    }

    @Override
    public IndonesiaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kata, parent, false);
        final IndonesiaHolder indonesiaHolder = new IndonesiaHolder(view);
        indonesiaHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterpos = indonesiaHolder.getAdapterPosition();
                if (adapterpos != RecyclerView.NO_POSITION){
                    if (recyclerItemClickListener != null){
                        recyclerItemClickListener.onItemClick(adapterpos, indonesiaHolder.itemView);
                    }
                }
            }
        });
        return indonesiaHolder;
    }

    @Override
    public void onBindViewHolder(IndonesiaHolder holder, int position) {
        final DataKata dataKata = daftarKata.get(position);
        final Resources resources = context.getResources();
        final int tileSize = resources.getDimensionPixelSize(R.dimen.letter_tile_size);

        LetterTile letterTile = new LetterTile(context);
        Bitmap letterBitmap = letterTile.getLetterTile(dataKata.getIndo_kata(),
                String.valueOf(dataKata.getId()), tileSize, tileSize);

        holder.list_icon.setImageBitmap(letterBitmap);
        holder.list_kata.setText(dataKata.getIndo_kata());
    }

    @Override
    public int getItemCount() {
        return daftarKata.size();
    }

    public void setOnItemClickListener(RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    static class IndonesiaHolder extends RecyclerView.ViewHolder {
        ImageView list_icon;
        TextView list_kata;

        public IndonesiaHolder(View itemView) {
            super(itemView);

            list_icon = (ImageView)itemView.findViewById(R.id.list_icon);
            list_kata = (TextView)itemView.findViewById(R.id.list_kata);
        }
    }
}

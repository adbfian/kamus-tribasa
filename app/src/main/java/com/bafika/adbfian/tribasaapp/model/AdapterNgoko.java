package com.bafika.adbfian.tribasaapp.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bafika.adbfian.tribasaapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adbfian on 24/09/2016.
 */
public class AdapterNgoko extends RecyclerView.Adapter<AdapterNgoko.NgokoHolder> {

    private List<DataKata> daftarKata;
    private Context context;

    private DataKata dataKata;
    private RecyclerItemClickListener recyclerItemClickListener;

    public AdapterNgoko(Context context){
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
    public NgokoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kata, parent, false);
        final NgokoHolder ngokoHolder = new NgokoHolder(view);
        ngokoHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterpos = ngokoHolder.getAdapterPosition();
                if (adapterpos != RecyclerView.NO_POSITION){
                    if (recyclerItemClickListener != null){
                        recyclerItemClickListener.onItemClick(adapterpos, ngokoHolder.itemView);
                    }
                }
            }
        });
        return ngokoHolder;
    }

    @Override
    public void onBindViewHolder(NgokoHolder holder, int position) {
        final DataKata dataKata = daftarKata.get(position);
        final Resources resources = context.getResources();
        final int tileSize = resources.getDimensionPixelSize(R.dimen.letter_tile_size);

        LetterTile letterTile = new LetterTile(context);
        Bitmap letterBitmap = letterTile.getLetterTile(dataKata.getNgoko_kata(),
                String.valueOf(dataKata.getId()), tileSize, tileSize);

        holder.list_icon.setImageBitmap(letterBitmap);
        holder.list_kata.setText(dataKata.getNgoko_kata());
    }

    @Override
    public int getItemCount() {
        return daftarKata.size();
    }


    public void setOnItemClickListener(RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    static class NgokoHolder extends RecyclerView.ViewHolder {
        ImageView list_icon;
        TextView list_kata;

        public NgokoHolder(View itemView) {
            super(itemView);

            list_icon = (ImageView)itemView.findViewById(R.id.list_icon);
            list_kata = (TextView)itemView.findViewById(R.id.list_kata);
        }
    }


}

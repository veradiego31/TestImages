package io.sirio.testimages.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.sirio.testimages.R;
import io.sirio.testimages.models.Images;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private ArrayList<Images> imagenes;
    private int itemLayout;
    private Context mContext;
    OnItemClickListener mItemClickListener;

    public HomeAdapter(ArrayList<Images> imagenes, int itemLayout, Context mContext){

        this.imagenes = imagenes;
        this.itemLayout = itemLayout;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        int image = imagenes.get(position).getResource();
        Glide.with(mContext).load(image).into(holder.imgHome);
        holder.textView.setText(imagenes.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return (imagenes != null? imagenes.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgHome;
        public TextView textView;


        public ViewHolder(View itemView) {
            super(itemView);

            imgHome = (ImageView) itemView.findViewById(R.id.row_imageview);
            textView = (TextView) itemView.findViewById(R.id.row_text);

            imgHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getAdapterPosition());

                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view , int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}

package io.sirio.testimages.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.sirio.testimages.R;



public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private ArrayList<Integer> imagenes;
    private int itemLayout;
    private Context mContext;
    OnItemClickListener mItemClickListener;

    public HomeAdapter(ArrayList<Integer> imagenes, int itemLayout, Context mContext){

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

        int image = imagenes.get(position);
        Glide.with(mContext).load(image).into(holder.imgHome);
    }

    @Override
    public int getItemCount() {
        return (imagenes != null? imagenes.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgHome;


        public ViewHolder(View itemView) {
            super(itemView);

            imgHome = (ImageView) itemView.findViewById(R.id.row_imageview);
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

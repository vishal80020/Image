package com.example.android.image;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageResultAdapterV extends RecyclerView.Adapter<ImageResultAdapterV.ResultViewHolder> {
    private Context mContext;
    private ArrayList<ImageResult> mImageResult;


    public ImageResultAdapterV(Context context, ArrayList<ImageResult> imageResult){
        mContext=context;
        mImageResult=imageResult;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.result_vitem,parent,false);
        return new ResultViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        ImageResult currentResult = mImageResult.get(position);
        String imageUrl = currentResult.getImageUrl();
        String creator = currentResult.getCreator();
        int likes = currentResult.getLikes();

        holder.mTextViewCreator.setText(creator);
        holder.mTextViewLikes.setText("Likes: "+ likes);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);



    }

    @Override
    public int getItemCount() {
        return mImageResult.size();
    }


    public class ResultViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageView;
        private TextView mTextViewCreator;
        private  TextView mTextViewLikes;


        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image_view);
            mTextViewCreator = (TextView) itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes = (TextView) itemView.findViewById(R.id.text_view_like);
        }
    }
}
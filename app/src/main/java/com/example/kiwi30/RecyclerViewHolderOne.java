package com.example.kiwi30;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolderOne  extends RecyclerView.ViewHolder{
    TextView textView8;
    TextView textView9;
    TextView textView10;
    TextView textView11;
    TextView textView12;
    TextView textView13;
    TextView textView14;
    public RecyclerViewHolderOne(@NonNull View itemView) {
        super(itemView);

        textView8 = itemView.findViewById(R.id.textView8);
        textView9 = itemView.findViewById(R.id.textView9);
        textView10 = itemView.findViewById(R.id.textView10);
        textView11 = itemView.findViewById(R.id.textView11);
        textView12 = itemView.findViewById(R.id.textView12);
        textView13 = itemView.findViewById(R.id.textView13);
        textView14 = itemView.findViewById(R.id.textView14);
    }



    public TextView getView(){
        return textView8;
    }

    public TextView getViewTwo(){
        return textView9;
    }
    public TextView getViewThree(){
        return textView10;
    }
    public TextView getViewFour(){
        return textView11;
    }

    public TextView getViewFive(){
        return textView12;
    }

    public TextView getViewSix(){
        return textView13;
    }

    public TextView getViewSeven(){
        return textView14;
    }
}

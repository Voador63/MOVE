package com.example.move.fragmentSucces;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.move.R;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<SuccessDataModel> implements View.OnClickListener{

        private ArrayList<SuccessDataModel> dataSet;
        Context mContext;

        // View lookup cache
        private static class ViewHolder {
            ImageView image;
            TextView txtNom;
            TextView txtDescription;
            ImageView info;
        }

        public CustomAdapter(ArrayList<SuccessDataModel> data, Context context) {
            super(context, R.layout.row_item, data);
            this.dataSet = data;
            this.mContext=context;
        }

        @Override
        public void onClick(View v) {

            int position=(Integer) v.getTag();
            Object object= getItem(position);
            SuccessDataModel dataModel=(SuccessDataModel)object;

            //switch (v.getId())
            //{
            //    case R.id.item_info:
            //        Snackbar.make(v, "", Snackbar.LENGTH_LONG)
            //                .setAction("No action", null).show();
            //        break;
            //}
        }

        private int lastPosition = -1;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            SuccessDataModel dataModel = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            ViewHolder viewHolder; // view lookup cache stored in tag

            final View result;

            if (convertView == null) {

                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.row_item, parent, false);
                viewHolder.image = (ImageView) convertView.findViewById(R.id.item_image);
                viewHolder.txtNom = (TextView) convertView.findViewById(R.id.name);
                viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.descript);
                viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

                result=convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }

            Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
            result.startAnimation(animation);
            lastPosition = position;

            viewHolder.image.setTag(position);
            viewHolder.txtNom.setText(dataModel.getNom());
            viewHolder.txtDescription.setText(dataModel.getDescription());
            //viewHolder.info.setOnClickListener(this);
            viewHolder.info.setTag(position);

            if(dataModel.getEtat())
                convertView.setBackground(this.getContext().getDrawable(R.color.colorPrimary));
            // Return the completed view to render on screen
            return convertView;
        }
}

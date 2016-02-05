package com.github.dawidhyzy.kotlinvsjava.jv.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.dawidhyzy.kotlinvsjava.R;
import com.github.dawidhyzy.kotlinvsjava.jv.api.model.Weather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import timber.log.Timber;

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 31/01/16.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{
    private List<Weather> weatherList = new ArrayList<>(0);

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        final Weather weather = weatherList.get(position);
        Picasso.with(holder.icon.getContext())
                .load(String.format("http://openweathermap.org/img/w/%s.png", weather.getIcon()))
                .into(holder.icon);
        holder.description.setText(String.format(Locale.getDefault(), "%s: %s",
                weather.getMain(),
                weather.getDescription()));
    }

    @Override public int getItemCount() {
        return this.weatherList.size();
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList.clear();
        boolean added = this.weatherList.addAll(weatherList);
        Timber.d("Objects added to list: %s; list size: %s", added, this.weatherList.size());
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView description;
        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}

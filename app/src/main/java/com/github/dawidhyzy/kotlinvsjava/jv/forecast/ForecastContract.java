package com.github.dawidhyzy.kotlinvsjava.jv.forecast;

import com.github.dawidhyzy.kotlinvsjava.jv.domain.Forecast;

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 31/01/16.
 */
public class ForecastContract {

    public interface View{
        void showLoading(boolean show);
        void showError(String message);
        void setForecast(Forecast forecast);
    }

    public interface Presenter{
        void loadForecast();
        void refresh();
    }
}

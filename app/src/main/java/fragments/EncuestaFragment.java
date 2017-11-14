package fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import mc.sms.R;
import model.Actividad;

/**
 * Created by Alvaro on 11/14/17.
 */

public class EncuestaFragment extends Fragment {

    public static Actividad activity;
    RelativeLayout header;

    public static EncuestaFragment newInstance(Actividad actividad) {


        EncuestaFragment fragment = new EncuestaFragment();
        activity=actividad;
        fragment.setRetainInstance(true);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retain this fragment across configuration changes.
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.encuesta_layout, container, false);
        Toolbar toolbar = (Toolbar) RootView.findViewById(R.id.encuestatoolbar);
        TextView textView = (TextView) RootView.findViewById(R.id.textHeader);
        if (Locale.getDefault().getLanguage().equals("en")) {
            toolbar.setTitle("Encuesta");
        } else {
            toolbar.setTitle("Encuesta");
        }

        if (activity.getType().equals("conferencia")) {
            toolbar.setBackgroundColor(getResources().getColor(R.color.conferencia));

        }

        else if (activity.getType().equals("social") || activity.getType().equals("break")) {
            toolbar.setBackgroundColor(getResources().getColor(R.color.brk));
        }

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();

            }
        });

        header = (RelativeLayout)RootView.findViewById(R.id.header);

        return RootView;
    }
}

package fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.annotation.Nullable;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


import adapters.MeetingAppsListViewAdapter;
import bolts.Continuation;
import bolts.Task;
import mc.sms2017.MainActivity;
import mc.sms2017.R;
import mc.sms2017.myApp;

import model.Actividad;


public class MeetingsFragment extends Fragment {

    SwipeDetector swipeDetector;

    Bundle savedState;
    ListView listview;
    public myApp myapp;
    MeetingAppsListViewAdapter adapter;
    public static List<Actividad> meetingAppList;



    public static MeetingsFragment newInstance() {

        // Instantiate a new fragment

        MeetingsFragment fragment = new MeetingsFragment();

        return fragment;

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Restore State Here


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        if (savedInstanceState != null) {
            Log.i("CHAO", "rer");

        } else {
            Log.i("HOLA", "rer");
        }
        ParseQuery<Actividad> query = ParseQuery.getQuery(Actividad.class);
        query.include("lugar");
        query.whereEqualTo("tipo","congreso");
        query.fromLocalDatastore().findInBackground().continueWithTask(new Continuation<List<Actividad>, Task<List<Actividad>>>() {
            @Override
            public Task<List<Actividad>> then(Task<List<Actividad>> task) throws Exception {
                meetingAppList=task.getResult();
                return task;
            }
        });

      /*  ParseQuery<Actividad> query = ParseQuery.getQuery(Actividad.class);
        query.include("lugar");
        query.whereEqualTo("tipo","congreso");
        query.fromLocalDatastore().findInBackground(new FindCallback<Actividad>() {
            @Override
            public void done(List<Actividad> objects, ParseException e) {
                    meetingAppList=objects;
            }

        });*/


    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.common_list_layout, container, false);
        listview = (ListView) RootView.findViewById(R.id.commonListView);
        this.myapp = (myApp) getActivity().getApplicationContext();
        listview.setOnTouchListener(swipeDetector);
        return RootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (meetingAppList != null) {
            adapter = new MeetingAppsListViewAdapter(getActivity(), meetingAppList);
            listview.setAdapter(adapter);

        }

        else {
            Intent intent = new Intent(getActivity(),
                    MainActivity.class);

            startActivity(intent);
        }

    }

    @Override
    public void onResume() {
        super.onResume();


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParseObject object = (ParseObject) (listview.getItemAtPosition(position));
                Actividad meetingApp = ParseObject.createWithoutData(Actividad.class, object.getObjectId());
                Log.i("meetingApp", meetingApp.getObjectId());
                Fragment fragment = SplashEventFragment.newInstance(meetingApp);
                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener


                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();


    }

    @Override
    public void onStop() {
        super.onStop();

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("true", true);

    }


}


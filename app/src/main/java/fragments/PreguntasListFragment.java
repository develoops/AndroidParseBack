package fragments;


import android.app.AlertDialog;
import android.support.design.widget.FloatingActionButton;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import adapters.PreguntasListViewAdapter;

import adapters.PreguntasRecyclerViewAdapter;
import mc.sms.R;

import model.Actividad;
import model.Emision;



/**
 * Created by Alvaro on 2/25/15.
 */
public class PreguntasListFragment extends Fragment {

    ListView listview;
    public static Actividad activity;
    //public static MobiFile map;
    //public static GridView gridview;
    public static List<Emision> emisiones;
    //ImageButton pregunta;



    public static PreguntasListFragment newInstance(Actividad actividad) {

        // Instantiate a new fragment
        activity = actividad; //Alfonso
        PreguntasListFragment fragment = new PreguntasListFragment();

        fragment.setRetainInstance(true);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retain this fragment across configuration changes.
        setRetainInstance(true);
        ParseQuery<Emision> queryEmision = ParseQuery.getQuery(Emision.class);
        queryEmision.include("emisor");
        queryEmision.whereEqualTo("actividad", activity);
        queryEmision.findInBackground(new FindCallback<Emision>() {
            @Override
            public void done(List<Emision> objects, ParseException e) {
                emisiones=objects;
            }
        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.preguntaslistlayout, container , false);
        listview = (ListView) RootView.findViewById(R.id.commonListView);
        FloatingActionButton fab = (FloatingActionButton) RootView.findViewById(R.id.fab);

        // gridview a partir del elemento del xml gridview

        Toolbar toolbar = (Toolbar) RootView.findViewById(R.id.preguntastoolbar);
        if(Locale.getDefault().getLanguage().equals("en")){
            toolbar.setTitle("Preguntas");
        }
        else {
            toolbar.setTitle("Preguntas");
        }

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();

            }
        });

        if(activity.getType().equals("conferencia")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.conferencia));

        }
        else if(activity.getType().equals("modulo")||activity.getType().equals("simposio")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.companySecundario));

        }

        else if(activity.getType().equals("social")||activity.getType().equals("break")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.brk));
        }

        else {
            toolbar.setBackgroundColor(getResources().getColor(R.color.conferencia));
        }

        if(activity!=null){
            if(emisiones!=null){

                listview.setAdapter(new PreguntasListViewAdapter(getActivity(),emisiones));

               /* PreguntasRecyclerViewAdapter adapter = new PreguntasRecyclerViewAdapter(emisiones);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                listview.setLayoutManager(mLayoutManager);
                listview.setItemAnimator(new DefaultItemAnimator());
                listview.setAdapter(adapter);
*/
            }
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(getActivity());
                View promptsView = li.inflate(R.layout.custom, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getActivity());

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        saveQuestion(userInput.getText().toString());

                                    }
                                })
                        .setNegativeButton("Cancelar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });


  /*      ParseQuery<MobiFile> query = ParseQuery.getQuery(MobiFile.class);
        query.whereEqualTo("title","MapaComercial");
        query.getFirstInBackground(new GetCallback<MobiFile>() {
            @Override
            public void done(MobiFile mobiFile, ParseException e) {

                if(mobiFile!=null){
                    mobiFile.getParseFileV1().getDataInBackground();
                    button.setVisibility(View.VISIBLE);
                    Log.i("MOBIFILE", String.valueOf(mobiFile.getObjectId()));
                    if(Locale.getDefault().getLanguage().equals("en")){
                        button.setText("Commercial Map");
                    }
                    else {
                        button.setText("Mapa Comercial");
                    }
                }

            }
        });*/


/*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialogo = new Dialog(getActivity());
                dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                dialogo.setContentView(R.layout.map_box_layout);

                ParseQuery<MobiFile> query = ParseQuery.getQuery(MobiFile.class);
                query.whereEqualTo("title","MapaComercial");
                query.getFirstInBackground(new GetCallback<MobiFile>() {
                    @Override
                    public void done(MobiFile mobiFile, ParseException e) {
                        map=mobiFile;
                        final Button done = (Button) dialogo.findViewById(R.id.btn_done_image_dialog);
                        TouchImageView mapadialog = (TouchImageView) dialogo.findViewById(R.id.image_dialog);
                        mapadialog.setMaxZoom(4f);
                        mapadialog.setMinZoom(1f);
                        if (map!= null) {
                            ImageLoader imageLoader = ImageLoader.getInstance();
                            //Load the image from the url into the ImageView.
                            imageLoader.displayImage(map.getParseFileV1().getUrl(), mapadialog);
                        }


                        dialogo.getWindow().getAttributes().width = RelativeLayout.LayoutParams.MATCH_PARENT;
                        done.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogo.dismiss();

                            }
                        });

                        dialogo.show();
                    }
                });







            }

        });*/

        return RootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        /*
        View v = mTabHost.getTabWidget().getChildAt(0);
        v.setBackgroundResource(R.drawable.programa);
*/



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

    private void saveQuestion(String question){
        final Emision emision = new Emision();
        //gameScore.setComment(selectedEvent.getTitle());
        //gameScore.setUser(ParseUser.getCurrentUser());
        emision.put("actividad", activity);
        emision.put("emisor", ParseUser.getCurrentUser());
        emision.setMensajeTexto(question);
        new Thread(new Runnable() {
            public void run() {
                emision.saveEventually();
            }

        }).start();

        
    }
}
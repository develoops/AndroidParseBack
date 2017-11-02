package mc.sms2017;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import bolts.Continuation;
import bolts.Task;
import fragments.LoadDataFragment;
import fragments.LoadDataFragment2;
import model.ActContAct;
import model.Actividad;
import model.Lugar;
import model.Media;
import model.Org;
import model.Persona;
import model.PersonaRolAct;
import model.PersonaRolOrg;
import utils.MUtil;


public class MainActivity extends ActionBarActivity {

    public myApp myapp;
    //public static CompanyApp company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.myapp = (myApp) getApplicationContext();
        //Start Timer:
        MUtil.startTimer();
        //Bundle recieveParams = getIntent().getExtras();
        if (savedInstanceState == null) {
            //final ImageView imageView= (ImageView) findViewById(R.id.splash_first);


            //myapp.setSecondPass();

//                    if(myapp.checkConnection()){
                       //myapp.setSecondPass();


                    LoadDataFragment2 loadDataFragment = LoadDataFragment2.newInstance();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, loadDataFragment,"Load");
                    ft.commitAllowingStateLoss();




//                    }
                   /* else {
                        new AlertDialog.Builder(this)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("Advertencia")
                                .setMessage("Necesitas conectarte a Internet")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }

                                })
                                .show();

                    }*/
                }





    }




    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);

    }


    @Override
    public void onBackPressed() {
        // If the fragment exists and has some back-stack entry

        // Let super handle the back press
        super.onBackPressed();


    }

    @Override
    protected void onDestroy() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Log.d("Test", "Back button pressed!");

        }
        else if(keyCode == KeyEvent.KEYCODE_HOME)
        {
            Log.d("Test", "Home button pressed!");
        }
        return super.onKeyDown(keyCode, event);
    }

    private void hacerQueries(){
        ParseQuery<Actividad> query =ParseQuery.getQuery(Actividad.class);
        ParseQuery<ActContAct> query2 =ParseQuery.getQuery(ActContAct.class);
        ParseQuery<Lugar> query3 =ParseQuery.getQuery(Lugar.class);
        ParseQuery<Persona> query4 =ParseQuery.getQuery(Persona.class);
        ParseQuery<PersonaRolAct> query5 =ParseQuery.getQuery(PersonaRolAct.class);
        ParseQuery<PersonaRolOrg> query6 =ParseQuery.getQuery(PersonaRolOrg.class);
        ParseQuery<Org> query7 =ParseQuery.getQuery(Org.class);
        ParseQuery<Media> query8 =ParseQuery.getQuery(Media.class);
        query.setLimit(1000);
        query2.setLimit(1000);
        query4.setLimit(1000);
        query5.setLimit(1000);


		/*query.findInBackground().onSuccessTask(new Continuation<List<ParseObject>, Task<List<ParseObject>>>() {
			@Override
			public Task<List<ParseObject>> then(Task<List<ParseObject>> task) throws Exception {
				ParseObject.pinAllInBackground(task.getResult());
				return task;
			}
		});*/

        query.findInBackground().continueWithTask(new Continuation<List<Actividad>, Task<List<Actividad>>>() {
            @Override
            public Task<List<Actividad>>then(Task<List<Actividad>> task) throws Exception {
                Log.i("PASAQUERIES","PASA");
                ParseObject.pinAllInBackground(task.getResult());
                return task;
            }
        });

        query2.findInBackground().continueWithTask(new Continuation<List<ActContAct>, Task<List<ActContAct>>>() {
            @Override
            public Task<List<ActContAct>>then(Task<List<ActContAct>> task) throws Exception {
                Log.i("PASAQUERIES2","PASA");
                ParseObject.pinAllInBackground(task.getResult());
                return task;
            }
        });

        query3.findInBackground().continueWithTask(new Continuation<List<Lugar>, Task<List<Lugar>>>() {
            @Override
            public Task<List<Lugar>>then(Task<List<Lugar>> task) throws Exception {
                Log.i("PASAQUERIES3","PASA");
                ParseObject.pinAllInBackground(task.getResult());
                return task;
            }
        });

        query4.findInBackground().continueWithTask(new Continuation<List<Persona>, Task<List<Persona>>>() {
            @Override
            public Task<List<Persona>>then(Task<List<Persona>> task) throws Exception {
                Log.i("PASAQUERIES4","PASA");
                ParseObject.pinAllInBackground(task.getResult());
                return task;
            }
        });

        query5.findInBackground().continueWithTask(new Continuation<List<PersonaRolAct>, Task<List<PersonaRolAct>>>() {
            @Override
            public Task<List<PersonaRolAct>>then(Task<List<PersonaRolAct>> task) throws Exception {
                Log.i("PASAQUERIES5","PASA");
                ParseObject.pinAllInBackground(task.getResult());

                return task;
            }
        });

        query6.findInBackground().continueWithTask(new Continuation<List<PersonaRolOrg>, Task<List<PersonaRolOrg>>>() {
            @Override
            public Task<List<PersonaRolOrg>>then(Task<List<PersonaRolOrg>> task) throws Exception {
                Log.i("PASAQUERIES6","PASA");
                ParseObject.pinAllInBackground(task.getResult());
                return task;
            }
        });

        query7.findInBackground().continueWithTask(new Continuation<List<Org>, Task<List<Org>>>() {
            @Override
            public Task<List<Org>>then(Task<List<Org>> task) throws Exception {
                Log.i("PASAQUERIES7","PASA");
                ParseObject.pinAllInBackground(task.getResult());
                return task;
            }
        });

        query8.findInBackground().continueWithTask(new Continuation<List<Media>, Task<List<Media>>>() {
            @Override
            public Task<List<Media>>then(Task<List<Media>> task) throws Exception {
                Log.i("PASAQUERIES8","PASA");
                ParseObject.pinAllInBackground(task.getResult());
                return task;
            }
        });



    }


/*
    public void setBackButton(){

        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            new AlertDialog.Builder(this)
                    .setTitle("Really Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            finish();
                        }
                    }).create().show();
        }
        else {
            Log.i("NOFRAGMENTCH", String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));

        }

    }*/





}
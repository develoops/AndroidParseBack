package fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import bolts.Continuation;
import bolts.Task;
import mc.sms2017.R;
import mc.sms2017.myApp;
import model.ActContAct;
import model.ActFavUser;
import model.Actividad;
import model.Lugar;
import model.Media;
import model.Org;
import model.Persona;
import model.PersonaRolAct;
import model.PersonaRolOrg;
import utils.MUtil;

/**
 * Created by Alvaro on 2/25/15.
 */
public class LoadDataFragment2 extends Fragment {


	public myApp myapp;

	public static Integer i = 0;
	public static ProgressBar bar;

	public static View RootView;
	public static Org com;


	public static LoadDataFragment2 newInstance() {
		// Instantiate a new fragment
		LoadDataFragment2 fragment = new LoadDataFragment2();
		fragment.setRetainInstance(true);
		return fragment;
	}



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setRetainInstance(true);
		this.myapp = (myApp) getActivity().getApplicationContext();
		if (myapp.isFirstTime()) {
			if (!myapp.checkConnection()) {
				//TODO Alert no internet.
				new AlertDialog.Builder(getActivity())
						.setIcon(android.R.drawable.ic_dialog_alert)
						.setTitle("Warning")
						.setMessage("You need to connect to internet")
						.setPositiveButton("OK", new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which) {
								getActivity().finish();
							}

						})
						.show();
				return;
			}
			else {
				loadDataAndUpdateLocal();
			}

		}

		else {
			if (!myapp.checkConnection()) {
				//TODO Alert no internet.

			}
			else {
				loadDataAndUpdateLocal();
			}
		}

	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View RootView = inflater.inflate(R.layout.splash_layout, container, false);
		bar = (ProgressBar) RootView.findViewById(R.id.progressBar);
		ImageView splash_first = (ImageView) RootView.findViewById(R.id.splash_first);
		splash_first.setVisibility(View.VISIBLE);
		return RootView;

	}

	@Override
	public void onResume() {

		super.onResume();
		newFragment();


	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	//From second time:
	// if connection available, load server data, update local
	// if not, load local data, finish.
	private void loadDataAndUpdateLocal() {



		Log.e(getClass().getName(), "call loadServerDataAndSaveLocal");

		MUtil.isUpdateLocal = true;


	/*	ParseQuery<Actividad> query = ParseQuery.getQuery(Actividad.class);

		query.findInBackground().continueWithTask(new Continuation<List<Actividad>, Task<List<Actividad>>>() {
			@Override
			public Task<List<Actividad>> then(Task<List<Actividad>> task) throws Exception {
				ParseObject.pinAllInBackground(task.getResult());
				if(task.isCompleted()){
					newFragment();
				}
				return task;
			}
		});*/
		ParseQuery<Actividad> queryContenido = ParseQuery.getQuery(Actividad.class);
		queryContenido.setLimit(1000);
		queryContenido.findInBackground(new FindCallback<Actividad>() {
			@Override
			public void done(final List<Actividad> objects, ParseException e) {
				if(objects!=null){
					ParseObject.pinAllInBackground(objects);
				}
				else {
					Log.i("NULO","NULO");
				}

			}
		});

		ParseQuery<ActContAct> queryActContAct = ParseQuery.getQuery(ActContAct.class);
		queryActContAct.setLimit(1000);
		queryActContAct.findInBackground(new FindCallback<ActContAct>() {
			@Override
			public void done(List<ActContAct> objects, ParseException e) {
				if(objects!=null){
					ParseObject.pinAllInBackground(objects);
				}
				else {
					Log.i("NULO2","NULO");
				}

			}
		});


		ParseQuery<Lugar> queryLugar = ParseQuery.getQuery(Lugar.class);
		queryLugar.findInBackground(new FindCallback<Lugar>() {
			@Override
			public void done(List<Lugar> objects, ParseException e) {
				if(objects!=null){
					ParseObject.pinAllInBackground(objects);
				}
			}
		});

		ParseQuery<Persona> queryPersona = ParseQuery.getQuery(Persona.class);
		queryPersona.findInBackground(new FindCallback<Persona>() {
			@Override
			public void done(List<Persona> objects, ParseException e) {
				if(objects!=null){
					ParseObject.pinAllInBackground(objects);
				}
			}
		});

		ParseQuery<PersonaRolAct> queryPersonaRolAct = ParseQuery.getQuery(PersonaRolAct.class);
		queryPersonaRolAct.findInBackground(new FindCallback<PersonaRolAct>() {
			@Override
			public void done(List<PersonaRolAct> objects, ParseException e) {
				if(objects!=null){
					ParseObject.pinAllInBackground(objects);
				}
				else {
					Log.i("NULO4","NULO");
				}
			}
		});


		ParseQuery<PersonaRolOrg> queryPersonaRolOrg = ParseQuery.getQuery(PersonaRolOrg.class);
		queryPersonaRolOrg.setLimit(1000);
		queryPersonaRolOrg.findInBackground(new FindCallback<PersonaRolOrg>() {
			@Override
			public void done(List<PersonaRolOrg> objects, ParseException e) {
				if(objects!=null){
					ParseObject.pinAllInBackground(objects);
				}
				else {
					Log.i("NULO3","NULO");
				}

			}
		});


		ParseQuery<Org> queryOrg = ParseQuery.getQuery(Org.class);
		queryOrg.findInBackground(new FindCallback<Org>() {
			@Override
			public void done(List<Org> objects, ParseException e) {
				if(objects!=null){
					ParseObject.pinAllInBackground(objects);
				}
				else {
					Log.i("NULO3","NULO");
				}

			}
		});


		ParseQuery<Media> queryMedia = ParseQuery.getQuery(Media.class);
		queryMedia.findInBackground(new FindCallback<Media>() {
			@Override
			public void done(List<Media> objects, ParseException e) {
				if(objects!=null){
					ParseObject.pinAllInBackground(objects);
				}
			}
		});


	}

	//First time: Load server data and save to local.



	private void newFragment() {
		MUtil.stopTimer(getClass().getName());


			Log.i("boolean", String.valueOf(myapp.isFirstTime()));
			myapp.setSecondTime();
			myapp.setSecondPass();
			ViewPagerFragment loadDataFragment = new ViewPagerFragment();
			if (getActivity() != null) {

				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.container, loadDataFragment, "ViewPager");
				ft.commitAllowingStateLoss();
				bar.setVisibility(View.INVISIBLE);
			}
			else {
			}

	}


}


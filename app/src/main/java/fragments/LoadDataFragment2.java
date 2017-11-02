package fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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
	public static ImageView splash_first;

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


				//hacerQueriesyGuardarlasEnLocalconCheck();
			}

		}

		else {
			if (!myapp.checkConnection()) {
				//TODO Alert no internet.


			}
			else {


				//hacerQueriesyGuardarlasEnLocalconCheck();
			}
		}

	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View RootView = inflater.inflate(R.layout.splash_layout, container, false);
		bar = (ProgressBar) RootView.findViewById(R.id.progressBar);

		splash_first = (ImageView) RootView.findViewById(R.id.splash_first);

		splash_first.setVisibility(View.VISIBLE);

		//splash_first.setVisibility(View.VISIBLE);;
		return RootView;

	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// Restore State Here


	}

	@Override
	public void onStart() {

		super.onStart();


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

	private void hacerQueries(){
		ParseQuery<ParseObject> query =ParseQuery.getQuery("Actividad");
		ParseQuery<ParseObject> query2 =ParseQuery.getQuery("ActConAct");
		ParseQuery<ParseObject> query3 =ParseQuery.getQuery("Lugar");
		ParseQuery<ParseObject> query4 =ParseQuery.getQuery("Persona");
		ParseQuery<ParseObject> query5 =ParseQuery.getQuery("PersonaRolAct");
		ParseQuery<ParseObject> query6 =ParseQuery.getQuery("PersonaRolOrg");
		ParseQuery<ParseObject> query7 =ParseQuery.getQuery("Org");
		ParseQuery<ParseObject> query8 =ParseQuery.getQuery("Media");
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

		query.findInBackground().continueWithTask(new Continuation<List<ParseObject>, Task<List<ParseObject>>>() {
			@Override
			public Task<List<ParseObject>>then(Task<List<ParseObject>> task) throws Exception {
				Log.i("PASAQUERIES","PASA");
				ParseObject.pinAllInBackground(task.getResult());
				return task;
			}
		});

		query2.findInBackground().continueWithTask(new Continuation<List<ParseObject>, Task<List<ParseObject>>>() {
			@Override
			public Task<List<ParseObject>>then(Task<List<ParseObject>> task) throws Exception {
				Log.i("PASAQUERIES","PASA");
				ParseObject.pinAllInBackground(task.getResult());
				return task;
			}
		});

		query3.findInBackground().continueWithTask(new Continuation<List<ParseObject>, Task<List<ParseObject>>>() {
			@Override
			public Task<List<ParseObject>>then(Task<List<ParseObject>> task) throws Exception {
				Log.i("PASAQUERIES","PASA");
				ParseObject.pinAllInBackground(task.getResult());
				return task;
			}
		});

		query4.findInBackground().continueWithTask(new Continuation<List<ParseObject>, Task<List<ParseObject>>>() {
			@Override
			public Task<List<ParseObject>>then(Task<List<ParseObject>> task) throws Exception {
				Log.i("PASAQUERIES","PASA");
				ParseObject.pinAllInBackground(task.getResult());
				return task;
			}
		});

		query5.findInBackground().continueWithTask(new Continuation<List<ParseObject>, Task<List<ParseObject>>>() {
			@Override
			public Task<List<ParseObject>>then(Task<List<ParseObject>> task) throws Exception {
				Log.i("PASAQUERIES","PASA");
				ParseObject.pinAllInBackground(task.getResult());

				return task;
			}
		});

		query6.findInBackground().continueWithTask(new Continuation<List<ParseObject>, Task<List<ParseObject>>>() {
			@Override
			public Task<List<ParseObject>>then(Task<List<ParseObject>> task) throws Exception {
				Log.i("PASAQUERIES","PASA");
				ParseObject.pinAllInBackground(task.getResult());
				return task;
			}
		});

		query7.findInBackground().continueWithTask(new Continuation<List<ParseObject>, Task<List<ParseObject>>>() {
			@Override
			public Task<List<ParseObject>>then(Task<List<ParseObject>> task) throws Exception {
				Log.i("PASAQUERIES","PASA");
				ParseObject.pinAllInBackground(task.getResult());
				return task;
			}
		});

		query8.findInBackground().continueWithTask(new Continuation<List<ParseObject>, Task<List<ParseObject>>>() {
			@Override
			public Task<List<ParseObject>>then(Task<List<ParseObject>> task) throws Exception {
				Log.i("PASAQUERIES","PASA");
				ParseObject.pinAllInBackground(task.getResult());
				return task;
			}
		});



	}

	private void hacerQueriesyGuardarlasEnLocalconCheck(){
		final CountDownLatch mCountDownLatch = new CountDownLatch(8);
		for (int threadNo = 0; threadNo < 4000; threadNo++) {
			Runnable t = new LatchedThread(mCountDownLatch);
			new Thread(t).start();
		}
		ParseQuery<ParseObject> query =ParseQuery.getQuery("Actividad");
		ParseQuery<ParseObject> query2 =ParseQuery.getQuery("ActConAct");
		ParseQuery<ParseObject> query3 =ParseQuery.getQuery("Lugar");
		ParseQuery<ParseObject> query4 =ParseQuery.getQuery("Persona");
		ParseQuery<ParseObject> query5 =ParseQuery.getQuery("PersonaRolAct");
		ParseQuery<ParseObject> query6 =ParseQuery.getQuery("PersonaRolOrg");
		ParseQuery<ParseObject> query7 =ParseQuery.getQuery("Org");
		ParseQuery<ParseObject> query8 =ParseQuery.getQuery("Media");

		query.setLimit(1000);
		query2.setLimit(1000);
		query4.setLimit(1000);
		query5.setLimit(1000);

		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> parseObjects, ParseException e) {
				if (null == e) {
					ParseObject.pinAllInBackground(parseObjects);
					mCountDownLatch.countDown();
				}
			}
		});
		query2.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> parseObjects, ParseException e) {
				if (null == e) {
					ParseObject.pinAllInBackground(parseObjects);
					mCountDownLatch.countDown();
				}
			}
		});

		query3.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> parseObjects, ParseException e) {
				if (null == e) {
					ParseObject.pinAllInBackground(parseObjects);
					mCountDownLatch.countDown();
				}
			}
		});

		query4.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> parseObjects, ParseException e) {
				if (null == e) {
					ParseObject.pinAllInBackground(parseObjects);
					mCountDownLatch.countDown();
				}
			}
		});

		query5.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> parseObjects, ParseException e) {
				if (null == e) {
					ParseObject.pinAllInBackground(parseObjects);
					mCountDownLatch.countDown();
				}
			}
		});

		query6.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> parseObjects, ParseException e) {
				if (null == e) {
					ParseObject.pinAllInBackground(parseObjects);
					mCountDownLatch.countDown();
				}
			}
		});

		query7.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> parseObjects, ParseException e) {
				if (null == e) {
					ParseObject.pinAllInBackground(parseObjects);
					mCountDownLatch.countDown();
				}
			}
		});

		query8.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> parseObjects, ParseException e) {
				if (null == e) {
					ParseObject.pinAllInBackground(parseObjects);
					mCountDownLatch.countDown();
				}
			}
		});

	/*	try {
			mCountDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			newFragment();
		}*/

	}

	//From second time:
	// if connection available, load server data, update local
	// if not, load local data, finish.
	private void loadDataAndUpdateLocal() {


		Log.e(getClass().getName(), "call loadServerDataAndSaveLocal");

		MUtil.isUpdateLocal = true;


	  /*ParseQuery<Actividad> query = ParseQuery.getQuery(Actividad.class);

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
					i++;
					Log.i("HOLAENTERO", String.valueOf(i));

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
					i++;
					Log.i("HOLAENTERO2", String.valueOf(i));

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
					i++;
					Log.i("HOLAENTERO3", String.valueOf(i));

				}
			}
		});

		ParseQuery<Persona> queryPersona = ParseQuery.getQuery(Persona.class);
		queryPersona.findInBackground(new FindCallback<Persona>() {
			@Override
			public void done(List<Persona> objects, ParseException e) {
				if(objects!=null){
					ParseObject.pinAllInBackground(objects);
					i++;
					Log.i("HOLAENTERO4", String.valueOf(i));

				}
			}
		});

		ParseQuery<PersonaRolAct> queryPersonaRolAct = ParseQuery.getQuery(PersonaRolAct.class);
		queryPersonaRolAct.findInBackground(new FindCallback<PersonaRolAct>() {
			@Override
			public void done(List<PersonaRolAct> objects, ParseException e) {
				if(objects!=null){
					ParseObject.pinAllInBackground(objects);
					i++;
					Log.i("HOLAENTERO5", String.valueOf(i));

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
					i++;
					Log.i("HOLAENTERO6", String.valueOf(i));

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
					i++;
					Log.i("HOLAENTERO7", String.valueOf(i));

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
					i++;
					Log.i("HOLAENTER8O", String.valueOf(i));

				}
			}
		});


	}

	//First time: Load server data and save to local.



	private void newFragment() {
		MUtil.stopTimer(getClass().getName());
		myapp.setSecondTime();

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


	public class LatchedThread extends Thread {
		private final CountDownLatch startLatch;

		public LatchedThread(CountDownLatch startLatch) {
			this.startLatch = startLatch;
		}
		public void run() {
			try {
				startLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				newFragment();
			}
		}
	}







}


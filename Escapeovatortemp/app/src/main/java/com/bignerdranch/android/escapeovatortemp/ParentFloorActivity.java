package com.bignerdranch.android.escapeovatortemp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * H. Ben Reed, Anthony Hessler
 * */

public class ParentFloorActivity extends AppCompatActivity {
    //All tags and keys for savedInstanceStates and Extras
    //H. Ben Reed
    private static final String TAG = "ParentFloorActivity";
    public static final String KEY_FLASHLIGHT = "Flashlight";
    public static final String KEY_BLACKLIGHT = "Blacklight";
    public static final String KEY_KEY = "Key";
    public static final String KEY_LOCKPICK = "Lockpick";
    public static final String KEY_XRAYGLASSES = "XRayGlasses";
    public static final String KEY_CHEST = "Chest";
    public static final String KEY_FLOOR = "Floor";
    private static final int NOTE = 0;
    public static final String EXTRA_FLOOR = "com.bignerdranch.android.escapeovatortemp.floor";

    public int mFloor;

    //All Buttons, ImageButtons, and other widgets shared by all Child Activities
    //H. Ben Reed
    public Button mElevatorButton;

    public ImageButton mGrabFlashlightButton;
    public ImageButton mSafeButton;

    public ImageButton mGrabKeyButton;
    public ImageButton mChestButton;

    public ImageButton mGrabBlacklightButton;

    public ImageButton mGrabLockpickButton;

    public ImageButton mGrabXRayGlassesButton;

    public ImageButton mNoteButton;
    public ImageButton mFlashlightButton;
    public ImageButton mXRayGlassesButton;
    public ImageButton mBlacklightButton;
    public ImageButton mKeyButton;
    public ImageButton mLockpickButton;

    public ImageView mBackground4;
    public ImageView mBackground5;

    public TextView mClue;

    //Booleans that inform other activities, mainly child activities, of whether or not the user has a certain tool.
    //Used in conjunction with Anthony's SQLite code.
    //H. Ben Reed
    public boolean mFlashlightHeld = false;
    public boolean mXRayGlassesHeld = false;
    public boolean mBlacklightHeld = false;
    public boolean mKeyHeld = false;
    public boolean mLockpickHeld = false;
    public boolean mChestOpened = false;

    /*
    public static Intent newIntent(Context packageContext, int mFloor) {
        Intent intent = new Intent(packageContext, ParentFloorActivity.class);
        intent.putExtra(EXTRA_FLOOR, mFloor);
        return intent;
    }
    */

    public static int insertFloor(Intent result) {
        return result.getIntExtra(EXTRA_FLOOR, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_floor);

        mFloor = getIntent().getIntExtra(EXTRA_FLOOR, 1);

        /*
        if(mFloor == 1){
            Intent intent = Floor1Activity.newIntent(ParentFloorActivity.this, mFloor);
            startActivity(intent);
        } else if(mFloor == 2){
            Intent intent = Floor2Activity.newIntent(ParentFloorActivity.this, mFloor);
            startActivity(intent);
        }else if(mFloor == 3){
            Intent intent = Floor3Activity.newIntent(ParentFloorActivity.this, mFloor);
            startActivity(intent);
        }else if(mFloor == 4){
            Intent intent = Floor4Activity.newIntent(ParentFloorActivity.this, mFloor);
            startActivity(intent);
        }else if(mFloor == 5){
            Intent intent = Floor5Activity.newIntent(ParentFloorActivity.this, mFloor);
            startActivity(intent);
        }
        */

        //Failed savedInstanceState code, untouched to avoid messing with current functionality. Support dropped in favor of SQLite.
        //H. Ben Reed
        if (savedInstanceState != null){
            mXRayGlassesHeld = savedInstanceState.getBoolean(KEY_XRAYGLASSES, false);
            mFlashlightHeld = savedInstanceState.getBoolean(KEY_FLASHLIGHT, false);
            mBlacklightHeld = savedInstanceState.getBoolean(KEY_BLACKLIGHT, false);
            mKeyHeld = savedInstanceState.getBoolean(KEY_KEY, false);
            mLockpickHeld = savedInstanceState.getBoolean(KEY_LOCKPICK, false);
            mChestOpened = savedInstanceState.getBoolean(KEY_CHEST, false);
        }

        mElevatorButton = (Button) (findViewById(R.id.elevator_button));
        mElevatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ElevatorFragment fragment = new ElevatorFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(fragment, null);
                transaction.commit();
            }
        });

       /*
       mNoteButton = (ImageButton) findViewById(R.id.note_button);
        mNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent note = new Intent(getApplicationContext(), MenuFragment.class);
                startActivityForResult(note, NOTE);
            }
        });
        */

       //Base codes for all buttons, used as reference when writing code for ChildActivities
        //H. Ben Reed
        mFlashlightButton = (ImageButton) findViewById(R.id.flashlight_button);
        mFlashlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFlashlightButton.isEnabled()) {
                    if (mFloor == 5){
                        mGrabXRayGlassesButton.setVisibility(View.VISIBLE);
                    }
                    //changes background of specific floor
                }
            }
        });

        mXRayGlassesButton = (ImageButton) findViewById(R.id.xrayglasses_button);
        mXRayGlassesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mXRayGlassesButton.isEnabled()) {
                    if (mFloor == 2){
                        mGrabKeyButton.setVisibility(View.VISIBLE);
                    }
                    //changes background of specific floor
                }
            }
        });

        mBlacklightButton = (ImageButton) findViewById(R.id.blacklight_button);
        mBlacklightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBlacklightButton.isEnabled()) {
                    //changes background of specific floor
                }
            }
        });

        mKeyButton = (ImageButton) findViewById(R.id.key_button);
        mKeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mKeyButton.isEnabled()) {
                    //changes background of specific floor
                }
            }
        });

        mLockpickButton = (ImageButton) findViewById(R.id.lockpick_button);
        mLockpickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLockpickButton.isEnabled()) {
                    //changes background of specific floor
                }
            }
        });

        //updateToolbar();
    }
    /*
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putBoolean(KEY_FLASHLIGHT, mFlashlightHeld);
        savedInstanceState.putBoolean(KEY_BLACKLIGHT, mBlacklightHeld);
        savedInstanceState.putBoolean(KEY_KEY, mKeyHeld);
        savedInstanceState.putBoolean(KEY_LOCKPICK, mLockpickHeld);
        savedInstanceState.putBoolean(KEY_XRAYGLASSES, mXRayGlassesHeld);
        savedInstanceState.putBoolean(KEY_CHEST, mChestOpened);
        savedInstanceState.putInt(KEY_FLOOR, mFloor);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
        Intent intent = new Intent(ParentFloorActivity.this, Floor1Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
*/
    /*
    Checks to see if the note associated with a tool exists and sets the flag for whether the player has that tool
    based on whether the player has that note. That way, what tools the player has are saved in thr SQLite database
    via the notes. Pretty clever, right?
    This method made by Anthony Hessler
     */
    public void updateToolbar()
    {
        mFlashlightHeld = Notepad.get(ParentFloorActivity.this).getNote(Floor1Activity.sAutoGeneratedNotes.get(0).getId()) != null;
        mFlashlightButton.setEnabled(mFlashlightHeld);

        mBlacklightHeld = Notepad.get(ParentFloorActivity.this).getNote(Floor1Activity.sAutoGeneratedNotes.get(2).getId()) != null;
        mBlacklightButton.setEnabled(mBlacklightHeld);

        mXRayGlassesHeld = Notepad.get(ParentFloorActivity.this).getNote(Floor1Activity.sAutoGeneratedNotes.get(4).getId()) != null;
        mXRayGlassesButton.setEnabled(mXRayGlassesHeld);

        mKeyHeld = Notepad.get(ParentFloorActivity.this).getNote(Floor1Activity.sAutoGeneratedNotes.get(1).getId()) != null;
        mKeyButton.setEnabled(mKeyHeld);

        mLockpickHeld = Notepad.get(ParentFloorActivity.this).getNote(Floor1Activity.sAutoGeneratedNotes.get(3).getId()) != null;
        mLockpickButton.setEnabled(mLockpickHeld);
    }

    //Reveals the number of the next floor to go to. Modifies the value defined as mClue for the current floor.
    //H. Ben Reed
    public void useBlacklight()
    {
        mClue.setVisibility(View.VISIBLE);
        mClue.setTextColor(getResources().getColor(R.color.colorUV));
        mBlacklightButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_uv_on));
    }

    //Disables the effect of the blacklight. Currently not in use. Was originally called when other tools were used.
    //H. Ben Reed
    public void disableBlacklight()
    {
        mClue.setVisibility(View.INVISIBLE);
        mBlacklightButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_uv_off));
    }
}

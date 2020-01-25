package com.example.a2019scouting;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.a2019scouting.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner teamMembersSpinner = findViewById(R.id.teamMemberSpinner);
        ArrayAdapter<CharSequence> TMadapt = ArrayAdapter.createFromResource(this, R.array.TeamMember, R.layout.spinner_row);
        TMadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamMembersSpinner.setAdapter(TMadapt);

        Spinner teamNumbersSpinner = findViewById(R.id.teamNumberSpinner);
        ArrayAdapter<CharSequence> TNadapt = ArrayAdapter.createFromResource(this, R.array.TeamNumber, R.layout.spinner_row);
        TMadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamNumbersSpinner.setAdapter(TNadapt);

        Spinner startingLocationSpinner = findViewById(R.id.startingPositionSpinner);
        ArrayAdapter<CharSequence> SLadapt = ArrayAdapter.createFromResource(this, R.array.StartingLocation, R.layout.spinner_row);
        TMadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startingLocationSpinner.setAdapter(SLadapt);

        Spinner startingHabitatSpinner = findViewById(R.id.startingHABSpinner);
        ArrayAdapter<CharSequence> SHadapt = ArrayAdapter.createFromResource(this, R.array.StartingHabitatLevel, R.layout.spinner_row);
        TMadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startingHabitatSpinner.setAdapter(SHadapt);

        Spinner rocketLevelReachedSpinner = findViewById(R.id.rocketLevelSpinner);
        ArrayAdapter<CharSequence> RLadapt = ArrayAdapter.createFromResource(this, R.array.RocketLevel, R.layout.spinner_row);
        TMadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rocketLevelReachedSpinner.setAdapter(RLadapt);

        Spinner habitatLevelEndGameSpinner = findViewById(R.id.habitatLevelSpinner);
        ArrayAdapter<CharSequence> HLadapt = ArrayAdapter.createFromResource(this, R.array.HABLevelEndGame, R.layout.spinner_row);
        TMadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        habitatLevelEndGameSpinner.setAdapter(HLadapt);

        Spinner liftOtherRobotSpinner = findViewById(R.id.liftOthersSpinner);
        ArrayAdapter<CharSequence> LOadapt = ArrayAdapter.createFromResource(this, R.array.LiftOthers, R.layout.spinner_row);
        TMadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        liftOtherRobotSpinner.setAdapter(LOadapt);

        EditText matchNum = findViewById(R.id.matchNumberText);
        CheckBox habitatLine = findViewById(R.id.crossedHABLineCheckBox);
        EditText sandstormHatchRocket = findViewById(R.id.sandstormHatchRocket);
        EditText sandstormHatchShip = findViewById(R.id.sandstormHatchShip);
        EditText sandstormCargoRocket = findViewById(R.id.sandstormCargoRocket);
        EditText sandstormCargoShip = findViewById(R.id.sandstormCargoShip);
        EditText teleopHatchRocket = findViewById(R.id.teleopHatchRocket);
        EditText teleopHatchShip = findViewById(R.id.teleopHatchShip);
        EditText teleopCargoRocket = findViewById(R.id.teleopCargoRocket);
        EditText teleopCargoShip = findViewById(R.id.teleopCargoShip);
        CheckBox hatchPickUp = findViewById(R.id.hatchPickupCheckbox);
        CheckBox defense = findViewById(R.id.defenseCheckBox);
        EditText comments = findViewById(R.id.commentsText);
        Button submit = findViewById(R.id.submitButton);

        submit.setOnClickListener(v -> {
            if (teamMembersSpinner.getSelectedItem().toString().equals("") || teamMembersSpinner.getSelectedItem().toString().equals("-4003-") || teamMembersSpinner.getSelectedItem().toString().equals("-5980-")) {
                Snackbar.make(findViewById(R.id.teamMemberSpinner), "Please enter Scouter Name", Snackbar.LENGTH_SHORT).show();
            } else if (teamNumbersSpinner.getSelectedItem().toString().equals("")) {
                Snackbar.make(findViewById(R.id.teamNumberSpinner), "Please enter Team Number", Snackbar.LENGTH_SHORT).show();
            } else if (matchNum.getText().toString().equals("")) {
                Snackbar.make(findViewById(R.id.matchNumberText), "Please enter Match Number", Snackbar.LENGTH_SHORT).show();
            } else if (startingLocationSpinner.getSelectedItem().toString().equals("")) {
                Snackbar.make(findViewById(R.id.startingPositionSpinner), "Please enter Sandstorm Starting Position", Snackbar.LENGTH_SHORT).show();
            } else if (startingHabitatSpinner.getSelectedItem().toString().equals("")) {
                Snackbar.make(findViewById(R.id.startingHABSpinner), "Please enter Sandstorm HAB Level", Snackbar.LENGTH_SHORT).show();
            } else if (rocketLevelReachedSpinner.getSelectedItem().toString().equals("")) {
                Snackbar.make(findViewById(R.id.rocketLevelSpinner), "Please enter Highest Rocket Level Reached", Snackbar.LENGTH_SHORT).show();
            } else if (habitatLevelEndGameSpinner.getSelectedItem().toString().equals("")) {
                Snackbar.make(findViewById(R.id.habitatLevelSpinner), "Please enter End Game HAB Level", Snackbar.LENGTH_SHORT).show();
            } else if (liftOtherRobotSpinner.getSelectedItem().toString().equals("")) {
                Snackbar.make(findViewById(R.id.liftOthersSpinner), "Please enter whether or not Lifted Others", Snackbar.LENGTH_SHORT).show();
            } else {
                JSONObject output = new JSONObject();
                try {
                    output.put("name", teamMembersSpinner.getSelectedItem());
                    output.put("team_num", Integer.parseInt(teamNumbersSpinner.getSelectedItem().toString()));
                    output.put("match_num", Integer.parseInt(matchNum.getText().toString()));
                    output.put("position", startingLocationSpinner.getSelectedItem().toString());
                    output.put("hab_lvl", Integer.parseInt(startingHabitatSpinner.getSelectedItem().toString()));
                    output.put("hab_line", habitatLine.isChecked());
                    output.put("SaHR", Integer.parseInt(sandstormHatchRocket.getText().toString()));
                    output.put("SaHS", Integer.parseInt(sandstormHatchShip.getText().toString()));
                    output.put("SaCR", Integer.parseInt(sandstormCargoRocket.getText().toString()));
                    output.put("SaCS", Integer.parseInt(sandstormCargoShip.getText().toString()));
                    output.put("TeHR", Integer.parseInt(teleopHatchRocket.getText().toString()));
                    output.put("TeHS", Integer.parseInt(teleopHatchShip.getText().toString()));
                    output.put("TeCR", Integer.parseInt(teleopCargoRocket.getText().toString()));
                    output.put("TeCS", Integer.parseInt(teleopCargoShip.getText().toString()));
                    output.put("rkt_lvl", Integer.parseInt(rocketLevelReachedSpinner.getSelectedItem().toString()));
                    output.put("hatch_PU", hatchPickUp.isChecked());
                    output.put("defense", defense.isChecked());
                    output.put("climb", Integer.parseInt(habitatLevelEndGameSpinner.getSelectedItem().toString()));
                    output.put("lift", Integer.parseInt(liftOtherRobotSpinner.getSelectedItem().toString()));
                    output.put("comments", comments.getText());
                    Log.d("output", output.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Write JSON to file
                String outStr = matchNum.getText() + "-" + teamNumbersSpinner.getSelectedItem().toString() + ".json";
                File dir = new File(Environment.getExternalStorageDirectory() + "/scoutingFiles/");
                if(!dir.exists()){
                    dir.mkdir();
                }

                try{
                    File outFile = new File(dir, outStr);
                    FileOutputStream out = new FileOutputStream(outFile);
                    out.write(output.toString().getBytes());
                    out.close();
                } catch (IOException e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }

                Snackbar.make(findViewById(R.id.myConstraintLayout), "Successfully Saved: " + outStr, Snackbar.LENGTH_SHORT).show();

                teamNumbersSpinner.setSelection(0);
                matchNum.setText("");
                startingLocationSpinner.setSelection(0);
                startingHabitatSpinner.setSelection(0);
                habitatLine.setChecked(false);
                sandstormHatchRocket.setText("0");
                sandstormHatchShip.setText("0");
                sandstormCargoRocket.setText("0");
                sandstormCargoShip.setText("0");
                teleopHatchRocket.setText("0");
                teleopHatchShip.setText("0");
                teleopCargoRocket.setText("0");
                teleopCargoShip.setText("0");
                rocketLevelReachedSpinner.setSelection(0);
                hatchPickUp.setChecked(false);
                defense.setChecked(false);
                habitatLevelEndGameSpinner.setSelection(0);
                liftOtherRobotSpinner.setSelection(0);
                comments.setText("");

            }
        });


        Button sandHatchRocketP = findViewById(R.id.sandstormHatchRocketPlus);
        sandHatchRocketP.setOnClickListener(v -> {
            int start = Integer.parseInt(sandstormHatchRocket.getText().toString());
            if (start < 12) {
                start++;
                String result = String.valueOf(start);
                sandstormHatchRocket.setText(result);
            }
        });

        Button sandHatchRocketM = findViewById(R.id.sandstormHatchRocketMinus);
        sandHatchRocketM.setOnClickListener(v -> {
            int start = Integer.parseInt(sandstormHatchRocket.getText().toString());
            if (start > 0) {
                start--;
                String result = String.valueOf(start);
                sandstormHatchRocket.setText(result);
            }
        });

        Button sandHatchShipP = findViewById(R.id.sandstormHatchShipPlus);
        sandHatchShipP.setOnClickListener(v -> {
            int start = Integer.parseInt(sandstormHatchShip.getText().toString());
            if (start < 8) {
                start++;
                String result = String.valueOf(start);
                sandstormHatchShip.setText(result);
            }
        });

        Button sandHatchShipM = findViewById(R.id.sandstormHatchShipMinus);
        sandHatchShipM.setOnClickListener(v -> {
            int start = Integer.parseInt(sandstormHatchShip.getText().toString());
            if (start > 0) {
                start--;
                String result = String.valueOf(start);
                sandstormHatchShip.setText(result);
            }
        });

        Button sandCargoRocketP = findViewById(R.id.sandstormCargoRocketPlus);
        sandCargoRocketP.setOnClickListener(v -> {
            int start = Integer.parseInt(sandstormCargoRocket.getText().toString());
            if (start < 12) {
                start++;
                String result = String.valueOf(start);
                sandstormCargoRocket.setText(result);
            }
        });
        Button sandCargoRocketM = findViewById(R.id.sandstormCargoRocketMinus);
        sandCargoRocketM.setOnClickListener(v -> {
            int start = Integer.parseInt(sandstormCargoRocket.getText().toString());
            if (start > 0) {
                start--;
                String result = String.valueOf(start);
                sandstormCargoRocket.setText(result);
            }
        });

        Button sandCargoShipP = findViewById(R.id.sandstormCargoShipPlus);
        sandCargoShipP.setOnClickListener(v -> {
            int start = Integer.parseInt(sandstormCargoShip.getText().toString());
            if (start < 8) {
                start++;
                String result = String.valueOf(start);
                sandstormCargoShip.setText(result);
            }
        });

        Button sandCargoShipM = findViewById(R.id.sandstormCargoShipMinus);
        sandCargoShipM.setOnClickListener(v -> {
            int start = Integer.parseInt(sandstormCargoShip.getText().toString());
            if (start > 0) {
                start--;
                String result = String.valueOf(start);
                sandstormCargoShip.setText(result);
            }
        });

        Button teleHatchRocketP = findViewById(R.id.teleopHatchRocketPlus);
        teleHatchRocketP.setOnClickListener(v -> {
            int start = Integer.parseInt(teleopHatchRocket.getText().toString());
            if (start < 12) {
                start++;
                String result = String.valueOf(start);
                teleopHatchRocket.setText(result);
            }
        });

        Button teleHatchRocketM = findViewById(R.id.teleopHatchRocketMinus);
        teleHatchRocketM.setOnClickListener(v -> {
            int start = Integer.parseInt(teleopHatchRocket.getText().toString());
            if (start > 0) {
                start--;
                String result = String.valueOf(start);
                teleopHatchRocket.setText(result);
            }
        });

        Button teleHatchShipP = findViewById(R.id.teleopHatchShipPlus);
        teleHatchShipP.setOnClickListener(v -> {
            int start = Integer.parseInt(teleopHatchShip.getText().toString());
            if (start < 8) {
                start++;
                String result = String.valueOf(start);
                teleopHatchShip.setText(result);
            }
        });

        Button teleHatchShipM = findViewById(R.id.teleopHatchShipMinus);
        teleHatchShipM.setOnClickListener(v -> {
            int start = Integer.parseInt(teleopHatchShip.getText().toString());
            if (start > 0) {
                start--;
                String result = String.valueOf(start);
                teleopHatchShip.setText(result);
            }
        });

        Button teleCargoRocketP = findViewById(R.id.teleopCargoRocketPlus);
        teleCargoRocketP.setOnClickListener(v -> {
            int start = Integer.parseInt(teleopCargoRocket.getText().toString());
            if (start < 12) {
                start++;
                String result = String.valueOf(start);
                teleopCargoRocket.setText(result);
            }
        });

        Button teleCargoRocketM = findViewById(R.id.teleopCargoRocketMinus);
        teleCargoRocketM.setOnClickListener(v -> {
            int start = Integer.parseInt(teleopCargoRocket.getText().toString());
            if (start > 0) {
                start--;
                String result = String.valueOf(start);
                teleopCargoRocket.setText(result);
            }
        });

        Button teleCargoShipP = findViewById(R.id.teleopCargoShipPlus);
        teleCargoShipP.setOnClickListener(v -> {
            int start = Integer.parseInt(teleopCargoShip.getText().toString());
            if (start < 8) {
                start++;
                String result = String.valueOf(start);
                teleopCargoShip.setText(result);
            }
        });

        Button teleCargoShipM = findViewById(R.id.teleopCargoShipMinus);
        teleCargoShipM.setOnClickListener(v -> {
            int start = Integer.parseInt(teleopCargoShip.getText().toString());
            if (start > 0) {
                start--;
                String result = String.valueOf(start);
                teleopCargoShip.setText(result);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item= menu.findItem(R.id.action_settings);
        item.setVisible(false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}

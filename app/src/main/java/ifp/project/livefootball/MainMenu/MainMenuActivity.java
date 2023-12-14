package ifp.project.livefootball.MainMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import ifp.project.livefootball.Match.CreateMatchActivity;
import ifp.project.livefootball.Match.EditMatchActivity;
import ifp.project.livefootball.R;
import ifp.project.livefootball.Database.Database;
import ifp.project.livefootball.Team.ListTeamActivity;

public class MainMenuActivity extends AppCompatActivity {
    private Database db;
    private ArrayList <String> arrayList= new ArrayList<String>();
    private ArrayAdapter <String> arrayAdapter;
    private ListView listView1;
    private Button button1;
    private String textContent;
    protected Intent changeActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        listView1= (ListView) findViewById(R.id.listView_mainMenu);
        button1= (Button) findViewById(R.id.button1_mainMenu);

        db= new Database(this);
        arrayList= db.getMatches();

        arrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView1.setAdapter(arrayAdapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                textContent= adapterView.getItemAtPosition(i).toString();
                changeActivity=new Intent(MainMenuActivity.this, EditMatchActivity.class);
                changeActivity.putExtra("MATCH", textContent).toString();
                finish();
                startActivity(changeActivity);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity= new Intent(MainMenuActivity.this, CreateMatchActivity.class);
                finish();
                startActivity(changeActivity);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.options_main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.Teams_options_main_menu:
                changeActivity= new Intent(MainMenuActivity.this, ListTeamActivity.class);
                finish();
                startActivity(changeActivity);
                return true;

            case R.id.Exit_options_main_menu:
                System.exit(0);

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
package com.thoromirtech.comicconbingo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;

public class BingoActivity extends Activity {
    /** Called when the activity is first created. */
	private Game game;
	DatabaseHelper db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(this);
        game = new Game(this, db);
        setContentView(game);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.new_game:
            newGame();
            return true;
        case R.id.settings:
            Intent intent = new Intent(BingoActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
         default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    private void newGame(){
        game = new Game(this, db);
        this.setContentView(game);
    }
}
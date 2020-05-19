package com.example.recyclerviewsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Adapter adapter;
    private List<Items> itemsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillList();
        setUpRecyclerView();
    }
    private void fillList() {
        itemsList = new ArrayList<>();
        itemsList.add(new Items(R.drawable.apple,"A","A for Apple"));
        itemsList.add(new Items(R.drawable.ball,"B","B for Ball"));
        itemsList.add(new Items(R.drawable.cat,"C","C for Cat"));
        itemsList.add(new Items(R.drawable.donkey,"D","D for Donkey"));
        itemsList.add(new Items(R.drawable.elephant,"E","E for Elephant"));
        itemsList.add(new Items(R.drawable.fish,"F","F for Fish"));
        itemsList.add(new Items(R.drawable.goat,"G","G for Goat"));
    }
    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new Adapter(itemsList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

}

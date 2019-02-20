package com.example.minesh.navigationd;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.SearchView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;


import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;
import java.util.List;


public class Faculty_info_list extends AppCompatActivity{

    private RecyclerView recyclerView;
    private Faculty_info_adapter adapter;
    List<Faculty_list_classs> facultyList;
    private Intent intent;
    private android.support.v7.widget.SearchView searchview;
    private Toolbar toolbar;
    private SlidrInterface slidr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        slidr= Slidr.attach(this);
        slidr.unlock();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Faculty Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAfterTransition();
            }
        });


        facultyList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        intent = new Intent(Faculty_info_list.this, FacultyInformation.class);

        addList();

        Context con=recyclerView.getContext();
        LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation(con,R.anim.lay_fall_down);
        adapter=new Faculty_info_adapter(this,facultyList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
        onClickList();
    }

    private void addList() {

        facultyList.add(
                new Faculty_list_classs(
                        R.drawable.aniruddhfataniya,
                        "Aniruddhkumar G. Fataniya",
                        "Assistant Professor"
                )
        );
        facultyList.add(
                new Faculty_list_classs(
                        R.drawable.arpitashah,
                        "Arpita Shah",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new Faculty_list_classs(
                        R.drawable.ashwinmakwana,
                        "Ashwin Makwana",
                        "Assistant Professor"
                )
        );
        facultyList.add(
                new Faculty_list_classs(
                        R.drawable.chintanbhatt,
                        "Chintan Bhatt",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new Faculty_list_classs(
                        R.drawable.devyanipanchal,
                        "Devyani Panchal",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new Faculty_list_classs(
                        R.drawable.dippalishrani,
                        "Dippal Israni",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new Faculty_list_classs(
                        R.drawable.dipsidave,
                        "Dipsi Dave",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new Faculty_list_classs(
                        R.drawable.hardikmandora,
                        "Hardik Mandora",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new Faculty_list_classs(
                        R.drawable.khushboopatel,
                        "Khushboo Patel",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new Faculty_list_classs(
                        R.drawable.krutidhyani,
                        "Kruti Dhyani",
                        "Assistant Professor"
                )
        );


        facultyList.add(
                new Faculty_list_classs(
                        R.drawable.martinparmar,
                        "Martin Parmar",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new Faculty_list_classs(
                        R.drawable.minalmaniar,
                        "Minal Maniar",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new Faculty_list_classs(
                        R.drawable.mrugendrarahevar,
                        "Mrugendrasinh Rahevar",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new Faculty_list_classs(
                        R.drawable.padmavatibindulal,
                        "Padmavathi Bindulal",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new Faculty_list_classs(
                        R.drawable.riteshpatel,
                        "Ritesh Patel",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new Faculty_list_classs(
                        R.drawable.ronakpatel,
                        "Ronak Patel",
                        "Assistant Professor"
                )
        );

        facultyList.add(
                new Faculty_list_classs(
                        R.drawable.vaishalimewada,
                        "Vaishali Mewada",
                        "Assistant Professor"
                )
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.material_search_view, menu);

        MenuItem search = menu.findItem(R.id.action_search);
        searchview= (SearchView) search.getActionView();
        search(searchview);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final Faculty_info_adapter adapt=(Faculty_info_adapter) recyclerView.getAdapter();
                adapt.getFilter().filter(newText);
                onClickList();
                return true;
            }
        });
    }

    private void onClickList(){
        final Faculty_info_adapter adaptNew=(Faculty_info_adapter)recyclerView.getAdapter();
        adaptNew.setOnItemClickListener(new Faculty_info_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                facultyList=adaptNew.currentList();
                intent.putExtra("Faculty_retrieve_class", facultyList.get(position).gName());
                intent.putExtra("image",(int)facultyList.get(position).gImage());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        facultyList.clear();
        addList();
        Context con = recyclerView.getContext();
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(con, R.anim.lay_fall_down);
        adapter = new Faculty_info_adapter(this, facultyList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
        onClickList();
    }

}

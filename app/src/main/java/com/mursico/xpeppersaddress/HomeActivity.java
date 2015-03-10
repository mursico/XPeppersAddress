package com.mursico.xpeppersaddress;

import android.app.ListActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeActivity extends ListActivity  implements View.OnClickListener{
    private Button addButton;
    private EditText etSearch;
    public static List<User> rubricaList = new ArrayList<User>();
    static{
        rubricaList.add(new User("Paolo","Rossi","+39 334 000096"));
        rubricaList.add(new User("Luca","bianchi","+39 333 333333"));
        rubricaList.add(new User("Marco","bianchi","+39 322 123456"));
    }
    public static List<User> rubricaListtemp = new ArrayList<User>();

    private String selUserid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        etSearch = (EditText) findViewById(R.id.textSearch);

        etSearch.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                EditText search = (EditText) findViewById(R.id.textSearch);
                //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                rubricaListtemp.clear();
                String testo = search.getText().toString();
                if(testo == ""){
                    rubricaListtemp = rubricaList;
                }else {
                    List<User> list = new ArrayList<User>();


                    for (User us : rubricaList) {
                        if (us.getName().toLowerCase().contains(testo.toLowerCase()) || us.getSurname().toLowerCase().contains(testo.toLowerCase()) || us.getPhone().toLowerCase().contains(testo.toLowerCase()))
                            rubricaListtemp.add(us);

                    }
                   /* users = new User[list.size()];
                    users = list.toArray(users);*/
                }
               ArrayAdapter<User> adapter = new ArrayAdapter<User>( getBaseContext(),
                        android.R.layout.simple_list_item_1, rubricaListtemp);
                setListAdapter(adapter);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,
                android.R.layout.simple_list_item_1, rubricaList);
        setListAdapter(adapter);

        addButton = (Button) findViewById(R.id.add_button);
        addButton.setEnabled(true);
        addButton.setOnClickListener(this);
        registerForContextMenu(getListView());
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        String selectedWord = ((TextView) info.targetView).getText().toString();

        selUserid = selectedWord;
        menu.setHeaderTitle(selectedWord);

        menu.add(0, v.getId(), 0, "Edit");
        menu.add(0, v.getId(), 0, "Delete");
    }

    public boolean onContextItemSelected(MenuItem item) {

        if(item.getTitle() == "Edit"){
            Intent intent = new Intent(this, EditActivity.class);
            intent.putExtra("ID",selUserid);
            startActivity(intent);
        }
        if(item.getTitle() == "Delete"){

            for (int j = rubricaList.size()-1; j >= 0; j--) {
                if(rubricaList.get(j).getId().equals(selUserid))
                    rubricaList.remove(rubricaList.get(j));
            }

            ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,
                    android.R.layout.simple_list_item_1, rubricaList);
            setListAdapter(adapter);
            Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show();
        }
        return true;


    }

    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
        User uselection = (User) getListAdapter().getItem(position);

        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("ID",uselection.getId());
        startActivity(intent);

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_button: {
                Intent intent = new Intent(this, NewEntryActivity.class);
                startActivity(intent);
            }
            break;
        }
    }




}


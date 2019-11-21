package com.norbertoledo.pac_desarrollo_m08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class Activity2 extends AppCompatActivity implements DialogForm.DialogFormListener{


    // Declaraciones
    Button btnCreateTable;
    Button btnDeleteTable;
    Button btnAddUser;
    Button btnDeleteUsers;
    RecyclerView usersList;
    UserListItem userListItem;
    TextView usersListText;
    Button btnActivity1;
    DB db;
    User user;
    public DialogForm dialogForm;
    ArrayList<User> users = new ArrayList<User>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        // Mensaje de ingreso a Activity 2
        Toast.makeText(this, "Activity 2", Toast.LENGTH_SHORT).show();
        db = new DB(this,"pac", null, 1);


        // Instancias
        btnActivity1 = findViewById(R.id.btnActivity1);
        btnCreateTable = findViewById(R.id.btnCreateTable);
        btnDeleteTable = findViewById(R.id.btnDeleteTable);
        btnAddUser = findViewById(R.id.btnAddUser);
        btnDeleteUsers = findViewById(R.id.btnDeleteUsers);
        usersList = findViewById(R.id.usersList);
        usersListText = findViewById(R.id.usersListText);


        // Acciones de Boton
        btnActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity1(v);
            }
        });

        btnCreateTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTable();
            }
        });

        btnDeleteTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTable();
            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogForm();
            }
        });

        btnDeleteUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUsers();
            }
        });


        isTableExists();
    }

    // Evaluar si existe tabla creada para definir estado de botones
    private void isTableExists() {

        if(db.checkTableExist()){
            btnCreateTable.setEnabled(false);
            btnDeleteTable.setEnabled(true);
            btnAddUser.setEnabled(true);
            btnDeleteUsers.setEnabled(true);
            setUsersList();
        }else{
            btnCreateTable.setEnabled(true);
            btnDeleteTable.setEnabled(false);
            btnAddUser.setEnabled(false);
            btnDeleteUsers.setEnabled(false);
            usersListText.setText(R.string.db_no_table);
        }
    }

    // Abrir formulario de registro de nuevo usuario
    private void openDialogForm(){
        dialogForm = new DialogForm();
        dialogForm.show(getSupportFragmentManager(), "Dialog Form");
    }

    // Obtener los datos del Formulario y guardarlos en la DB
    @Override
    public void applyData(String name, String surname, String phone, String gender) {

        // Agrego a la Base de Datos
        if(db.setData(name, surname, phone, gender)) setUsersList();
    }

    // Crear tabla 'users'
    private void createTable(){
        db.createTable();
        btnCreateTable.setEnabled(false);
        btnDeleteTable.setEnabled(true);
        btnAddUser.setEnabled(true);
        setUsersList();
    }

    // Eliminar tabla 'users'
    private void deleteTable(){
        db.deleteTable();
        btnCreateTable.setEnabled(true);
        btnDeleteTable.setEnabled(false);
        btnAddUser.setEnabled(false);
        btnDeleteUsers.setEnabled(false);

        users.clear();
        usersList.setLayoutManager(new LinearLayoutManager(this));
        userListItem = new UserListItem(this, users);
        usersList.setAdapter(userListItem);
        usersListText.setText(R.string.db_no_table);
    }

    // Eliminar todos los registros de la tabla
    private void deleteUsers(){
        if(db.deleteData()) setUsersList();
    }

    // Obtener los usuarios de la DB y listaros en un RecyclerView
    private void setUsersList(){

        users.clear();

        usersListText.setText("");

        Cursor data = db.getData();

            if (data.getCount() > 0) {

                data.moveToFirst();

                do {

                    String name = data.getString(data.getColumnIndex("name"));
                    String surname = data.getString(data.getColumnIndex("surname"));
                    String phone = data.getString(data.getColumnIndex("phone"));
                    String gender = data.getString(data.getColumnIndex("gender"));

                    user = new User(name, surname, phone, gender);
                    users.add(user);

                    data.moveToNext();

                } while (!data.isAfterLast());

                data.close();

                btnDeleteUsers.setEnabled(true);

            } else {
                btnDeleteUsers.setEnabled(false);
                usersListText.setText(R.string.db_no_users);
            }


        usersList.setLayoutManager(new LinearLayoutManager(this));
        userListItem = new UserListItem(this, users);
        usersList.setAdapter(userListItem);

    }


    // Navegar a Activity 1
    private void gotoActivity1(View v){
        Intent intent =  new Intent(this, Activity1.class);
        startActivity(intent);
        finish();
    }
}


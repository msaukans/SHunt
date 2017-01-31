package com.codester.maris.shunt;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME ="game.db";//have to have .db in the end

    //public static final String TABLE_PRODUCTS = "products";//table name
    public static final String TABLE_PLAYERS = "players";
    public static final String TABLE_TASKS = "tasks";

    public static final String COLUMN_ID = "_id";
    //public static final String COLUMN_PRODUCTNAME = "productname";
    public static final String COLUMN_PLAYERFNAME = "playerFname";
    public static final String COLUMN_PLAYERLNAME = "playerLname";
    public static final String COLUMN_TASKNAME = "taskname";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,DATABASE_NAME,factory,DATABASE_VERSION);

    }//end MyDBHandler constructor

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_TASKS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASKNAME + " TEXT " +
                ");";
        db.execSQL(query);//execSQL = execute query
    }//end onCreate method

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_TASKS);
        onCreate(db);
    }//if i need to upgrade my version

    //add new row to the database
    public void addTask(Task task){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASKNAME, task.getTaskName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TASKS,null,values);
        db.close();
    }//end addTask method

    public void addPlayer(Player player){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLAYERFNAME, player.getPlayerFName());
        values.put(COLUMN_PLAYERLNAME, player.getPlayerLName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TASKS,null,values);
        db.close();
    }//end addPlayer method

    //delete from db
    public void deleteTask(String taskName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TASKS + " WHERE" + COLUMN_TASKNAME + " =\" " + taskName
                +"\";");
    }//end deleteTask method

    //print out db as a string
    public String databateToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TASKS + "WHERE 1";
        //cursor point to a location in my result

        Cursor c = db.rawQuery(query,null);
        //move to the first row in my result
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("taskname"))!=null){
                dbString += c.getString(c.getColumnIndex("taskname"));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }//end databaseToString method

}//end MyDBHandler class

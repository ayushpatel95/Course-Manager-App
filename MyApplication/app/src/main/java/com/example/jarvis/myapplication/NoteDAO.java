package com.example.jarvis.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NoteDAO {
    private SQLiteDatabase db;

    public NoteDAO(SQLiteDatabase db){
        this.db = db;
    }

    public long save(User note){
        ContentValues values
                = new ContentValues();
        values.put(NotesTable.COLUMN_first_name, note.getFirst_name());
        values.put(NotesTable.COLUMN_Last_name, note.getLast_name());
        values.put(NotesTable.COLUMN_Username, note.getUsername());
        values.put(NotesTable.COLUMN_Password, note.getPassword());
        values.put("image", note.getImage());
        return db.insert(NotesTable.TABLENAME, null, values);
    }

    public long saveinstructor(Instructor note){
        ContentValues values = new ContentValues();
        values.put("id", note.getId());
        values.put("user_id",note.getUser_id());
        values.put("firstname", note.getFirst_name());
        values.put("image", note.getProfile_image());
        values.put("lastname", note.getLast_name());
        values.put("Personal_Website", note.getPersonal_website());
        values.put("email", note.getEmail());
        return db.insert("Instructors", null, values);
    }

    public long saveCourse(Course note){
        ContentValues values = new ContentValues();
        values.put("instructor_id", note.getInstructor_id());
        values.put("user_id", note.getUser_id());
        values.put("title", note.getTitle());
        values.put("day", note.getDay());
        values.put("time", note.getTime());
        values.put("semester", note.getSemester());
        values.put("credit", note.getCredit());
        return db.insert("Course", null, values);
    }
    public boolean update(User note){
        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_first_name, note.getFirst_name());
        values.put(NotesTable.COLUMN_Last_name, note.getLast_name());
        values.put(NotesTable.COLUMN_Password, note.getPassword());
        values.put(NotesTable.COLUMN_Username, note.getUsername());
        return db.update(NotesTable.TABLENAME, values, NotesTable.COLUMN_ID + "=?", new String[]{String.valueOf(note.getUsername())}) > 0;
    }

    public boolean delete(Course note){
        return db.delete("Course", "rowid=?", new String[]{String.valueOf(note.getCourse_id())}) > 0;
    }

    public User get(long id){
        User note = null;
        Cursor c = db.query(true,NotesTable.TABLENAME, new String[]{"rowid", NotesTable.COLUMN_first_name, NotesTable.COLUMN_Last_name, NotesTable.COLUMN_Username,NotesTable.COLUMN_Password}, NotesTable.COLUMN_ID + "=?", new String[]{id +  ""},null,null,null,null,null);
        if(c != null && c.moveToFirst()){
            note = buildNoteFromCursorforuser(c);
            if(!c.isClosed()){
                c.close();
            }
        }
        return note;
    }
    public List<User> getAllNotes(){
        List<User> notes = new ArrayList<User>();
        Cursor c = db.query(NotesTable.TABLENAME, new String[]{ NotesTable.COLUMN_first_name, NotesTable.COLUMN_Last_name, NotesTable.COLUMN_Username,NotesTable.COLUMN_Password},null,null,null,null,null);
        if(c != null && c.moveToFirst()){
            do{
                User note = buildNoteFromCursorforuser(c);
                if(note != null){
                    notes.add(note);
                }
            }while (c.moveToNext());
            if(!c.isClosed()){
                c.close();
            }
        }
        return notes;
    }

    private User buildNoteFromCursorforuser(Cursor c){
        User note = null;
        if(c != null){
            note = new User();
            note.setFirst_name(c.getString(0));
            note.setLast_name(c.getString(1));
            note.setUsername(c.getString(2));
            note.setPassword(c.getString(3));
        }
        return note;
    }
    private Instructor buildNoteFromCursorforInstructor(Cursor c){
        Instructor note = null;
        if(c != null){
            note = new Instructor();
            note.setId(c.getLong(0));
            note.setFirst_name(c.getString(1));
            note.setLast_name(c.getString(2));
            note.setEmail(c.getString(3));
            note.setPersonal_website(c.getString(4));
            note.setProfile_image(c.getBlob(5));
            note.setUser_id(c.getInt(6));
        }
        return note;
    }


    public ArrayList<Instructor> getAllInstructors() {

        List<Instructor> notes = new ArrayList<>();
        Cursor c = db.query("Instructors", new String[]{"rowid","firstname","lastname","email","Personal_Website","image","user_id"},null,null,null,null,null);
        if(c != null && c.moveToFirst()){
            do{
                Instructor note = buildNoteFromCursorforInstructor(c);
                if(note != null){
                    notes.add(note);
                }
            }while (c.moveToNext());
            if(!c.isClosed()){
                c.close();
            }
        }
        return (ArrayList<Instructor>) notes;
    }

    public ArrayList<Course> getAllCourses() {

        List<Course> notes = new ArrayList<>();
        Cursor c = db.query("Course", new String[]{"rowid","instructor_id","user_id","title","day","time","semester","credit"},null,null,null,null,null);
        if(c != null && c.moveToFirst()){
            do{
                Course note = buildNoteFromCursorforCourse(c);
                if(note != null){
                    notes.add(note);
                }
            }while (c.moveToNext());
            if(!c.isClosed()){
                c.close();
            }
        }
        return (ArrayList<Course>) notes;
    }



    private Course buildNoteFromCursorforCourse(Cursor c){
        Course note = null;
        if(c != null){
            note = new Course();
            note.setCourse_id((int) c.getLong(0));
            note.setInstructor_id(c.getInt(1));
            note.setUser_id(c.getInt(2));
            note.setTitle(c.getString(3));
            note.setDay(c.getString(4));
            note.setTime(c.getString(5));
            note.setSemester(c.getString(6));
            note.setCredit(c.getInt(7));
        }
        return note;
    }

    public ArrayList<Course> getAllCoursesforUser(int user_id) {

        List<Course> notes = new ArrayList<>();
        Cursor c = db.query("Course", new String[]{"rowid","instructor_id","user_id","title","day","time","semester","credit"}, "user_id = ?", new String[]{String.valueOf(user_id)},null,null,null);
        if(c != null && c.moveToFirst()){
            do{
                Course note = buildNoteFromCursorforCourse(c);
                if(note != null){
                    notes.add(note);
                }
            }while (c.moveToNext());
            if(!c.isClosed()){
                c.close();
            }
        }
        return (ArrayList<Course>) notes;
    }

    public ArrayList<Instructor> getAllInstructorsforUser(int user_id) {

        List<Instructor> notes = new ArrayList<>();
        Cursor c = db.query("Instructors", new String[]{"rowid","firstname","lastname","email","Personal_Website","image","user_id"}, "user_id = ?", new String[]{String.valueOf(user_id)},null,null,null);
        if(c != null && c.moveToFirst()){
            do{
                Instructor note = buildNoteFromCursorforInstructor(c);
                if(note != null){
                    notes.add(note);
                }
            }while (c.moveToNext());
            if(!c.isClosed()){
                c.close();
            }
        }
        return (ArrayList<Instructor>) notes;
    }

    public ArrayList<Course> getCoursesforInstructor(int id) {

        List<Course> notes = new ArrayList<>();
        Cursor c = db.query("Course", new String[]{"rowid","instructor_id","user_id","title","day","time","semester","credit"}, "instructor_id = ?", new String[]{String.valueOf(id)},null,null,null);
        if(c != null && c.moveToFirst()){
            do{
                Course note = buildNoteFromCursorforCourse(c);
                if(note != null){
                    notes.add(note);
                }
            }while (c.moveToNext());
            if(!c.isClosed()){
                c.close();
            }
        }
        return (ArrayList<Course>) notes;

    }

    public boolean deleteinstructor(Instructor ins) {
        return db.delete("Instructors", "rowid=?", new String[]{String.valueOf(ins.getId())}) > 0;

    }
}

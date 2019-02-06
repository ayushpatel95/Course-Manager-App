package com.example.jarvis.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseDataManager {
    private Context mContext;
    private DatabaseOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private NoteDAO noteDAO;

    public DatabaseDataManager(Context mContext) {
        this.mContext = mContext;
        dbOpenHelper = new DatabaseOpenHelper(this.mContext);
        db = dbOpenHelper.getWritableDatabase();
        noteDAO = new NoteDAO(db);
    }

    public void close(){
        if(db != null){
            db.close();
        }
    }

    public NoteDAO getNoteDAO(){
        return this.noteDAO;
    }

    public long saveNOte(User note){
        return this.noteDAO.save(note);
    }

    public boolean updateNote(User note){
        return this.noteDAO.update(note);
    }

    public boolean deleteNote(Course note){
        return this.noteDAO.delete(note);
    }

    public User get(long id){
        return this.noteDAO.get(id);
    }

    public List<User> getAllNotes() {
        return this.noteDAO.getAllNotes();
    }

    public long saveInstructor(Instructor instructor) {
        return this.noteDAO.saveinstructor(instructor);
    }

    public long saveCourse(Course instructor) {
        return this.noteDAO.saveCourse(instructor);
    }

    public ArrayList<Instructor> getAllinstructors() {
        return this.noteDAO.getAllInstructors();
    }

    public ArrayList<Course> getAllCourses() { return this.noteDAO.getAllCourses();
    }
    public ArrayList<Course> getAllCoursesforUser(int user_id) { return this.noteDAO.getAllCoursesforUser(user_id);
    }

    public ArrayList<Instructor> getInstructorsforUser(long id) {
        return this.noteDAO.getAllInstructorsforUser((int) id);
    }

    public ArrayList<Course> getCoursesforInstructor(long id) {
        return  this.noteDAO.getCoursesforInstructor((int) id);
    }

    public boolean deleteInstructor(Instructor course) {
        return this.noteDAO.deleteinstructor(course);
    }
}

package com.example.jarvis.myapplication;

/**
 * Created by jarvis on 2/11/17.
 */

public class Instructor {
    String first_name, last_name, personal_website, email;
    long id;
    int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    byte[] profile_image;

    @Override
    public String toString() {
        return super.toString();
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPersonal_website() {
        return personal_website;
    }

    public void setPersonal_website(String personal_website) {
        this.personal_website = personal_website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(byte[] profile_image) {
        this.profile_image = profile_image;
    }
}

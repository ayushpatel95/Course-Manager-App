package com.example.jarvis.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements DisplayCourseFragment.OnFragmentInteractionListener,DisplayInstructorsFragment.OnFragmentInteractionListener,Loginfragment.OnFragmentInteractionListener, RegisterFragment.OnFragmentInteractionListener, CreateCourseFragment.OnFragmentInteractionListener , Coursefragment.OnFragmentInteractionListener, AddInstructorFragment.OnFragmentInteractionListener{

    android.app.FragmentManager fragmentManager = getFragmentManager();
    Fragment fragment1 = new Loginfragment();
    Fragment fragment2 = new RegisterFragment();
    Fragment fragment3 = new Coursefragment();
    Fragment fragment4 = new CreateCourseFragment();
    Fragment fragment5 = new AddInstructorFragment();
    Fragment fragment6 = new DisplayInstructorsFragment();
    DatabaseDataManager dm;
    byte[] byteArray;
    User user;
    Instructor instructor;
    Bitmap bitmap;
    static Course course;
    int image_set = 0, iimageset = 0;

    @Override
    public void onBackPressed() {
        }

    @Override
    public void clickinstructorimage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,2);

    }

    @Override
    public void saveinstructor(Instructor instructor) {
        if(iimageset==1){
            iimageset=0;
        }
        dm.saveInstructor(instructor);

        Toast.makeText(this, "Instructor created", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager.beginTransaction().add(R.id.container,fragment1,"first").commit();
        dm= new DatabaseDataManager(MainActivity.this);
    }


    @Override
    public void saveuser(User user) {
        if(image_set==1){
            image_set=0;
        }
        dm.saveNOte(user);
        this.user = user;
        Toast.makeText(this, "User created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveCourse(Course course) {
        dm.saveCourse(course);
    }

    @Override
    public void gotoInstructorfragment() {
        fragmentManager.beginTransaction().replace(R.id.container,fragment5,"third").commit();

    }

    @Override
    public byte[] getByteArrayforUser() {
        return this.byteArray;
    }

    @Override
    public void gotoCourseFragment() {
        fragmentManager.beginTransaction().replace(R.id.container,fragment3,"third").commit();

    }

    @Override
    public User getUserforAdding() {
        return this.user;
    }

    @Override
    public User getUserforCourses() {
        return this.user;
    }

    @Override
    public User getuserforinstructor() {
        return this.user;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public User getUserforDisplayingCourse() {
        return this.user;
    }

    @Override
    public User getUserforInstrcutors() {
        return this.user;
    }

    @Override
    public void clickimage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,1);
    }

    @Override
    public void opengallery() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 3);
   /*     Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image*//*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 3);
   */ }

    @Override
    public byte[] getByteArray() {
        return this.byteArray;
    }

    @Override
    public Bitmap getImage() {
        return this.bitmap;
    }

    @Override
    public void gotoRegisterFragment() {
        fragmentManager.beginTransaction().replace(R.id.container,fragment2,"second").commit();
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK)
        {
            image_set=0;
            byteArray = null;

            bitmap =(Bitmap) data.getExtras().get("data");
            ImageView imageView = (ImageView) fragment2.getView().findViewById(R.id.registercameraiv);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();

            imageView.setImageBitmap(bitmap);
            image_set = 1;
        }
        if(requestCode==2 && resultCode==RESULT_OK)
        {
            image_set=0;
            byteArray = null;
            bitmap =(Bitmap) data.getExtras().get("data");
            ImageView imageView = (ImageView) fragment5.getView().findViewById(R.id.instructorcameraiv);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();

            imageView.setImageBitmap(bitmap);
            iimageset = 1;

        }
        if(requestCode == 3 && resultCode == RESULT_OK &&data!=null){

            Uri selectedImage = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = this.getContentResolver().openInputStream(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            BufferedInputStream bufferedInputStream = new BufferedInputStream(imageStream);
            Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();

            ImageView imageView = (ImageView) fragment2.getView().findViewById(R.id.registercameraiv);
            imageView.setImageBitmap(bmp);

            /* Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();

                ImageView imageView = (ImageView) fragment2.getView().findViewById(R.id.registercameraiv);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
        if(requestCode == 4 && resultCode == RESULT_OK &&data!=null){

            Uri selectedImage = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = this.getContentResolver().openInputStream(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            BufferedInputStream bufferedInputStream = new BufferedInputStream(imageStream);
            Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();

            ImageView imageView = (ImageView) fragment5.getView().findViewById(R.id.instructorcameraiv);
            imageView.setImageBitmap(bmp);

            /* Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();

                ImageView imageView = (ImageView) fragment2.getView().findViewById(R.id.registercameraiv);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }

    }

    @Override
    public void opengalleryforInstructor() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 4);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                fragmentManager.beginTransaction().replace(R.id.container,fragment3,"third").commit();
                return true;
            case R.id.instructors:
                fragmentManager.beginTransaction().replace(R.id.container,fragment6,"sixth").commit();

                return true;
            case R.id.addinstructor:
                fragmentManager.beginTransaction().replace(R.id.container,fragment5,"fifth").commit();
                return true;
            case R.id.logout:
                fragmentManager.beginTransaction().replace(R.id.container,new Loginfragment(),"logged out").commit();
                return true;
            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void Courseclick(View view) {

    }

}

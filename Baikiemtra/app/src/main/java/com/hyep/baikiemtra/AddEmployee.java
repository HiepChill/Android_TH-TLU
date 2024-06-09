package com.hyep.baikiemtra;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hyep.baikiemtra.Helpers.EDatabseHelper;

import java.util.ArrayList;


public class AddEmployee extends AppCompatActivity {

    ImageView imgProfile;
    EditText edtEName, edtEEmail, edtEPos, edtEImage, edtEPhone, edtEDepID;
    ImageButton btnAddEmployee, btnBack;
    EDatabseHelper dbHelper;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 200;
    private static final int IMAGE_FROM_GALLERY_CODE = 300;
    private static final int IMAGE_FROM_CAMERA_CODE = 400;

    private String[] cameraPermission;
    private String[] storagePermission;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_employee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cameraPermission = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        imgProfile = findViewById(R.id.imgProfile);
        edtEName = findViewById(R.id.edtEName);
        edtEEmail = findViewById(R.id.edtEEmail);
        edtEPos = findViewById(R.id.edtEPos);
        edtEPhone = findViewById(R.id.edtEPhone);
        edtEDepID = findViewById(R.id.edtEDepartID);
        btnAddEmployee = findViewById(R.id.btnAddEmployee);
        btnBack = findViewById(R.id.btnBack);
        dbHelper = new EDatabseHelper(this);

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImagePickerDialog();
            }
        });

        btnAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String name = edtEName.getText().toString();
//                String email = edtEEmail.getText().toString();
//                String pos = edtEPos.getText().toString();
//                String img = edtEImage.getText().toString();
//                String phone = edtEPhone.getText().toString();
//                String depID = edtEDepID.getText().toString();
//
//
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                values.put(EDatabseHelper.COLUMN_NAME, name);
//                values.put(EDatabseHelper.COLUMN_EMAIL, email);
//                values.put(EDatabseHelper.COLUMN_POS, pos);
//                values.put(EDatabseHelper.COLUMN_IMAGE, img);
//                values.put(EDatabseHelper.COLUMN_PHONE, phone);
//                values.put(EDatabseHelper.COLUMN_ID_DEPARTMENT, depID);
//
//                db.insert(EDatabseHelper.TABLE_EMPLOYEES, null, values);
//                db.close();
//
//                Intent myIntent = new Intent(AddEmployee.this, MainActivity.class);
//                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(myIntent);
//                finish();

                saveData();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void showImagePickerDialog() {
        //option for dialog
        String options[] = {"Camera","Gallery"};

        // Alert dialog builder
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);

        //setTitle
        builder.setTitle("Choose An Option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //handle item click
                if (which == 0){ //start from 0 index
                    pickFromCamera();

                }else if (which == 1){
                    pickFromGallery();

                }
            }
        }).create().show();
    }

    private void pickFromGallery() {
        //intent for taking image from gallery
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*"); // only Image

        startActivityForResult(galleryIntent,IMAGE_FROM_GALLERY_CODE);
    }

    private void pickFromCamera() {

//       ContentValues for image info
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"IMAGE_TITLE");
        values.put(MediaStore.Images.Media.DESCRIPTION,"IMAGE_DETAIL");

        //save imageUri
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        //intent to open camera
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);

        startActivityForResult(cameraIntent,IMAGE_FROM_CAMERA_CODE);
    }

    private boolean checkCameraPermission(){
        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        return result & result1;
    }

    //request for camera permission
    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this,cameraPermission,CAMERA_PERMISSION_CODE); // handle request permission on override method
    }

    //check storage permission
    private boolean checkStoragePermission(){
        boolean result1 = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        return result1;
    }

    //request for camera permission
    private void requestStoragePermission(){
        ActivityCompat.requestPermissions(this,storagePermission,STORAGE_PERMISSION_CODE);
    }

    //handle request permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_PERMISSION_CODE:
                if (grantResults.length >0){

                    //if all permission allowed return true , otherwise false
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && storageAccepted){
                        //both permission granted
                        pickFromCamera();
                    }else {
                        //permission not granted
                        Toast.makeText(getApplicationContext(), "Camera & Storage Permission needed..", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case STORAGE_PERMISSION_CODE:
                if (grantResults.length >0){

                    //if all permission allowed return true , otherwise false
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (storageAccepted){
                        //permission granted
                        pickFromGallery();
                    }else {
                        //permission not granted
                        Toast.makeText(getApplicationContext(), "Storage Permission needed..", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_FROM_GALLERY_CODE && data != null) {
                // Lấy ảnh từ thư viện
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    // Hiển thị ảnh trong ImageView
                    imgProfile.setImageURI(selectedImageUri);
                    // Nếu cần, lưu URI vào biến để sử dụng sau này
                    imageUri = selectedImageUri;
                }
            } else if (requestCode == IMAGE_FROM_CAMERA_CODE) {
                // Lấy ảnh từ camera
                if (imageUri != null) {
                    // Hiển thị ảnh trong ImageView
                    imgProfile.setImageURI(imageUri);
                    // Lưu ý: URI của camera đã được đặt trước khi khởi tạo camera
                }
            }
        }
    }

    private void saveData() {
//        String name = edtEName.getText().toString();
//        String email = edtEEmail.getText().toString();
//        String pos = edtEPos.getText().toString();
//        String phone = edtEPhone.getText().toString();
//        String depID = edtEDepID.getText().toString();
//
//        if (!name.isEmpty() || !phone.isEmpty() || !email.isEmpty() || !pos.isEmpty() || !depID.isEmpty()) {
//            long id = dbHelper.insertEmployee(imageUri.toString(), name, phone, email, pos, depID);
//
//            Toast.makeText(getApplicationContext(), "Inserted "+id, Toast.LENGTH_SHORT).show();
//            Intent myIntent = new Intent(AddEmployee.this, MainActivity.class);
//            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(myIntent);
//            finish();
//        }
//        else {
//            Toast.makeText(getApplicationContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
//        }

        String name = edtEName.getText().toString();
        String email = edtEEmail.getText().toString();
        String pos = edtEPos.getText().toString();
        String img = imageUri.toString();
        String phone = edtEPhone.getText().toString();
        String depID = edtEDepID.getText().toString();


        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EDatabseHelper.COLUMN_NAME, name);
        values.put(EDatabseHelper.COLUMN_EMAIL, email);
        values.put(EDatabseHelper.COLUMN_POS, pos);
        values.put(EDatabseHelper.COLUMN_IMAGE, img);
        values.put(EDatabseHelper.COLUMN_PHONE, phone);
        values.put(EDatabseHelper.COLUMN_ID_DEPARTMENT, depID);

        db.insert(EDatabseHelper.TABLE_EMPLOYEES, null, values);
        db.close();

        Intent myIntent = new Intent(AddEmployee.this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(myIntent);
        finish();
    }
}
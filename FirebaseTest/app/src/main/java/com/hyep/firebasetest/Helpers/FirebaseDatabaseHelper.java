package com.hyep.firebasetest.Helpers;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hyep.firebasetest.Models.Student;

import java.util.HashMap;
import java.util.Objects;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceStudents;

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceStudents = mDatabase.getReference("students");
    }

    public void addStudent(Student student) {
        String id = mReferenceStudents.push().getKey();
        student.setId(id);
        mReferenceStudents.child(id).setValue(student);

    }

    public void updateStudent(String id, Student student) {
        mReferenceStudents.child(id).setValue(student);
    }

    public void deleteStudent(String id) {
        mReferenceStudents.child(id).removeValue();
    }

    public DatabaseReference getReference() {
        return mReferenceStudents;
    }

}

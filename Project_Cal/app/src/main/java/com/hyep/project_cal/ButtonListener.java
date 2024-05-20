package com.hyep.project_cal;

import android.view.View;
import android.widget.Toast;

public class ButtonListener  implements View.OnClickListener{
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.buttonCheck) {
            Toast.makeText(view.getContext(), "Test", Toast.LENGTH_SHORT).show();
        }
    }
}

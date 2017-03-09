package com.stealthcopter.colourmatrixexample;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.stealthcopter.colourmatrixexample.ColorMatrixHelpers.ColorMatrixAndSaturation;
import com.stealthcopter.colourmatrixexample.ColorMatrixHelpers.ColorMatrixPresets;

public class MainActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener {

    private EditText editTextR1;
    private EditText editTextR2;
    private EditText editTextR3;
    private EditText editTextR4;
    private EditText editTextG1;
    private EditText editTextG2;
    private EditText editTextG3;
    private EditText editTextG4;
    private EditText editTextB1;
    private EditText editTextB2;
    private EditText editTextB3;
    private EditText editTextB4;
    private EditText editTextA1;
    private EditText editTextA2;
    private EditText editTextA3;
    private EditText editTextA4;
    private EditText editTextW1;
    private EditText editTextW2;
    private EditText editTextW3;
    private EditText editTextW4;
    private EditText editTextSaturation;

    private ImageView imageView;
    private CheckBox saturationCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        editTextR1 = (EditText) findViewById(R.id.editTextR1);
        editTextR2 = (EditText) findViewById(R.id.editTextR2);
        editTextR3 = (EditText) findViewById(R.id.editTextR3);
        editTextR4 = (EditText) findViewById(R.id.editTextR4);
        editTextG1 = (EditText) findViewById(R.id.editTextG1);
        editTextG2 = (EditText) findViewById(R.id.editTextG2);
        editTextG3 = (EditText) findViewById(R.id.editTextG3);
        editTextG4 = (EditText) findViewById(R.id.editTextG4);
        editTextB1 = (EditText) findViewById(R.id.editTextB1);
        editTextB2 = (EditText) findViewById(R.id.editTextB2);
        editTextB3 = (EditText) findViewById(R.id.editTextB3);
        editTextB4 = (EditText) findViewById(R.id.editTextB4);
        editTextA1 = (EditText) findViewById(R.id.editTextA1);
        editTextA2 = (EditText) findViewById(R.id.editTextA2);
        editTextA3 = (EditText) findViewById(R.id.editTextA3);
        editTextA4 = (EditText) findViewById(R.id.editTextA4);
        editTextW1 = (EditText) findViewById(R.id.editTextW1);
        editTextW2 = (EditText) findViewById(R.id.editTextW2);
        editTextW3 = (EditText) findViewById(R.id.editTextW3);
        editTextW4 = (EditText) findViewById(R.id.editTextW4);
        editTextSaturation = (EditText) findViewById(R.id.editTextSaturation);
        saturationCheckbox = (CheckBox) findViewById(R.id.saturationCheckbox);

        editTextR1.addTextChangedListener(this);
        editTextR2.addTextChangedListener(this);
        editTextR3.addTextChangedListener(this);
        editTextR4.addTextChangedListener(this);
        editTextG1.addTextChangedListener(this);
        editTextG2.addTextChangedListener(this);
        editTextG3.addTextChangedListener(this);
        editTextG4.addTextChangedListener(this);
        editTextB1.addTextChangedListener(this);
        editTextB2.addTextChangedListener(this);
        editTextB3.addTextChangedListener(this);
        editTextB4.addTextChangedListener(this);
        editTextA1.addTextChangedListener(this);
        editTextA2.addTextChangedListener(this);
        editTextA3.addTextChangedListener(this);
        editTextA4.addTextChangedListener(this);
        editTextW1.addTextChangedListener(this);
        editTextW2.addTextChangedListener(this);
        editTextW3.addTextChangedListener(this);
        editTextW4.addTextChangedListener(this);
        editTextSaturation.addTextChangedListener(this);

        findViewById(R.id.presetButton).setOnClickListener(this);
        findViewById(R.id.getCodeButton).setOnClickListener(this);

        saturationCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editTextSaturation.setVisibility(isChecked ? View.VISIBLE : View.GONE);

                setColourFilter();
            }
        });

        setColourFilter();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        setColourFilter();
    }

    @Nullable
    private float[] getColourMatrix() {
        try {
            return new float[]{
                    Float.valueOf(editTextR1.getText().toString()), Float.valueOf(editTextG1.getText().toString()), Float.valueOf(editTextB1.getText().toString()), Float.valueOf(editTextA1.getText().toString()), Float.valueOf(editTextW1.getText().toString()),
                    Float.valueOf(editTextR2.getText().toString()), Float.valueOf(editTextG2.getText().toString()), Float.valueOf(editTextB2.getText().toString()), Float.valueOf(editTextA2.getText().toString()), Float.valueOf(editTextW2.getText().toString()),
                    Float.valueOf(editTextR3.getText().toString()), Float.valueOf(editTextG3.getText().toString()), Float.valueOf(editTextB3.getText().toString()), Float.valueOf(editTextA3.getText().toString()), Float.valueOf(editTextW3.getText().toString()),
                    Float.valueOf(editTextR4.getText().toString()), Float.valueOf(editTextG4.getText().toString()), Float.valueOf(editTextB4.getText().toString()), Float.valueOf(editTextA4.getText().toString()), Float.valueOf(editTextW4.getText().toString())
            };
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setColorMatrix(ColorMatrixAndSaturation colorMatrixAndSaturation) {

        float[] colorMatrix = colorMatrixAndSaturation.getColorMatrix();

        if (colorMatrix.length != 20) {
            throw new IllegalArgumentException("Error colorMatrix must be of length 20");
        }

        int i = 0;

        editTextR1.setText(String.valueOf(colorMatrix[i++]));
        editTextG1.setText(String.valueOf(colorMatrix[i++]));
        editTextB1.setText(String.valueOf(colorMatrix[i++]));
        editTextA1.setText(String.valueOf(colorMatrix[i++]));
        editTextW1.setText(String.valueOf(colorMatrix[i++]));
        editTextR2.setText(String.valueOf(colorMatrix[i++]));
        editTextG2.setText(String.valueOf(colorMatrix[i++]));
        editTextB2.setText(String.valueOf(colorMatrix[i++]));
        editTextA2.setText(String.valueOf(colorMatrix[i++]));
        editTextW2.setText(String.valueOf(colorMatrix[i++]));
        editTextR3.setText(String.valueOf(colorMatrix[i++]));
        editTextG3.setText(String.valueOf(colorMatrix[i++]));
        editTextB3.setText(String.valueOf(colorMatrix[i++]));
        editTextA3.setText(String.valueOf(colorMatrix[i++]));
        editTextW3.setText(String.valueOf(colorMatrix[i++]));
        editTextR4.setText(String.valueOf(colorMatrix[i++]));
        editTextG4.setText(String.valueOf(colorMatrix[i++]));
        editTextB4.setText(String.valueOf(colorMatrix[i++]));
        editTextA4.setText(String.valueOf(colorMatrix[i++]));
        editTextW4.setText(String.valueOf(colorMatrix[i]));

        if (colorMatrixAndSaturation.getSaturation() == null) {
            saturationCheckbox.setChecked(false);
        } else {
            editTextSaturation.setText(String.valueOf(colorMatrixAndSaturation.getSaturation()));
            saturationCheckbox.setChecked(true);
        }
    }

    private void setColourFilter() {

        float[] colorMatrix = getColourMatrix();

        if (colorMatrix == null) {
            return;
        }

        ColorMatrix matrix = new ColorMatrix();
        matrix.set(colorMatrix);

        if (saturationCheckbox.isChecked()) {

            float sat = 0.5f;

            try {
                sat = Float.valueOf(editTextSaturation.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            // Only set saturation if checkbox is ticked
            matrix.setSaturation(sat);
        }

        ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
        imageView.setColorFilter(cf);
    }

    private void showGetCodeDialog() {

        float[] colorMatrix = getColourMatrix();

        if (colorMatrix == null) {
            Toast.makeText(this, "Error creating colour matrix", Toast.LENGTH_SHORT).show();
            return;
        }

        int saturation = 1;

        try {
            Integer.parseInt(editTextSaturation.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, "Invalid saturation value", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Get Code");

        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_code, null);

        int i = 0;
        final TextView codeText = (TextView) view.findViewById(R.id.codeText);
        codeText.setTextIsSelectable(true);

        StringBuilder sb = new StringBuilder();

        sb.append("\nColorMatrix matrix = new ColorMatrix();\n" + "\n" + "matrix.set(new float[] {\n" + "        ").append(colorMatrix[i++]).append(",  ").append(colorMatrix[i++]).append(",  ").append(colorMatrix[i++]).append(",  ").append(colorMatrix[i++]).append(",  ").append(colorMatrix[i++]).append(",\n").append("        ").append(colorMatrix[i++]).append(",  ").append(colorMatrix[i++]).append(",  ").append(colorMatrix[i++]).append(",  ").append(colorMatrix[i++]).append(",  ").append(colorMatrix[i++]).append(",\n").append("        ").append(colorMatrix[i++]).append(",  ").append(colorMatrix[i++]).append(",  ").append(colorMatrix[i++]).append(",  ").append(colorMatrix[i++]).append(",  ").append(colorMatrix[i++]).append(",\n").append("        ").append(colorMatrix[i++]).append(",  ").append(colorMatrix[i++]).append(",  ").append(colorMatrix[i++]).append(",  ").append(colorMatrix[i++]).append(",  ").append(colorMatrix[i]).append("\n").append("});\n").append("\n");

        if (saturationCheckbox.isChecked()) {
            sb.append("\nmatrix.setSaturation(").append(saturation).append(");\n\n");
        }

        sb.append("\nColorMatrixColorFilter cf \n    = new ColorMatrixColorFilter(matrix);\n\nimageView.setColorFilter(cf);");

        codeText.setText(sb.toString());

        builder.setView(view);

        builder.setPositiveButton("Copy to clipboard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ClipboardManager clipboard = (ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);

                ClipData clip = ClipData.newPlainText("simple text", codeText.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showPresetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Preset");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);

        for (String presetName : ColorMatrixPresets.getPresetNames()) {
            arrayAdapter.add(presetName);
        }

        builder.setNegativeButton("cancel", null);
        builder.setCancelable(true);
        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                setColorMatrix(ColorMatrixPresets.getPresetColorMatrix(strName));
            }
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_github) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(getString(R.string.github_url)));
            startActivity(i);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.presetButton:
                showPresetDialog();
                break;
            case R.id.getCodeButton:
                showGetCodeDialog();
                break;
        }
    }
}

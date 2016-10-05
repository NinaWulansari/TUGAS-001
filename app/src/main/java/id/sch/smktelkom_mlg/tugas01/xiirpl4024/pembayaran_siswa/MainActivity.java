package id.sch.smktelkom_mlg.tugas01.xiirpl4024.pembayaran_siswa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText etNomor;
    //    RadioButton rbR, rbT;
    RadioGroup rgJurusan;
    CheckBox cbS, cbD, cbL;
    Button bOk;
    TextView tvHasil2, tvHasil3, tvHasil4, tvHasil5, tvJenis;
    int nJenis;
    Spinner spBulan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNomor = (EditText) findViewById(R.id.editTextNomor);
        /*rbR = (RadioButton) findViewById(R.id.radioButtonR);
        rbT = (RadioButton) findViewById(R.id.radioButtonT);*/
        rgJurusan = (RadioGroup) findViewById(R.id.radioGroupStatus);
        cbS = (CheckBox) findViewById(R.id.checkBoxS);
        cbD = (CheckBox) findViewById(R.id.checkBoxD);
        cbL = (CheckBox) findViewById(R.id.checkBoxL);
        cbS.setOnCheckedChangeListener(this);
        cbD.setOnCheckedChangeListener(this);
        cbL.setOnCheckedChangeListener(this);
        spBulan = (Spinner) findViewById(R.id.spinnerBulan);
        bOk = (Button) findViewById(R.id.buttonSimpan);
        tvHasil2 = (TextView) findViewById(R.id.textViewHasil2);
        tvHasil3 = (TextView) findViewById(R.id.textViewHasil3);
        tvHasil4 = (TextView) findViewById(R.id.textViewHasil4);
        tvHasil5 = (TextView) findViewById(R.id.textViewHasil5);
        tvJenis = (TextView) findViewById(R.id.textViewJenis);

        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doProsess();
            }
        });
    }

    private void doProsess() {
        if (isValid()) {
            int nomor = Integer.parseInt(etNomor.getText().toString());
            tvHasil5.setText("Virtual Account : " + nomor);

            String hasil = null;

            if (rgJurusan.getCheckedRadioButtonId() != -1) {
                RadioButton rb = (RadioButton)
                        findViewById(rgJurusan.getCheckedRadioButtonId());
                hasil = rb.getText().toString();
            }
            /*if(rbR.isChecked())
            {
                hasil = rbR.getText().toString();
            }
            else if (rbT.isChecked())
            {
                hasil = rbT.getText().toString();
            }*/

            if (hasil == null) {
                tvHasil2.setText("Belum memilih Jurusan");
            } else {
                tvHasil2.setText("Jurusan : " + hasil);
            }

            String jenis = "Jenis Pembayaran : ";
            int startlen = jenis.length();
            if (cbS.isChecked()) jenis += cbS.getText() + "\n";
            if (cbD.isChecked()) jenis += cbD.getText() + "\n";
            if (cbL.isChecked()) jenis += cbL.getText() + "\n";

            if (jenis.length() == startlen) jenis += "Tidak ada pada Pilihan";

            tvHasil3.setText(jenis);
            tvHasil4.setText("Bayar Bulan : " + spBulan.getSelectedItem().toString());
        }


    }

    private boolean isValid() {
        boolean valid = true;

        String nomor = etNomor.getText().toString();

        if (nomor.isEmpty()) {
            etNomor.setError("Virtual Account belum diisi");
            valid = false;
        } else if (nomor.length() < 6) {
            etNomor.setError("Penulisan Tidak Valid!(Min. 6 Karakter))");
            valid = false;
        } else {
            etNomor.setError(null);
        }

        return valid;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) nJenis += 1;
        else nJenis -= 1;

        tvJenis.setText("Jenis Pembayaran(" + "\n" + nJenis + " terpilih)");
    }
}

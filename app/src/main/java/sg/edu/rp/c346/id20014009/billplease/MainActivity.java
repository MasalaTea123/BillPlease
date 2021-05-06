package sg.edu.rp.c346.id20014009.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText amount;
    EditText numpax;
    EditText discount;
    ToggleButton svs;
    ToggleButton gst;
    Button split;
    Button reset;
    TextView totalbill;
    TextView eachpay;
    RadioGroup rgMode;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        amount=findViewById(R.id.editInputAmount);
        numpax=findViewById(R.id.editeditInputNumPax);
        totalbill=findViewById(R.id.totalbill);
        eachpay=findViewById(R.id.eachPay);
        svs=findViewById(R.id.tbsSvs);
        gst=findViewById(R.id.tbsGst);
        split=findViewById(R.id.split);
        reset=findViewById(R.id.reset);
        discount=findViewById(R.id.editDiscount);
        rgMode=findViewById(R.id.rg);


        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText().toString().trim().length()!=0 && numpax.getText().toString().trim().length()!=0) {
                    double newAmt = 0.0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString());
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.1;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.07;
                    } else {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.17;
                    }
//Discount
                    if (discount.getText().toString().trim().length() != 0) {
                        newAmt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }
                    totalbill.setText("Total Bill: $" + String.format("%.2f", newAmt));
                    int numPerson = Integer.parseInt(numpax.getText().toString());
                    if (numPerson != 1)
                        eachpay.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson));
                    else
                        eachpay.setText("Each Pays: $" + newAmt);
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                numpax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
            }
        });




    }
}
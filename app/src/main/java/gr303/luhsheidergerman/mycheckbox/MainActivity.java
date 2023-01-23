package gr303.luhsheidergerman.mycheckbox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class MainActivity extends AppCompatActivity {

    CheckBox[] checkBoxes = new CheckBox[4];

    EditText[] amounts = new EditText[4];
    EditText[] prices = new EditText[4];

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxes[0] = findViewById(R.id.checkBox_Apple);
        checkBoxes[1] = findViewById(R.id.checkBox_Strawberry);
        checkBoxes[2] = findViewById(R.id.checkBox_Blueberry);
        checkBoxes[3] = findViewById(R.id.checkBox_Potatoes);

        amounts[0] = findViewById(R.id.editText_AppleAmount);
        amounts[1] = findViewById(R.id.editText_StrawberryAmount);
        amounts[2] = findViewById(R.id.editText_BlueberryAmount);
        amounts[3] = findViewById(R.id.editText_PotatoesAmount);

        prices[0] = findViewById(R.id.editText_ApplePrice);
        prices[1] = findViewById(R.id.editText_StrawberryPrice);
        prices[2] = findViewById(R.id.editText_BlueberryPrice);
        prices[3] = findViewById(R.id.editText_PotatoesPrice);

        prices[0].setText("10.25");
        prices[1].setText("20.10");
        prices[2].setText("5.99");
        prices[3].setText("17.50");
    }

    @SuppressLint("DefaultLocale")
    public void button_Calculate_OnClick(View view) {
        String output = "";
        double sum = 0;
        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isChecked()) {
                String amountTxt = amounts[i].getText().toString();
                String priceTxt = prices[i].getText().toString();

                if (amountTxt.isEmpty() || amountTxt.contains(".")) {
                    Toast.makeText(this, "Error in amounts!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (priceTxt.isEmpty() || priceTxt.equals(".")) {
                    Toast.makeText(this, "Error in prices!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int amount = Integer.parseInt(amountTxt);
                double price = Double.parseDouble(priceTxt);
                double result = amount*price;

                sum += result;
                output += String.format("%d: %d x %s = %d x %.2f = %.2f\n",
                        i+1, amount, checkBoxes[i].getText().toString(), amount, price, result);
            }
        }
        output += String.format("\ntotal - %.2f", sum);

        if (((RadioButton)findViewById(R.id.radioButton_ToastOutput)).isChecked()) {
            Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();
        dialog.setIcon(R.drawable.fruits);
        dialog.setTitle("Results");
        dialog.setMessage(output);
        dialog.show();
    }
}
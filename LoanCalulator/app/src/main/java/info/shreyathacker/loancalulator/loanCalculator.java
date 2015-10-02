package info.shreyathacker.loancalulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.text.Editable; //Interface allowing you to change the content and markup of text in a GUI.
import android.text.TextWatcher; //respond to events when the user interacts with an EditText component
import android.widget.EditText; //widget and layout for EditText component

import android.widget.TextView; //widget and layout for TextView component




public class loanCalculator extends AppCompatActivity {

    //constants used when saving/restoring state
    private static final String BILL_TOTAL = "BILL_TOTAL";
    private static final String INTEREST_TOTAL = "INTEREST_TOTAL";

    private double currentBillTotal; //bill/loan amount entered by the user
    private double currentInterestTotal; //interest amount entered by the user
    private EditText total10EditText; //displays the total with 10% tip
    private EditText total15EditText; //displays the total with 15% tip
    private EditText total20EditText; //displays the total with 20% tip
    private EditText billEditText; //accepts user input for bill total
    private EditText interestEditText;// accepts user input for interest total


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_calculator);

        //check if app just started or being restored from memory
        if (savedInstanceState == null)//the app just started
        {
            //initialize some fields
            currentBillTotal = 0.0;
            currentInterestTotal = 0.0;
        } else //being restored from memory
        {
            //initialize fields from saved values
            currentBillTotal = savedInstanceState.getDouble(BILL_TOTAL);
            currentInterestTotal = savedInstanceState.getDouble(INTEREST_TOTAL);
        }

        //get references to all the items on the gui

        total10EditText = (EditText) findViewById(R.id.total10EditText);
        total15EditText = (EditText) findViewById(R.id.total15EditText);
        total20EditText = (EditText) findViewById(R.id.total20EditText);
        billEditText = (EditText) findViewById(R.id.billEditText);
        interestEditText = (EditText) findViewById(R.id.interestEditText);
        //handle when the bill total changes
        billEditText.addTextChangedListener(billEditTextWatcher);
        interestEditText.addTextChangedListener(interestEditTextWatcher);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_loan_calculator, menu);
        return true;
    }



        //update the 10,15 and 20% editTexts
        private void updateStandard() {
            //calculare total
           // double tenPercentTip = currentBillTotal / 2;
           // double tenPercentTotal = currentBillTotal + tenPercentTip;

            double rateOfInterest = currentInterestTotal / 1200;
            double rate1 = currentBillTotal * rateOfInterest;
            double rate2 = (1 + rateOfInterest);
            double rate3 = Math.pow(rate2,120);
            double rate4 = rate1 * rate3;

            double rate5= rate3 - 1;
            double rate6= rate4 / rate5;

            double repayment = rate6 * 120;





            //set the corresponding editText value

            total10EditText.setText(String.format("%.02f",rate6));

            double tPercentTip = currentInterestTotal / 2;
            double tPercentTotal = currentInterestTotal + tPercentTip;
            //set the corresponding editText value

            total15EditText.setText(String.format("%.02f", repayment));



       }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(BILL_TOTAL, currentBillTotal);
        outState.putDouble(INTEREST_TOTAL, currentInterestTotal);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

    return super.onOptionsItemSelected(item);
    }


    private TextWatcher billEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try

            {
                currentBillTotal = Double.parseDouble(s.toString());
            } catch (NumberFormatException e)

            {
                currentBillTotal = 0.0;

            }
            updateStandard();

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher interestEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try

            {
                currentInterestTotal = Double.parseDouble(s.toString());
            } catch (NumberFormatException e)

            {
                currentInterestTotal = 0.0;

            }
            updateStandard();

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}

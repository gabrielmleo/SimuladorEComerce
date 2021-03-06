package com.example.gabrielmoraes.simuladorecomerce;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabrielmoraes.simuladorecomerce.domain.Product;
import com.example.gabrielmoraes.simuladorecomerce.mvp.MVP;
import com.example.gabrielmoraes.simuladorecomerce.mvp.PaymentPresenter;
import com.example.gabrielmoraes.simuladorecomerce.util.Util;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity implements MVP.PaymentViewOp{

    private Toolbar mToolBar;
    private MVP.PaymentPresenterOp mPresenter;
    private EditText creditCardNumber;
    private EditText creditCardOwner;
    private EditText creditCardMonthExpire;
    private EditText creditCardYearExpire;
    private EditText creditCardCvv;
    private Button paymentButton;
    private ProgressBar mProgressCircular;
    private TextView amountValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mToolBar = (Toolbar)findViewById(R.id.tb_payment);
        mProgressCircular = (ProgressBar)findViewById(R.id.progress_circular);
        this.mToolBar.setTitle(getResources().getString(R.string.payment_activity_name));
        setSupportActionBar(this.mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (mPresenter == null){
            mPresenter = new PaymentPresenter();
        }

        mPresenter.setView(this);

        if (getIntent() != null){
            ArrayList<Product> l = getIntent().getParcelableArrayListExtra(MVP.CartPresenterOp.BUNDLE_PAYMENT_KEY);
            mPresenter.setCartProducts(l);
        }

        setComponents();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void setComponents(){
        creditCardNumber = (EditText)findViewById(R.id.credit_card_number);
        creditCardOwner = (EditText)findViewById(R.id.credit_card_owner);
        creditCardMonthExpire = (EditText)findViewById(R.id.credit_card_month);
        creditCardYearExpire = (EditText)findViewById(R.id.credit_card_year);
        creditCardCvv = (EditText)findViewById(R.id.credit_card_cvv);
        paymentButton = (Button) findViewById(R.id.pay_button);
        amountValue = (TextView) findViewById(R.id.amount_value);
        amountValue.setText(Util.currencyFormater(mPresenter.getAmountValue()));
    }

    public void confirmPayment(View v){
        mPresenter.confirmPayment();
    }

    @Override
    public String getCreditCardOwnerName() {
        return creditCardOwner.getText().toString();
    }

    @Override
    public String getCreditCardNumber() {
        return creditCardNumber.getText().toString();
    }

    @Override
    public String getCreditCardCvv() {
        return creditCardCvv.getText().toString();
    }

    @Override
    public String getCreditCardMonth() {
        return creditCardMonthExpire.getText().toString();
    }

    @Override
    public String getCreditCardYear() {
        return creditCardYearExpire.getText().toString();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void paymentSuccess() {
        setResult(MVP.PaymentPresenterOp.PAYMENT_SUCCESS_RESULT_CODE);
        finish();
    }

    @Override
    public void paymentFailure() {
        Toast.makeText(this,getResources().getString(R.string.payment_failure),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar(int visibility) {
        mProgressCircular.setVisibility(visibility);
    }

    @Override
    public String getFieldsErrorMessage() {
        return getResources().getString(R.string.fields_incomplete);
    }
}


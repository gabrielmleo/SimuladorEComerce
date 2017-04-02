package com.example.gabrielmoraes.simuladorecomerce;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabrielmoraes.simuladorecomerce.domain.Product;
import com.example.gabrielmoraes.simuladorecomerce.mvp.MVP;
import com.example.gabrielmoraes.simuladorecomerce.mvp.PaymentPresenter;

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
    private TextView amountValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mToolBar = (Toolbar)findViewById(R.id.tb_payment);
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

    public void setComponents(){
        creditCardNumber = (EditText)findViewById(R.id.credit_card_number);
        creditCardOwner = (EditText)findViewById(R.id.credit_card_owner);
        creditCardMonthExpire = (EditText)findViewById(R.id.credit_card_month);
        creditCardYearExpire = (EditText)findViewById(R.id.credit_card_year);
        creditCardCvv = (EditText)findViewById(R.id.credit_card_cvv);
        paymentButton = (Button) findViewById(R.id.pay_button);
        amountValue = (TextView) findViewById(R.id.amount_value);
        amountValue.setText(mPresenter.getAmountValue());
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
}


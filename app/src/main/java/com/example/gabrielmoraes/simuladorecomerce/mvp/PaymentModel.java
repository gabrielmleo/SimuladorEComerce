package com.example.gabrielmoraes.simuladorecomerce.mvp;

import com.example.gabrielmoraes.simuladorecomerce.domain.PaymentData;
import com.example.gabrielmoraes.simuladorecomerce.domain.PaymentTransaction;
import com.example.gabrielmoraes.simuladorecomerce.network.ProductPaymentService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gabri on 02/04/2017.
 */

public class PaymentModel implements MVP.PaymentModelOp {

    private PaymentData mPaymentData;
    private MVP.PaymentPresenterOp mPresenterOp;
    private Retrofit retrofit;
    private String creditCardOwner;
    private String creditCardNumber;
    private String creditCardCvv;
    private String creditCardMonth;
    private String creditCardYear;

    public PaymentModel(MVP.PaymentPresenterOp presenterOp){
        mPresenterOp = presenterOp;
        mPaymentData = new PaymentData();
        retrofitConfiguration();
    }

    private void createNewTransaction(){

        DateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateFormated = dFormat.format(date);

        PaymentTransaction transaction = new PaymentTransaction(mPresenterOp.getAmountValue(),creditCardNumber.substring(12,15),dateFormated,creditCardOwner);
        transaction.save();

    }

    public void fillPaymentData(){
        mPaymentData.setCard_holder_name(creditCardOwner);
        mPaymentData.setCard_number(creditCardNumber);

        String expireDateConcat = creditCardMonth + creditCardYear.substring(2);
        mPaymentData.setExp_date(expireDateConcat);
        mPaymentData.setCvv(creditCardCvv);
        mPaymentData.setValue(mPresenterOp.getAmountValue());
    }

    @Override
    public void requestPayment() {

        fillPaymentData();

        ProductPaymentService service = this.retrofit.create(ProductPaymentService.class);
        Call<PaymentData> requestRepositoryList = service.newPayment(mPaymentData);

        requestRepositoryList.enqueue(new Callback<PaymentData>() {
            @Override
            public void onResponse(Call<PaymentData> call, Response<PaymentData> response) {
                if (!response.isSuccessful()){
                    //mPresenterOp.updateProductsList(response.body());
                }
                else{
                    response.code();
                        createNewTransaction();
                        mPresenterOp.showToast("pagamento efetuado com sucesso");

                }
            }

            @Override
            public void onFailure(Call<PaymentData> call, Throwable t) {

                //Log.i("AIAIAIAI0","AIAIAIAIAOOOOOPPP");
            }
        });

    }

    @Override
    public void retrofitConfiguration() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ProductPaymentService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void setCreditCardOwnerName(String name) {
        creditCardOwner = name;
    }

    @Override
    public void setCreditCardNumber(String number) {
        creditCardNumber = number;
    }

    @Override
    public void setCreditCardCvv(String cvv) {
        creditCardCvv = cvv;
    }

    @Override
    public void setCreditCardMonth(String month) {
        creditCardMonth = month;
    }

    @Override
    public void setCreditCardYear(String year) {
        creditCardYear = year;
    }
}

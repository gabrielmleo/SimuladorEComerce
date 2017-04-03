package com.example.gabrielmoraes.simuladorecomerce.mvp;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.gabrielmoraes.simuladorecomerce.domain.PaymentTransaction;
import com.example.gabrielmoraes.simuladorecomerce.domain.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabri on 28/03/2017.
 */

public interface MVP {

    public interface ViewOp{
        public void updateProductsList();
        public void showCartActivity(Bundle p);
        public void showHistoryActivity();
        public boolean callSuperOnOptionItemSelected(MenuItem item);
        public void addProductToCart(Product p);
    }

    public interface PresenterOp{
        String BUNDLE_KEY = "CART_PRODUCTS";
        public ArrayList<Product> getProducts();
        public void retrieveProducts();
        public void updateProductsList(ArrayList<Product> mList);
        public boolean onOptionsItemSelected(MenuItem item);
        public void addProductToCart(Product p);

    }

    public interface ModelOp{
        public void retrieveProducts();
        public void retrofitConfiguration();
    }

    public interface CartViewOp{
        public void showPaymentActivity(Bundle b);
        public boolean callSuperOnOptionItemSelected(MenuItem item);
        public void setEmptyListVisibility(int i);
        public void updateMenuItem();

    }

    public interface CartPresenterOp{
        String BUNDLE_PAYMENT_KEY = "PAYMENT_KEY";
        public void setView(CartViewOp view);
        public void setCartProducts(ArrayList<Product> list);
        public ArrayList<Product> getCartProductsList();
        public boolean onOptionsItemSelected(MenuItem item);
        public void checkEmptyList(ArrayList<Product> l);
        public boolean mustShowMenuItem();
    }

    public interface PaymentPresenterOp{
        public void setView(PaymentViewOp view);
        public void setCartProducts(ArrayList<Product> list);
        public String getAmountValue();
        public void confirmPayment();
        public void showToast(String message);
    }

    public interface PaymentModelOp{
        public void requestPayment();
        public void retrofitConfiguration();
        public void setCreditCardOwnerName(String name);
        public void setCreditCardNumber(String number);
        public void setCreditCardCvv(String cvv);
        public void setCreditCardMonth(String month);
        public void setCreditCardYear(String year);

    }

    public interface PaymentViewOp{
        public String getCreditCardOwnerName();
        public String getCreditCardNumber();
        public String getCreditCardCvv();
        public String getCreditCardMonth();
        public String getCreditCardYear();
        public void showToast(String message);

    }

    public interface TransactionViewOp{

    }

    public interface TransactionPresenterOp{
        public List<PaymentTransaction> getTransactionList();
        public void setView(MVP.TransactionViewOp viewOp);
    }

    public interface TransactionModelOp{
        public List<PaymentTransaction> getTransactionList();
    }
}

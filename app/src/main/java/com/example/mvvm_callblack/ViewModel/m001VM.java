package com.example.mvvm_callblack.ViewModel;

import androidx.lifecycle.ViewModel;

public class m001VM  extends ViewModel {
    private Thread th ;
    private CountDownCallBack callBack ;

    public void setCallBack(CountDownCallBack callBack) {
        this.callBack = callBack;
    }

    public void startCountDown() {
        if(th == null || !th.isAlive())
        {
            th = new Thread(new Runnable() {
                @Override
                public void run() {
                        for(int i=10;i>=0;i--)
                        {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            callBack.upDateUI(i);
                        }
                }
            });
            th.start();
        }
    }

    public interface CountDownCallBack
    {
        void upDateUI(int i);
    }
}

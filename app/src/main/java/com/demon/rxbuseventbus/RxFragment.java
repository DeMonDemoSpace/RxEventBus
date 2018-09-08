package com.demon.rxbuseventbus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author DeMon
 * @date 2018/9/7
 * @description
 */
public class RxFragment extends Fragment {
    @BindView(R.id.text)
    TextView text;
    Unbinder unbinder;
    private View view;
    //private CompositeDisposable compositeDisposable;

    @SuppressLint("CheckResult")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rx, container, false);
        unbinder = ButterKnife.bind(this, view);
        /*RxBus.getInstance().toObservable(MsgEvent.class).subscribe(new Observer<MsgEvent>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(MsgEvent msgEvent) {
                text.setText(msgEvent.getMsg());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/
        RxBus.getInstance().toObservable(this, MsgEvent.class).subscribe(new Consumer<MsgEvent>() {
            @Override
            public void accept(MsgEvent msgEvent) throws Exception {
                //处理事件
                text.setText(msgEvent.getMsg());
            }
        });
        return view;
    }

    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (compositeDisposable!=null && !compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }*/
}

package basic2.base;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import java.util.List;

import basic2.base.mvp.BaseLogicImp;
import basic2.base.mvp.BaseView;
import basic2.control.annotation.BasicKnife;

/**
 * @USER: https://github.com/meikoz
 * @DATE: 2017/5/17
 */

public abstract class BaseActy<T extends BaseLogicImp> extends FragmentActivity implements BaseView {

    protected abstract void setupLayout();

    protected abstract Class<T> getLogicClzz();

    protected T mLogicImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BasicKnife.bind(this);
        configureLogicImpl();
        setupLayout();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mLogicImpl != null && mLogicImpl.view() != this) mLogicImpl.bind(this);
    }

    private void configureLogicImpl() {
        if (mLogicImpl == null) {
            try {
                mLogicImpl = getLogicClzz().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /*-------------View Method-------------*/
    @Override
    public void showLoading() {

    }

    @Override
    public void closeLoading() {

    }

    @Override
    public void showErrorView() {

    }

    /*-------------function Method-------------*/
    public boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public void showToast(@StringRes int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    public void showToast(CharSequence string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
}
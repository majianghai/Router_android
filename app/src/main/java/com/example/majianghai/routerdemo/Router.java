package com.example.majianghai.routerdemo;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import java.util.Map;
import java.util.Stack;

public class Router {

    private static Stack<Activity> activityStack;
    private static Router instance;

    public Map params;

    /// 单例
    public static Router getInstance() {
        if (instance == null) {
            instance = new Router();
        }

        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        return instance;
    }


    /// 向前跳转方法
    public void routeForward(Class<?> cls, Map params) {

        instance.params = params;

        Intent intent = new Intent();

        Activity currentActivity = currentActivity();

        intent.setClass(currentActivity, cls);

        currentActivity.startActivity(intent);
    }


    /// 向后跳转到指定的控制器
    public void routeBack(Class<?> cls, Map params) {

        BaseActivity backActivity = (BaseActivity) getActivity(cls);

        if (backActivity == null) return;

        backActivity.backParams = params;

        for (int i = activityStack.size() - 1; i > 0; i--) {

            Activity activity = activityStack.get(i);

            if (activity.equals(backActivity) == false) {
                finishActivity(activity);
            } else {
                return;
            }
        }
    }

    public int size() {
        return activityStack.size();
    }


    /// 添加activity到管理栈
    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    /// 获取当前的activity
    public Activity currentActivity() {
        return activityStack.lastElement();
    }


    /// 结束指定的Activity
    public void finishActivity(Activity activity) {
        if (activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        } else  {
            Log.e("RouterError","移除控制器失败");
        }
    }


    /// 结束当前的activity
    public void finishCurrentActivity() {
        Activity activity = currentActivity();
        finishActivity(activity);
    }

    /// 结束指定类名的控制器
    public void finishActivity(Class<Activity> cls) {

        for (int i = 0; i < activityStack.size(); i++) {

            Activity activity = activityStack.get(i);

            if (activity.getClass().equals(cls)) {

                finishActivity(activity);
            }
        }
    }


    /// 结束所有的控制器
    public void finishAllActivity() {
        for (Activity activity : activityStack) {
            finishActivity(activity);
        }

        activityStack.clear();
    }



    /// 获取指定类名的首个控制器
    public Activity getActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }

        return null;
    }








}

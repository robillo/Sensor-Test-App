package com.appbusters.robinkamboj.senseitall.view.main_activity.list_fragment.adapter;

import android.content.Context;
import android.content.Intent;

import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;

public interface GenericAdapterInterface {

    Intent returnDetailActivityIntent(Context context, GenericData data);
}

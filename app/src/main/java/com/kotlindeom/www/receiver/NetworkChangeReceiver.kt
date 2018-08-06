package com.kotlindeom.www.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.kotlindeom.www.constant.Constant
import com.kotlindeom.www.event.NetworkChangeEvent
import com.kotlindeom.www.ext.loge
import com.kotlindeom.www.utils.NetWorkUtil
import com.kotlindeom.www.utils.Preference
import org.greenrobot.eventbus.EventBus

/**
 * Created by chenxz on 2018/8/1.
 */
class NetworkChangeReceiver : BroadcastReceiver() {

    /**
     * 缓存上一次的网络状态
     */
    private var hasNetwork: Boolean by Preference(Constant.HAS_NETWORK_KEY, true)

    override fun onReceive(context: Context, intent: Intent) {
        val isConnected = NetWorkUtil.isNetworkConnected(context)
        loge("onReceive：当前网络状态------>>$isConnected")
        if (isConnected) {
            if (isConnected != hasNetwork) {
                EventBus.getDefault().post(NetworkChangeEvent(isConnected))
            }
        } else {
            EventBus.getDefault().post(NetworkChangeEvent(isConnected))
        }
    }

}
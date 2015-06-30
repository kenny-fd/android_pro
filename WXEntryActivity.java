package cn.warthog.playercommunity.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.warthog.playercommunity.R;
import cn.warthog.playercommunity.legacy.common.share.AbstractSharePlatform;
import cn.warthog.playercommunity.legacy.common.share.ShareCallback;
import cn.warthog.playercommunity.legacy.common.share.SharePlatformFactory;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler{
    
    private IWXAPI mWXAPI;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.page_loading);14修改
        Intent intent = getIntent();
        mWXAPI.handleIntent(intent, this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mWXAPI.handleIntent(intent, this);
    }

    @Override
    public void onResp(BaseResp resp) {
        // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
        case BaseResp.ErrCode.ERR_OK:
            result = ShareCallback.SHARE_SUCCESS;15修改
            break;
        case BaseResp.ErrCode.ERR_USER_CANCEL:
            result = ShareCallback.SHARE_CANCEL;
            break;
        case BaseResp.ErrCode.ERR_AUTH_DENIED:
            result = ShareCallback.SHARE_AUTH_DENIED;
            break;
        default:
            result = ShareCallback.SHARE_FAILED;
            break;
        }
    }

}

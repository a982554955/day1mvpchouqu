package frame;

import android.content.Context;

import com.example.frame.R;

public class Host {
    /**
     *     <string name="bbs_url">https://bbs.zhulong.com/</string>
     *     <string name="bbs_api">https://bbs.zhulong.com/api/</string>
     *     <string name="bbs_openapi">https://bbs.zhulong.com/openapi/</string>
     *     <string name="webrule_url">https://news.zhulong.com/webrule.txt</string>
     *     <string name="upload_photo">https://f.zhulong.com/uploadZhulongImgForEdit.php</string>
     *     <string name="message">https://message.zhulong.com/</string>
     *     <string name="message_api">https://message.zhulong.com/api/</string>
     *     <string name="message_openapi">https://message.zhulong.com/openapi/</string>
     *     <string name="passport">https://passport.zhulong.com/</string>
     *     <string name="passport_api">https://passport.zhulong.com/api/</string>
     *     <string name="passport_openapi">https://passport.zhulong.com/openapi/</string>
     *     <string name="weibo">https://t.zhulong.com/</string>
     *     <string name="weibo_api">https://t.zhulong.com/api/</string>
     *     <string name="weibo_openapi">https://t.zhulong.com/openapi/</string>
     *     <string name="edu_url">https://edu.zhulong.com/</string>
     *     <string name="edu_api">https://edu.zhulong.com/api/</string>
     *     <string name="edu_openapi">https://edu.zhulong.com/openapi/</string>
     *     <string name="passport_openapi_user">https://passport.zhulong.com/openapi/user/</string>
     *     <string name="blog_api">https://blog.zhulong.com/api/</string>
     *     <string name="s_url">https://s.zhulong.com/</string>
     *     <string name="topic_openapi">https://s.zhulong.com/api/bbs</string>
     *     <string name="info_openapi">https://s.zhulong.com/api/info</string>
     *     <string name="all_openapi">https://s.zhulong.com/api/all</string>
     *     <string name="payment_agreement_api">https://m.edu.zhulong.com/xieyi/eduxy.html?is_app=1</string>
     *     <string name="e_payment_agreement_api">https://passport.zhulong.com/score/exy</string>
     *       <string name="ad_openapi">https://a.zhulong.com/openapi/</string>
     *     <string name="foolow_list_openapi">https://bbs.zhulong.com/openapi/thread/</string>
     *     <string name="vip_list_openapi">https://edu.zhulong.com/openapi/lesson/</string>
     *     <string name="fx_openapi">https://fx.zhulong.com/openapi/</string>
     *     <string name="photo_url">https://photo.zhulong.com/</string>
     *     <string name="photo_openapi">https://photo.zhulong.com/openapi/</string>
     *     */
    private static Context mContext=FrameApplication.getFrameApplicationContext();
    public static final int API_TYPE = 3;//1,内测  2，外测  3，外正
    public static String AD_OPENAPI ;
    public static String BBS_URL ;
    public static String BBS_OPENAPI ;
    public static String WEBRULE_URL ;
    public static String UPLOAD_PHOTO ;
    public static String MESSAGE ;
    public static String MESSAGE_API ;
    public static String MESSAGE_OPENAPI ;
    public static String PASSPORT ;
    public static String PASSPORT_API ;
    public static String PASSPORT_OPENAPI ;
    public static String WEIBO ;
    public static String WEIBO_API ;
    public static String WEIBO_OPENAPI ;
    public static String EDU_URL ;
    public static String EDU_API ;
    public static String EDU_OPENAPI ;
    public static String PASSPORT_OPENAPI_USER ;
    public static String BLOG_API ;
    public static String S_URL ;
    public static String TOPIC_OPENAPI ;
    public static String INFO_OPENAPI ;
    public static String ALL_OPENAPI ;
    public static String PAYMENT_AGREEMENT_API ;
    public static String E_PAYMENT_AGREEMENT_API ;
    public static String FOOLOW_LIST_OPENAPI ;
    public static String VIP_LIST_OPENAPI ;
    public static String FX_OPENAPI ;
    public static String PHOTO_URL ;
    public static String PHOTO_OPENAPI ;
    public static String BBS_API ;
    public static String WX_OAUTH;

    /**
     * 静态代码块，优先于对象的创建而执行，且只执行一次
     */
    static {
        if (API_TYPE == 1){
            AD_OPENAPI = "";
        }
        if (API_TYPE == 2){
            AD_OPENAPI = "";
        }
        if (API_TYPE == 3){
            WX_OAUTH="https://api.weixin.qq.com/sns/oauth2/";
            AD_OPENAPI = mContext.getString(R.string.ad_openapi);
            PHOTO_URL = mContext.getString(R.string.photo_url);
            BBS_API = mContext.getString(R.string.bbs_api);
            PHOTO_OPENAPI = mContext.getString(R.string.photo_openapi);
            FX_OPENAPI = mContext.getString(R.string.fx_openapi);
            VIP_LIST_OPENAPI = mContext.getString(R.string.vip_list_openapi);
            FOOLOW_LIST_OPENAPI = mContext.getString(R.string.foolow_list_openapi);
            S_URL = mContext.getString(R.string.s_url);
            PAYMENT_AGREEMENT_API = mContext.getString(R.string.payment_agreement_api);
            BLOG_API = mContext.getString(R.string.blog_api);
            TOPIC_OPENAPI = mContext.getString(R.string.topic_openapi);
            INFO_OPENAPI = mContext.getString(R.string.info_openapi);
            ALL_OPENAPI = mContext.getString(R.string.all_openapi);
            E_PAYMENT_AGREEMENT_API = mContext.getString(R.string.e_payment_agreement_api);

            BBS_URL=mContext.getString(R.string.bbs_url);
            BBS_OPENAPI=mContext.getString(R.string.bbs_openapi);
            WEBRULE_URL=mContext.getString(R.string.webrule_url);
            UPLOAD_PHOTO=mContext.getString(R.string.upload_photo);
            MESSAGE=mContext.getString(R.string.message);
            MESSAGE_API=mContext.getString(R.string.message_api);
            MESSAGE_OPENAPI=mContext.getString(R.string.message_openapi);
            PASSPORT=mContext.getString(R.string.passport);
            PASSPORT_API=mContext.getString(R.string.passport_api);
            PASSPORT_OPENAPI=mContext.getString(R.string.passport_openapi);
            WEIBO=mContext.getString(R.string.weibo);
            WEIBO_API=mContext.getString(R.string.weibo_api);
            WEIBO_OPENAPI=mContext.getString(R.string.weibo_openapi);
            EDU_URL=mContext.getString(R.string.edu_url);
            EDU_API=mContext.getString(R.string.edu_api);
            EDU_OPENAPI=mContext.getString(R.string.edu_openapi);
            PASSPORT_OPENAPI_USER=mContext.getString(R.string.passport_openapi_user);
        }
    }
}

package com.naruto.a_presentsay.tool;

/**
 * Created by dllo on 16/11/23.
 */

public class UrlTools {

   // 轮播图
   public static final String PICTURE = "http://api.liwushuo.com/v2/banners";

   // 首页tablayout
   public static final String TITLE = "http://api.liwushuo.com/v2/channels/preset?gender=1&generation=2";
   // 精选
   public static final String CHOOSE = "http://api.liwushuo.com/v2/channels/101/items_v2?ad=2&gender=1&generation=2&limit=20&offset=0";
   // 首页首尾
   public static final String HOME_HEAD = "http://api.liwushuo.com/v2/channels/";
   public static final String HOME_TAIL = "/items_v2?gender=1&limit=20&offset=0&generation=2";
   // 六宫格
   public static final String GRIDE = "http://api.liwushuo.com/v2/secondary_banners?gender=1&generation=2";
   // 榜单收尾
   public static final String HEAD = "http://api.liwushuo.com/v2/ranks_v3/ranks/";
   public static final String TAIL = "?limit=20&offset=0";
   // 榜单tablayout
   public static final String GIFT_TAB = "http://api.liwushuo.com/v2/ranks_v2/ranks";
}

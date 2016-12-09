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
   // 分类-攻略-栏目
   public static final String COLUMN = "http://api.liwushuo.com/v2/columns?limit=20&offset=0";
   // 分类-攻略-品类&&风格&&对象
   public static final String STRATEGY_FOOT = "http://api.liwushuo.com/v2/channel_groups/all";
   // 分类_单品
   public static final String SINGLE = "http://api.liwushuo.com/v2/item_categories/tree";
   // 轮播图详情页
   public static final String BANNER_HEAD = "http://api.liwushuo.com/v2/collections/";
   public static final String BANNER_TAIL = "/posts?limit=20&offset=0";
   // 首页Grid详情页
   public static final String HOME_GRID_HEAD = "http://api.liwushuo.com/v2/collections/";
   public static final String HOME_GRID_TAIL = "/posts?limit=20&offset=0";
   // 首页Listview详情页
   public static final String HOME_LISTVIEW_HEAD = "http://www.liwushuo.com/posts/";
   public static final String HOME_LISTVIEW_TAIL = "/content";
   // 榜单页上部详情页
   public static final String GIFT_ONE_HEAD = "http://api.liwushuo.com/v2/items/";
   // 榜单页下部RecyclerView详情页
   public static final String GIFT_RV_HEAD = "http://api.liwushuo.com/v2/items/";
   public static final String GIFT_RV_TAIL = "/recommend?num=20&post_num=5";
   // 榜单详情页页评论
   public static final String GIFT_REVIEW_HEAD = "http://api.liwushuo.com/v2/items/";
   public static final String GIFT_REVIEW_TAIL = "/comments?limit=20&offset=0";
}

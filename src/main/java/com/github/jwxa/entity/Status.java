/*
 * Copyright (C) 2010-2013 The SINA WEIBO Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.jwxa.entity;


import lombok.Data;

import java.util.ArrayList;

/**
 * 微博结构体。
 *
 * @author SINA
 * @since 2013-11-22
 */
@Data
public class Status {

    /**
     * 微博创建时间
     */
    public String created_at;
    /**
     * 微博ID
     */
    public String id;
    /**
     * 微博MID
     */
    public String mid;
    /**
     * 字符串型的微博ID
     */
    public String idstr;
    /**
     * 微博文本内容长度
     */
    public int textLength;
    /**
     * 微博信息内容
     */
    public String text;
    /**
     * 是否是超过140个字的长微博
     */
    public boolean isLongText;
    /**
     * 微博来源类型
     */
    public int source_type;
    /**
     * 微博来源
     */
    public String source;
    /**
     * 是否已收藏，true：是，false：否
     */
    public boolean favorited;
    /**
     * 是否被截断，true：是，false：否
     */
    public boolean truncated;
    /**
     * （暂未支持）回复ID
     */
    public String in_reply_to_status_id;
    /**
     * （暂未支持）回复人UID
     */
    public String in_reply_to_user_id;
    /**
     * （暂未支持）回复人昵称
     */
    public String in_reply_to_screen_name;
    /**
     * 缩略图片地址（小图），没有时不返回此字段
     */
    public String thumbnail_pic;
    /**
     * 中等尺寸图片地址（中图），没有时不返回此字段
     */
    public String bmiddle_pic;
    /**
     * 原始图片地址（原图），没有时不返回此字段
     */
    public String original_pic;
    /**
     * 地理信息字段
     */
    public String geo;
    /**
     * 微博作者的用户信息字段
     */
    public User user;
    /**
     * 被转发的原微博信息字段，当该微博为转发微博时返回
     */
    public Status retweeted_status;
    /**
     * 转发数
     */
    public int reposts_count;
    /**
     * 评论数
     */
    public int comments_count;
    /**
     * 表态数
     */
    public int attitudes_count;
    /**
     * 暂未支持
     */
    public int mlevel;
    /**
     * 微博的可见性及指定可见分组信息。该 object 中 type 取值，
     * 0：普通微博，1：私密微博，3：指定分组微博，4：密友微博；
     * list_id为分组的组号
     */
//    public String visible;
    /**
     * 微博来源是否允许点击，如果允许
     */
    public int source_allowclick;

    /**
     * 微博图片字段
     */
    public ArrayList<PicUrlsBean> pic_urls;


    /**
     * 缩略图的url，本地私有的字段，服务器不会返回此字段，在gson赋值完成后，需要手动为此字段赋值
     */

    public ArrayList<String> thumbnail_pic_urls = new ArrayList<>();

    /**
     * 中等质量图片的url，本地私有的字段，服务器不会返回此字段，在gson赋值完成后，需要手动为此字段赋值
     */
    public ArrayList<String> bmiddle_pic_urls = new ArrayList<>();

    /**
     * 原图的url，本地私有的字段，服务器不会返回此字段，在gson赋值完成后，需要手动为此字段赋值
     */
    public ArrayList<String> origin_pic_urls = new ArrayList<>();

    /**
     * 单张微博的尺寸，本地私有的字段，服务器不会返回此字段，在gson赋值完成后，需要手动为此字段赋值
     */
    public String singleImgSizeType;



    @Data
    public static class PicUrlsBean  {
        public String thumbnail_pic;
    }

}

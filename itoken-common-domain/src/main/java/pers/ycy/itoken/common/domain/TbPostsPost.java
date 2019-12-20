package pers.ycy.itoken.common.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tb_posts_post")
@Getter
@Setter
public class TbPostsPost extends BaseDomain{
    private static final long serialVersionUID = 4325450029389679348L;
    /**
     * 文章编码
     */
    @Id
    @Column(name = "post_guid")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private String postGuid;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章发布时间
     */
    @Column(name = "time_published")
    private Date timePublished;

    /**
     * 文章状态（0草稿 1已发布的文章 2待审核的文章 3被拒绝文章 4定时发布的文章）
     */
    private String status;

    /**
     * 文章别名
     */
    private String alias;

    /**
     * 文章得分
     */
    private Short score;

    /**
     * 被赞数
     */
    @Column(name = "number_of_upvotes")
    private Integer numberOfUpvotes;

    /**
     * 被踩数
     */
    @Column(name = "number_of_downvotes")
    private Integer numberOfDownvotes;

    /**
     * 被阅读数
     */
    @Column(name = "number_of_reads")
    private Integer numberOfReads;

    /**
     * 被分享数
     */
    @Column(name = "number_of_shares")
    private Integer numberOfShares;

    /**
     * 被收藏数
     */
    @Column(name = "number_of_bookmarks")
    private Integer numberOfBookmarks;

    /**
     * 被评论数
     */
    @Column(name = "number_of_comments")
    private Integer numberOfComments;

    /**
     * 文章审核被拒理由
     */
    @Column(name = "reject_msg")
    private String rejectMsg;

    /**
     * 文章的阅读权限（0无限制 1会员）
     */
    private String access;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 文章正文
     */
    private String main;

    /**
     * 文章作者对象
     */
    private String authors;

    /**
     * 封面缩略图片
     */
    @Column(name = "thumb_image")
    private String thumbImage;

    /**
     * 裁剪后不带尺寸的正文图片数组
     */
    @Column(name = "original_images")
    private String originalImages;

    /**
     * 裁剪后带尺寸的正文图片数组
     */
    private String images;

    /**
     * 裁剪前的正文图片数组
     */
    @Column(name = "full_size_images")
    private String fullSizeImages;

    /**
     * 文章标签
     */
    private String tags;

    /**
     * 文章特色标签
     */
    @Column(name = "v_tags")
    private String vTags;

    /**
     * 一篇文章的系列集合
     */
    private String series;
}
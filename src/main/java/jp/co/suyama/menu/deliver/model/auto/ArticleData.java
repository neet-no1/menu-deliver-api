package jp.co.suyama.menu.deliver.model.auto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ArticleData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-24T14:39:40.139+09:00")




public class ArticleData   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("detail")
  private String detail = null;

  @JsonProperty("imgPath")
  private String imgPath = null;

  @JsonProperty("date")
  private String date = null;

  @JsonProperty("userId")
  private Integer userId = null;

  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("userIconPath")
  private String userIconPath = null;

  @JsonProperty("contents")
  private String contents = null;

  @JsonProperty("opened")
  private Boolean opened = null;

  public ArticleData id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * 記事ID
   * @return id
  **/
  @ApiModelProperty(example = "3", value = "記事ID")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ArticleData title(String title) {
    this.title = title;
    return this;
  }

  /**
   * 記事のタイトル
   * @return title
  **/
  @ApiModelProperty(example = "体にいい健康食 part1", value = "記事のタイトル")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ArticleData detail(String detail) {
    this.detail = detail;
    return this;
  }

  /**
   * 詳細文頭
   * @return detail
  **/
  @ApiModelProperty(example = "記事の文頭から始まるある程度の文字列", value = "詳細文頭")


  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public ArticleData imgPath(String imgPath) {
    this.imgPath = imgPath;
    return this;
  }

  /**
   * 表示する画像のパス
   * @return imgPath
  **/
  @ApiModelProperty(example = "/public/article_images/xxx", value = "表示する画像のパス")


  public String getImgPath() {
    return imgPath;
  }

  public void setImgPath(String imgPath) {
    this.imgPath = imgPath;
  }

  public ArticleData date(String date) {
    this.date = date;
    return this;
  }

  /**
   * 記事の更新日
   * @return date
  **/
  @ApiModelProperty(example = "2021/03/18", value = "記事の更新日")


  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public ArticleData userId(Integer userId) {
    this.userId = userId;
    return this;
  }

  /**
   * 投稿ユーザID
   * @return userId
  **/
  @ApiModelProperty(example = "32", value = "投稿ユーザID")


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public ArticleData userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * 投稿ユーザ名
   * @return userName
  **/
  @ApiModelProperty(example = "サンプル太朗", value = "投稿ユーザ名")


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public ArticleData userIconPath(String userIconPath) {
    this.userIconPath = userIconPath;
    return this;
  }

  /**
   * ユーザアイコン画像のパス
   * @return userIconPath
  **/
  @ApiModelProperty(example = "/public/user_images/xxx", value = "ユーザアイコン画像のパス")


  public String getUserIconPath() {
    return userIconPath;
  }

  public void setUserIconPath(String userIconPath) {
    this.userIconPath = userIconPath;
  }

  public ArticleData contents(String contents) {
    this.contents = contents;
    return this;
  }

  /**
   * 記事内容のパス
   * @return contents
  **/
  @ApiModelProperty(example = "/public/article_contents/xxx", value = "記事内容のパス")


  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public ArticleData opened(Boolean opened) {
    this.opened = opened;
    return this;
  }

  /**
   * 公開済みか
   * @return opened
  **/
  @ApiModelProperty(example = "true", value = "公開済みか")


  public Boolean isOpened() {
    return opened;
  }

  public void setOpened(Boolean opened) {
    this.opened = opened;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArticleData articleData = (ArticleData) o;
    return Objects.equals(this.id, articleData.id) &&
        Objects.equals(this.title, articleData.title) &&
        Objects.equals(this.detail, articleData.detail) &&
        Objects.equals(this.imgPath, articleData.imgPath) &&
        Objects.equals(this.date, articleData.date) &&
        Objects.equals(this.userId, articleData.userId) &&
        Objects.equals(this.userName, articleData.userName) &&
        Objects.equals(this.userIconPath, articleData.userIconPath) &&
        Objects.equals(this.contents, articleData.contents) &&
        Objects.equals(this.opened, articleData.opened);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, detail, imgPath, date, userId, userName, userIconPath, contents, opened);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArticleData {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
    sb.append("    imgPath: ").append(toIndentedString(imgPath)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    userIconPath: ").append(toIndentedString(userIconPath)).append("\n");
    sb.append("    contents: ").append(toIndentedString(contents)).append("\n");
    sb.append("    opened: ").append(toIndentedString(opened)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


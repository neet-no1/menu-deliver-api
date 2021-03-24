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
 * QuestionData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-24T14:39:40.139+09:00")




public class QuestionData   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("contents")
  private String contents = null;

  @JsonProperty("images")
  private String images = null;

  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("userIcon")
  private String userIcon = null;

  @JsonProperty("mine")
  private Boolean mine = null;

  public QuestionData id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * 質問ID
   * @return id
  **/
  @ApiModelProperty(example = "3", value = "質問ID")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public QuestionData contents(String contents) {
    this.contents = contents;
    return this;
  }

  /**
   * 質問内容
   * @return contents
  **/
  @ApiModelProperty(example = "胃腸の調子が悪いのですが何かいい食べ物はありますか？", value = "質問内容")


  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public QuestionData images(String images) {
    this.images = images;
    return this;
  }

  /**
   * 画像のパス
   * @return images
  **/
  @ApiModelProperty(example = "/public/question_images/xxx", value = "画像のパス")


  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
  }

  public QuestionData userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * ニックネーム
   * @return userName
  **/
  @ApiModelProperty(example = "サンプル太朗", value = "ニックネーム")


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public QuestionData userIcon(String userIcon) {
    this.userIcon = userIcon;
    return this;
  }

  /**
   * ユーザアイコンのパス
   * @return userIcon
  **/
  @ApiModelProperty(example = "/public/user_images/xxx", value = "ユーザアイコンのパス")


  public String getUserIcon() {
    return userIcon;
  }

  public void setUserIcon(String userIcon) {
    this.userIcon = userIcon;
  }

  public QuestionData mine(Boolean mine) {
    this.mine = mine;
    return this;
  }

  /**
   * 自分が投稿したものか
   * @return mine
  **/
  @ApiModelProperty(example = "true", value = "自分が投稿したものか")


  public Boolean isMine() {
    return mine;
  }

  public void setMine(Boolean mine) {
    this.mine = mine;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuestionData questionData = (QuestionData) o;
    return Objects.equals(this.id, questionData.id) &&
        Objects.equals(this.contents, questionData.contents) &&
        Objects.equals(this.images, questionData.images) &&
        Objects.equals(this.userName, questionData.userName) &&
        Objects.equals(this.userIcon, questionData.userIcon) &&
        Objects.equals(this.mine, questionData.mine);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, contents, images, userName, userIcon, mine);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuestionData {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    contents: ").append(toIndentedString(contents)).append("\n");
    sb.append("    images: ").append(toIndentedString(images)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    userIcon: ").append(toIndentedString(userIcon)).append("\n");
    sb.append("    mine: ").append(toIndentedString(mine)).append("\n");
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


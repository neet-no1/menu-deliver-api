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
 * AnswerData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-24T14:39:40.139+09:00")




public class AnswerData   {
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

  public AnswerData id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * 回答ID
   * @return id
  **/
  @ApiModelProperty(example = "3", value = "回答ID")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public AnswerData contents(String contents) {
    this.contents = contents;
    return this;
  }

  /**
   * 回答内容
   * @return contents
  **/
  @ApiModelProperty(example = "そんなものは存在しません", value = "回答内容")


  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public AnswerData images(String images) {
    this.images = images;
    return this;
  }

  /**
   * 画像のパス
   * @return images
  **/
  @ApiModelProperty(example = "/public/answer_images/xxx", value = "画像のパス")


  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
  }

  public AnswerData userName(String userName) {
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

  public AnswerData userIcon(String userIcon) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnswerData answerData = (AnswerData) o;
    return Objects.equals(this.id, answerData.id) &&
        Objects.equals(this.contents, answerData.contents) &&
        Objects.equals(this.images, answerData.images) &&
        Objects.equals(this.userName, answerData.userName) &&
        Objects.equals(this.userIcon, answerData.userIcon);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, contents, images, userName, userIcon);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnswerData {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    contents: ").append(toIndentedString(contents)).append("\n");
    sb.append("    images: ").append(toIndentedString(images)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    userIcon: ").append(toIndentedString(userIcon)).append("\n");
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


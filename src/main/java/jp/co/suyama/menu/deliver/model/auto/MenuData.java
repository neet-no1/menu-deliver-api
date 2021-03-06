package jp.co.suyama.menu.deliver.model.auto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MenuData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-30T01:12:08.040+09:00")




public class MenuData   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("subTitle")
  private String subTitle = null;

  @JsonProperty("categoryId")
  private Integer categoryId = null;

  @JsonProperty("thumbPath")
  private String thumbPath = null;

  @JsonProperty("contents")
  private String contents = null;

  @JsonProperty("imagePaths")
  @Valid
  private List<Object> imagePaths = null;

  @JsonProperty("opened")
  private Boolean opened = null;

  @JsonProperty("mine")
  private Boolean mine = null;

  public MenuData id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * 献立ID
   * @return id
  **/
  @ApiModelProperty(example = "3", value = "献立ID")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public MenuData title(String title) {
    this.title = title;
    return this;
  }

  /**
   * 献立のタイトル
   * @return title
  **/
  @ApiModelProperty(example = "豚肉の生姜焼き、ほうれん草の胡麻和え、味噌汁", value = "献立のタイトル")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public MenuData subTitle(String subTitle) {
    this.subTitle = subTitle;
    return this;
  }

  /**
   * 献立のサブタイトル
   * @return subTitle
  **/
  @ApiModelProperty(example = "材料少なめで、すぐできます。", value = "献立のサブタイトル")


  public String getSubTitle() {
    return subTitle;
  }

  public void setSubTitle(String subTitle) {
    this.subTitle = subTitle;
  }

  public MenuData categoryId(Integer categoryId) {
    this.categoryId = categoryId;
    return this;
  }

  /**
   * 献立のカテゴリ
   * @return categoryId
  **/
  @ApiModelProperty(example = "1", value = "献立のカテゴリ")


  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public MenuData thumbPath(String thumbPath) {
    this.thumbPath = thumbPath;
    return this;
  }

  /**
   * サムネイル画像パス
   * @return thumbPath
  **/
  @ApiModelProperty(example = "/public/menu_images/xxx", value = "サムネイル画像パス")


  public String getThumbPath() {
    return thumbPath;
  }

  public void setThumbPath(String thumbPath) {
    this.thumbPath = thumbPath;
  }

  public MenuData contents(String contents) {
    this.contents = contents;
    return this;
  }

  /**
   * 献立の栄養素・作り方の情報パス
   * @return contents
  **/
  @ApiModelProperty(example = "/public/menu_contents/xxx", value = "献立の栄養素・作り方の情報パス")


  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public MenuData imagePaths(List<Object> imagePaths) {
    this.imagePaths = imagePaths;
    return this;
  }

  public MenuData addImagePathsItem(Object imagePathsItem) {
    if (this.imagePaths == null) {
      this.imagePaths = new ArrayList<Object>();
    }
    this.imagePaths.add(imagePathsItem);
    return this;
  }

  /**
   * Get imagePaths
   * @return imagePaths
  **/
  @ApiModelProperty(value = "")


  public List<Object> getImagePaths() {
    return imagePaths;
  }

  public void setImagePaths(List<Object> imagePaths) {
    this.imagePaths = imagePaths;
  }

  public MenuData opened(Boolean opened) {
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

  public MenuData mine(Boolean mine) {
    this.mine = mine;
    return this;
  }

  /**
   * 自分が投稿したものか
   * @return mine
  **/
  @ApiModelProperty(example = "false", value = "自分が投稿したものか")


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
    MenuData menuData = (MenuData) o;
    return Objects.equals(this.id, menuData.id) &&
        Objects.equals(this.title, menuData.title) &&
        Objects.equals(this.subTitle, menuData.subTitle) &&
        Objects.equals(this.categoryId, menuData.categoryId) &&
        Objects.equals(this.thumbPath, menuData.thumbPath) &&
        Objects.equals(this.contents, menuData.contents) &&
        Objects.equals(this.imagePaths, menuData.imagePaths) &&
        Objects.equals(this.opened, menuData.opened) &&
        Objects.equals(this.mine, menuData.mine);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, subTitle, categoryId, thumbPath, contents, imagePaths, opened, mine);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MenuData {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    subTitle: ").append(toIndentedString(subTitle)).append("\n");
    sb.append("    categoryId: ").append(toIndentedString(categoryId)).append("\n");
    sb.append("    thumbPath: ").append(toIndentedString(thumbPath)).append("\n");
    sb.append("    contents: ").append(toIndentedString(contents)).append("\n");
    sb.append("    imagePaths: ").append(toIndentedString(imagePaths)).append("\n");
    sb.append("    opened: ").append(toIndentedString(opened)).append("\n");
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


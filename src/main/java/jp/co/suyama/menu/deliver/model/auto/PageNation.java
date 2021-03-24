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
 * PageNation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-24T14:39:40.139+09:00")




public class PageNation   {
  @JsonProperty("totalPages")
  private Integer totalPages = null;

  @JsonProperty("currentPage")
  private Integer currentPage = null;

  public PageNation totalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  /**
   * 総ページ数
   * @return totalPages
  **/
  @ApiModelProperty(example = "3", value = "総ページ数")


  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public PageNation currentPage(Integer currentPage) {
    this.currentPage = currentPage;
    return this;
  }

  /**
   * 現在のページ数
   * @return currentPage
  **/
  @ApiModelProperty(example = "2", value = "現在のページ数")


  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PageNation pageNation = (PageNation) o;
    return Objects.equals(this.totalPages, pageNation.totalPages) &&
        Objects.equals(this.currentPage, pageNation.currentPage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalPages, currentPage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageNation {\n");
    
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
    sb.append("    currentPage: ").append(toIndentedString(currentPage)).append("\n");
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


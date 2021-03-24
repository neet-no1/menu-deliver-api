package jp.co.suyama.menu.deliver.model.auto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jp.co.suyama.menu.deliver.model.auto.ErrorInfo;
import jp.co.suyama.menu.deliver.model.auto.MenuDeliverResponse;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BasicResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-24T14:39:40.139+09:00")




public class BasicResponse extends MenuDeliverResponse  {
  @JsonProperty("code")
  private Integer code = null;

  @JsonProperty("errorInfo")
  private ErrorInfo errorInfo = null;

  @JsonProperty("info")
  private Integer info = null;

  public BasicResponse code(Integer code) {
    this.code = code;
    return this;
  }

  /**
   * 0以外はエラーコード
   * @return code
  **/
  @ApiModelProperty(example = "0", value = "0以外はエラーコード")


  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public BasicResponse errorInfo(ErrorInfo errorInfo) {
    this.errorInfo = errorInfo;
    return this;
  }

  /**
   * Get errorInfo
   * @return errorInfo
  **/
  @ApiModelProperty(value = "")

  @Valid

  public ErrorInfo getErrorInfo() {
    return errorInfo;
  }

  public void setErrorInfo(ErrorInfo errorInfo) {
    this.errorInfo = errorInfo;
  }

  public BasicResponse info(Integer info) {
    this.info = info;
    return this;
  }

  /**
   * 成功可否
   * @return info
  **/
  @ApiModelProperty(example = "0", value = "成功可否")


  public Integer getInfo() {
    return info;
  }

  public void setInfo(Integer info) {
    this.info = info;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BasicResponse basicResponse = (BasicResponse) o;
    return Objects.equals(this.code, basicResponse.code) &&
        Objects.equals(this.errorInfo, basicResponse.errorInfo) &&
        Objects.equals(this.info, basicResponse.info) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, errorInfo, info, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BasicResponse {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    errorInfo: ").append(toIndentedString(errorInfo)).append("\n");
    sb.append("    info: ").append(toIndentedString(info)).append("\n");
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


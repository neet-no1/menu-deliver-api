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
 * DecideBestAnswerParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-24T14:39:40.139+09:00")




public class DecideBestAnswerParam   {
  @JsonProperty("questionId")
  private Integer questionId = null;

  @JsonProperty("answerId")
  private Integer answerId = null;

  public DecideBestAnswerParam questionId(Integer questionId) {
    this.questionId = questionId;
    return this;
  }

  /**
   * 質問ID
   * @return questionId
  **/
  @ApiModelProperty(example = "3", value = "質問ID")


  public Integer getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Integer questionId) {
    this.questionId = questionId;
  }

  public DecideBestAnswerParam answerId(Integer answerId) {
    this.answerId = answerId;
    return this;
  }

  /**
   * 回答ID
   * @return answerId
  **/
  @ApiModelProperty(example = "5", value = "回答ID")


  public Integer getAnswerId() {
    return answerId;
  }

  public void setAnswerId(Integer answerId) {
    this.answerId = answerId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DecideBestAnswerParam decideBestAnswerParam = (DecideBestAnswerParam) o;
    return Objects.equals(this.questionId, decideBestAnswerParam.questionId) &&
        Objects.equals(this.answerId, decideBestAnswerParam.answerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(questionId, answerId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DecideBestAnswerParam {\n");
    
    sb.append("    questionId: ").append(toIndentedString(questionId)).append("\n");
    sb.append("    answerId: ").append(toIndentedString(answerId)).append("\n");
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


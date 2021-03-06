/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.19-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package jp.co.suyama.menu.deliver.controller.interfaces;

import jp.co.suyama.menu.deliver.model.auto.ArticleDataResponse;
import jp.co.suyama.menu.deliver.model.auto.ArticlesResponse;
import jp.co.suyama.menu.deliver.model.auto.BasicResponse;
import jp.co.suyama.menu.deliver.model.auto.DeleteArticleParam;
import jp.co.suyama.menu.deliver.model.auto.FavoriteArticleParam;
import org.springframework.core.io.Resource;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-30T13:28:54.722+09:00")

@Validated
@Api(value = "article", description = "the article API")
@RequestMapping(value = "")
public interface ArticleApi {

    @ApiOperation(value = "記事削除", nickname = "deleteArticle", notes = "記事を削除 認証必須 ", response = BasicResponse.class, tags={ "Article", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "通信成功時の返却値", response = BasicResponse.class),
        @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/article/delete",
        produces = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<BasicResponse> deleteArticle(@ApiParam(value = "" ,required=true )  @Valid @RequestBody DeleteArticleParam deleteArticleParam);


    @ApiOperation(value = "記事お気に入り追加・解除", nickname = "favoriteArticle", notes = "記事のお気に入りの追加と解除を行う 認証必須 ", response = BasicResponse.class, tags={ "Article", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "通信成功時の返却値", response = BasicResponse.class),
        @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/article/favorite",
        produces = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<BasicResponse> favoriteArticle(@ApiParam(value = "" ,required=true )  @Valid @RequestBody FavoriteArticleParam favoriteArticleParam);


    @ApiOperation(value = "記事内容取得", nickname = "getArticle", notes = "記事内容を取得する S3のパスを取得し、内容はS3から取得する 投稿済み：認証不要 編集中：認証必須 ", response = ArticleDataResponse.class, tags={ "Article", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "通信成功時の返却値", response = ArticleDataResponse.class),
        @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/article",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<ArticleDataResponse> getArticle(@NotNull @ApiParam(value = "記事ID", required = true) @Valid @RequestParam(value = "id", required = true) Integer id);


    @ApiOperation(value = "新着記事取得", nickname = "getArticleNewArrival", notes = "最近の投稿順(更新日時)で記事情報を取得 認証不要 ", response = ArticlesResponse.class, tags={ "Article", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "通信成功時の返却値", response = ArticlesResponse.class),
        @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/article/newarrival",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<ArticlesResponse> getArticleNewArrival();


    @ApiOperation(value = "記事投稿", nickname = "postArticle", notes = "記事を投稿する DBに必要なデータだけ保存して、S3にアップロードする 認証必須 ", response = BasicResponse.class, tags={ "Article", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "通信成功時の返却値", response = BasicResponse.class),
        @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/article",
        produces = { "application/json" },
        consumes = { "multipart/form-data" },
        method = RequestMethod.POST)
    ResponseEntity<BasicResponse> postArticle(@ApiParam(value = "記事ID　存在しない場合は0", required=true) @RequestParam(value="id", required=true)  Integer id,@ApiParam(value = "記事のタイトル", required=true) @RequestParam(value="title", required=true)  String title,@ApiParam(value = "内容", required=true) @RequestParam(value="contents", required=true)  String contents,@ApiParam(value = "投稿/保存のフラグ", required=true) @RequestParam(value="opened", required=true)  Boolean opened,@ApiParam(value = "記事のサムネイル画像") @Valid @RequestPart(value="thumb", required=false) MultipartFile thumb);

}

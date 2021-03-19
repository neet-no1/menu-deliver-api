/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.19-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package jp.co.suyama.menu.deliver.controller.interfaces;

import jp.co.suyama.menu.deliver.model.auto.AccountAuthResponse;
import jp.co.suyama.menu.deliver.model.auto.FollowUserParam;
import jp.co.suyama.menu.deliver.model.auto.FollowersResponse;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-19T19:06:35.752+09:00")

@Validated
@Api(value = "follow", description = "the follow API")
@RequestMapping(value = "")
public interface FollowApi {

    @ApiOperation(value = "ユーザをフォローする", nickname = "followUser", notes = "ユーザをフォローする 認証必須 ", response = AccountAuthResponse.class, tags={ "Follow", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "通信成功時の返却値", response = AccountAuthResponse.class),
        @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/follow",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<AccountAuthResponse> followUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody FollowUserParam followUserParam);


    @ApiOperation(value = "フォロー・フォロワー取得", nickname = "getFollowers", notes = "フォロー・フォロワーの情報を取得する 認証必須 ", response = FollowersResponse.class, tags={ "Follow", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "通信成功時の返却値", response = FollowersResponse.class),
        @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/follow/list",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<FollowersResponse> getFollowers(@ApiParam(value = "取得ページ番号(1~)", defaultValue = "1") @Valid @RequestParam(value = "page", required = false, defaultValue="1") Integer page);

}

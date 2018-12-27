package com.yh.springcloudauthserver.configuration;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

// 授权服务器配置
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends
        AuthorizationServerConfigurerAdapter {
    /**
     * 为了使用"密码"授权类型，我们需要连接并使用AuthenticationManager bean
     */
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;


    @Autowired
    private DataSource dataSource;
    /**
     * 必须加 不加会报错 仅在security5.0之后会报错
     * @return
     */


    /**
     * 定义令牌端点上的安全性约束
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(
            AuthorizationServerSecurityConfigurer oauthServer)
            throws Exception {
        //允许表单认证
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }


    /**
     * 用于定义客户端详细信息服务的配置程序。可以初始化客户端详细信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        clients.jdbc(dataSource);
        //已经存储在数据里 就不需要再with了
//                //简化模式（隐士模式）
//               .withClient("sampleClientId")  //必須客戶端ID
//                .authorizedGrantTypes("implicit")//授权客户端使用的授权类型。默认值为空。
//                .secret("123")//(可信客户端需要）客户端密钥（如果有）
//                .redirectUris("http://localhost:9001/callback")
//                .scopes("read")//客户受限的范围。如果范围未定义或为空（默认值），则客户端不受范围限制。
//                .authorities()//授予客户端的权限（常规Spring Security权限）。
//              //  .autoApprove(true)
//                .and()
//                .withClient("clientapp")//
//                // 密码模式,授权码模式，支持刷新令牌
//                .secret("123")
//              //  .authorizedGrantTypes("authorization_code","refresh_token")
//               .authorizedGrantTypes("authorization_code","refresh_token", "password")
//                .redirectUris("http://localhost:9001/callback")
//                .autoApprove(true)
//                .scopes("read","update","delete","put");
    }

    /**
     * 定义授权和令牌端点以及令牌服务
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(
            AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {

        endpoints
                //.pathMapping()//有两个参数，第一个是默认的URL路径，第二个是自定义的路径
                .tokenStore(tokenStore())//数据库保存数据
       // 为了使用"密码"授权类型，我们需要连接并使用AuthenticationManager bean
         .authenticationManager(authenticationManager);//
            }

    /**
     * 为了持久化令牌，我们使用了JdbcTokenStore
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

}
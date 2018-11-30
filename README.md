                                  **My suggestion before use this template**
**1** password encoder is different from spring boot 1.5. 
    i have to use CustomConfiguration to get the custom passwordEncoder. i had to add "{bcrypt}"
    in the prefix of the encoder to make it work. also need to encode the **client secret**
    if you don't encode the client-secret. you will face issue getting the access token.
    
**2**  Token Enhancer is used to add additional information in the authentication response.
    clear the oauth_access_token and oauth_refresh table from database to test its working or not.
    otherwise it will return the old authentication response from oauth_access_token table.
    
**3** customUserDetailsService must be provided in AuthorizationServerConfig to use 
    MethodSecurity annotation, such as @Secured, @PreAuthorize, @PostAuthorize etc 
    if you don't provide in the AuthorizationServer config these methods will not work
    properly. it will denied every request. but 'hasAuthority' will work in resource server config.
    so it creates a confusion why @Secured annotation is not working so you must assign 
    customUserDetails in authorizationServerConfig
    
**4** you must need to create table `oauth_access_token` and `oauth_refresh_token`
    with field `token` and `authentication with` with type `LONG VARBINARY`..
    otherwise spring security will throw an error and won't be able to store token in
    the table. mysql will not allow you to create table with that type. change the 
    intellij dialect to sqlite dialect OR use the mysql console to run the schema
    
    `create table if not exists oauth_access_token (
      token_id VARCHAR(255),
      token LONG VARBINARY,
      authentication_id VARCHAR(255) PRIMARY KEY,
      user_name VARCHAR(255),
      client_id VARCHAR(255),
      authentication LONG VARBINARY,
      refresh_token VARCHAR(255)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;`
    
    `create table if not exists oauth_refresh_token (
      token_id VARCHAR(255),
      token LONG VARBINARY,
      authentication LONG VARBINARY
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;`
  

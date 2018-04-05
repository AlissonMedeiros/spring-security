create table IF NOT EXISTS oauth_client_details(
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

create table IF NOT EXISTS oauth_client_token(
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create table IF NOT EXISTS oauth_access_token(
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(255)
);

create table IF NOT EXISTS oauth_refresh_token(
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);

create table IF NOT EXISTS oauth_code(
  code VARCHAR(255), authentication LONG VARBINARY
);

create table IF NOT EXISTS oauth_approvals(
	userId VARCHAR(255),
	clientId VARCHAR(255),
	scope VARCHAR(255),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP DEFAULT '1970-01-01 00:00:01'
);

create table IF NOT EXISTS ClientDetails(
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
);

create table IF NOT EXISTS users(
      username varchar(250) not null primary key,
      password varchar(500) not null,
      enabled boolean not null);

create table IF NOT EXISTS authorities (
  username varchar(50) not null,
  authority varchar(50) not null,
  constraint fk_authorities_users foreign key(username) references users(username));
  --create unique index ix_auth_username on authorities (username,authority);

create table IF NOT EXISTS groups (
  id bigint primary key,
  group_name varchar(50) not null);

create table IF NOT EXISTS group_authorities (
  group_id bigint not null,
  authority varchar(50) not null,
constraint fk_group_authorities_group foreign key(group_id) references groups(id));

create table IF NOT EXISTS group_members (
  id bigint primary key,
  username varchar(50) not null,
  group_id bigint not null,
  constraint fk_group_members_group foreign key(group_id) references groups(id));

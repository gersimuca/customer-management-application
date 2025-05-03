drop table if exists users;

create table users
(
    user_id              bigint identity (1,1),
    username             varchar(8)    not null unique,
    given_name           varchar(512)  not null,
    family_name          varchar(512)  not null,
    email                varchar(1024) not null,
    preferred_language   varchar(10)   not null default 'en-US',
    is_active            bit           not null default 1,
    last_updated_user_id bigint        not null default 1,
    last_updated_at      datetimeoffset      not null default current_timestamp,
    created_user_id      bigint        not null default 1,
    created_at           datetimeoffset      not null default current_timestamp,
    primary key (user_id)
);

alter table users
    add constraint fk_users_last_updated_user_id foreign key (last_updated_user_id) references users (user_id);
alter table users
    add constraint fk_users_created_user_id foreign key (created_user_id) references users (user_id);
drop table if exists notification;

create table notification
(
    notification_id              bigint identity (1,1),
    message           varchar(512)  not null,
    last_updated_user_id bigint        not null default 1,
    last_updated_at      datetimeoffset      not null default current_timestamp,
    created_user_id      bigint        not null default 1,
    created_at           datetimeoffset      not null default current_timestamp,
    primary key (notification_id)
);

alter table users
    add constraint fk_notification_last_updated_user_id foreign key (last_updated_user_id) references users (user_id);
alter table users
    add constraint fk_notification_created_user_id foreign key (created_user_id) references users (user_id);
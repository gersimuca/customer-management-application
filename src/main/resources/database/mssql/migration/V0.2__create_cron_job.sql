drop table if exists cron_job;

create table cron_job
(
    cron_job_id              bigint identity (1,1),
    name                     varchar(512)  not null unique,
    scheduled_time           varchar(512)  not null,
    last_updated_user_id bigint        not null default 1,
    last_updated_at      datetimeoffset      not null default current_timestamp,
    created_user_id      bigint        not null default 1,
    created_at           datetimeoffset      not null default current_timestamp,
    primary key (cron_job_id)
);

alter table users
    add constraint fk_cron_job_last_updated_user_id foreign key (last_updated_user_id) references users (user_id);
alter table users
    add constraint fk_cron_job_created_user_id foreign key (created_user_id) references users (user_id);
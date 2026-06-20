drop table if exists country;

create table country
(
    country_id              bigint              identity (1,1),
    country_code            VARCHAR(2)          UNIQUE NOT NULL,
    country_name            VARCHAR(255)        NOT NULL,
    last_updated_user_id    bigint              NOT NULL default 1,
    last_updated_at         datetimeoffset      NOT NULL default current_timestamp,
    created_user_id         bigint              NOT NULL default 1,
    created_at              datetimeoffset      NOT NULL default current_timestamp,
    primary key (country_id)
)
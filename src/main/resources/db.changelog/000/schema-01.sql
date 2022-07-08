create table if not exists url
(
    id                      uuid not null
        constraint url_pkey
            primary key,
    base_url                varchar(255),
    processed_url           varchar(255)
);

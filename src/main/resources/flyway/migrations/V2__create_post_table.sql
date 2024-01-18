create table post(
    id bigint primary key,
    title varchar(255),
    date_created date,
    date_last_update date,
    date_delete date,
    count_like bigint default 0,
    count_dislike bigint default 0,
    url_image varchar(255)
);
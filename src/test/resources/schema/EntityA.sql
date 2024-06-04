create table entitya
(
    not_null_key_part uuid not null,
    null_key_part     timestamp(6),
    uuid              uuid
);

create unique index entitya_composite_key on entitya (not_null_key_part, null_key_part);
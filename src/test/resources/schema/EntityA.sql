create table entitya
(
    not_null_key_part uuid not null,
    null_key_part     timestamp(6) default NULL::timestamp without time zone,
    uuid              uuid,
    CONSTRAINT entitya_composite_key UNIQUE NULLS NOT DISTINCT (not_null_key_part, null_key_part)
);
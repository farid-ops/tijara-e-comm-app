create schema if not exists tijara;

create TABLE IF NOT EXISTS tijara.product (
  id uuid NOT NULL DEFAULT random_uuid(),
  name varchar(56) NOT NULL,
  description varchar(200),
  price numeric(16, 4) DEFAULT 0 NOT NULL,
  count numeric(8, 0),
  image_url varchar(40),
  PRIMARY KEY(id)
);
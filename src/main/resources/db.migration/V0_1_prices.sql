CREATE TABLE IF NOT EXISTS prices (
  id BIGINT NOT NULL AUTO_INCREMENT,
  brand_id INTEGER NOT NULL,
	start_date TIMESTAMP NOT NULL DEFAULT (now() at time zone 'utc'),
	end_date TIMESTAMP NOT NULL DEFAULT (now() at time zone 'utc'),
	price_list BIGINT NOT NULL,
	product_id BIGINT NOT NULL,
	priority INTEGER NOT NULL,
	price NUMERIC(20,2) NOT NULL,
	curr VARCHAR(255) NOT NULL,
  CONSTRAINT prices_pkey PRIMARY KEY (id),
  CONSTRAINT prices_fk FOREIGN KEY (brand_id) REFERENCES brand (id)
);